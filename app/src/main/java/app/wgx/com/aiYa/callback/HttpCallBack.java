package app.wgx.com.aiYa.callback;

import com.lzy.okgo.callback.AbsCallback;

import app.wgx.com.aiYa.assistTool.MyLogger;
import okhttp3.Response;

/**
 * 自定义Callback
 */
public abstract class HttpCallBack extends AbsCallback<String> {
    /**
     * 拿到响应后，将数据转换成需要的格式，子线程中执行，可以是耗时操作
     *
     * @param response 需要转换的对象
     * @return 转换后的结果
     * @throws Exception 转换过程发生的异常
     */
    @Override
    public String convertSuccess(Response response) throws Exception {
        String result = response.body().string();
        MyLogger.json(result);
        //String sss= (String) JsonTool.getKeyValue(result,"msg");
        return result;
    }
}
