package app.wgx.com.aiYa.fragments.main.presenter;


import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Map;

import app.wgx.com.aiYa.assistTool.JsonTool;
import app.wgx.com.aiYa.assistTool.MyLogger;
import app.wgx.com.aiYa.bean.HomeBannerInfo;
import app.wgx.com.aiYa.callback.HttpCallBack;
import app.wgx.com.aiYa.callback.HttpResponseCallBack;
import app.wgx.com.aiYa.fragments.main.view.MainFragmentView;
import app.wgx.com.aiYa.httpTool.AsyncHttp;
import okhttp3.Call;
import okhttp3.Request;

/**
 * 主页 4 个fragment presenter
 */
public class MainFragmentPresenter {
    MainFragmentView mainFragmentView;

    public MainFragmentPresenter(MainFragmentView m) {
        this.mainFragmentView = m;
    }


    /**
     * 加载bannerView
     *
     * @param url
     * @param map
     */
    public void loadBanner(String url, Map<String, String> map) {
        AsyncHttp.getInstance().setHttpParams(url, map, new HttpResponseCallBack() {
            @Override
            public void start() {

            }

            @Override
            public void progress(long totalSize, float progress) {

            }

            @Override
            public void error(String msg) {

            }

            @Override
            public void success(String result) {
                HomeBannerInfo info= JsonTool.jsonToBean(result,HomeBannerInfo.class);
                MyLogger.i(info.getMsg());
            }

            @Override
            public void finish() {

            }
        });
    }

    /**
     * 加载List数据
     *
     * @param url
     * @param map
     */
    public void loadData(String url, Map<String, String> map) {
        OkHttpUtils.get()
                .url(url)
                .params(map)
                .build()
                .execute(new HttpCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mainFragmentView.error(call, e.getMessage().toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mainFragmentView.success(response.toString());
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        mainFragmentView.progress(total, progress);
                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        mainFragmentView.start();
                    }

                    @Override
                    public void onAfter(int id) {
                        super.onAfter(id);
                        mainFragmentView.finish();
                    }
                });
    }

}
