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
        AsyncHttp.getInstance().setHttpParams(url,map,new HttpResponseCallBack(){
            @Override
            public void success(String result) {
                super.success(result);
                mainFragmentView.loadBannerSuccess(result);
            }

            @Override
            public void error(String msg) {
                super.error(msg);
                mainFragmentView.loadBannerFailure(msg);
            }
        });
    }

    /**
     * 加载newsType
     *
     * @param url
     * @param map
     */
    public void loadNewsType(String url, Map<String, String> map) {
        AsyncHttp.getInstance().setHttpParams(url,map,new HttpResponseCallBack(){
            @Override
            public void success(String result) {
                super.success(result);
                mainFragmentView.loadNewsTypeSuccess(result);
            }

            @Override
            public void error(String msg) {
                super.error(msg);
                mainFragmentView.loadNewsTypeFailure(msg);
            }
        });
    }

    /**
     * 加载newsType
     *
     * @param url
     * @param map
     */
    public void loadNews(String url, Map<String, String> map) {
        AsyncHttp.getInstance().setHttpParams(url,map,new HttpResponseCallBack(){
            @Override
            public void success(String result) {
                super.success(result);
                mainFragmentView.loadNewsSuccess(result);
            }

            @Override
            public void error(String msg) {
                super.error(msg);
                mainFragmentView.loadNewsFailure(msg);
            }
        });
    }

}
