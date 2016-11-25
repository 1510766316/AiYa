package app.wgx.com.aiYa.httpTool;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Map;

import app.wgx.com.aiYa.callback.HttpCallBack;
import app.wgx.com.aiYa.callback.HttpResponseCallBack;
import okhttp3.Call;
import okhttp3.Request;


/**
 * 异步请求
 */
public class AsyncHttp implements Runnable {
    private static final int MSG_ERROR = 1;
    private static final int MSG_RESPONSE = 2;
    private static final int MSG_PROGRESS = 3;
    private static final int MSG_BEFORE = 4;
    private static final int MSG_AFTER = 5;

    private static AsyncHttp instance = new AsyncHttp();

    private String url;
    private Map<String, String> map;
    private HttpResponseCallBack httpResponseCallBack;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (null == httpResponseCallBack)
                throw new RuntimeException("HttpResponseCallBack can not be null");

            switch (msg.what) {
                case MSG_ERROR:
                    httpResponseCallBack.error((String) msg.obj);
                    break;
                case MSG_RESPONSE:
                    httpResponseCallBack.success((String) msg.obj);
                    break;
                case MSG_PROGRESS:
                    Object[] objects = (Object[]) msg.obj;
                    httpResponseCallBack.progress((long) objects[1], (float) objects[0]);
                    break;
                case MSG_BEFORE:
                    httpResponseCallBack.start();
                    break;
                case MSG_AFTER:
                    httpResponseCallBack.finish();
                    break;
            }
        }
    };

    public static  AsyncHttp getInstance() {
        return instance;
    }

    public void setHttpParams(String url, Map<String, String> map,HttpResponseCallBack callBack) {
        this.url = url;
        this.map = map;
        httpResponseCallBack = callBack;
        mHandler.post(this);
    }

    @Override
    public synchronized void run() {
        httpGet();
    }

    private void httpGet() {
        OkHttpUtils.get()
                .url(url)
                .params(map)
                .build().execute(new HttpCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                call.cancel();
                Message msg = new Message();
                msg.obj = e.getMessage().toString();
                msg.what = MSG_ERROR;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onResponse(String response, int id) {
                Message msg = new Message();
                msg.obj = response;
                msg.what = MSG_RESPONSE;
                mHandler.sendMessage(msg);
            }

            @Override
            public void inProgress(float progress, long total, int id) {
                super.inProgress(progress, total, id);
                Object[] objects = {progress, total};
                Message msg = new Message();
                msg.obj = objects;
                msg.what = MSG_PROGRESS;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
                mHandler.sendEmptyMessage(MSG_BEFORE);
            }

            @Override
            public void onAfter(int id) {
                super.onAfter(id);
                mHandler.sendEmptyMessage(MSG_AFTER);
            }
        });
    }


}
