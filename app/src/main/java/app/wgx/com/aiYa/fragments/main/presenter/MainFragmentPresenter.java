package app.wgx.com.aiYa.fragments.main.presenter;

import com.lzy.okgo.OkGo;

import java.util.Map;

import app.wgx.com.aiYa.callback.HttpCallBack;
import app.wgx.com.aiYa.fragments.main.view.MainFragmentView;
import okhttp3.Call;
import okhttp3.Response;

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
        OkGo.get(url)
                .params(map)
                .execute(new HttpCallBack() {
                    /**
                     * 对返回数据进行操作的回调， UI线程
                     */
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        mainFragmentView.loadBannerSuccess(s);
                    }

                    /**
                     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
                     */
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mainFragmentView.loadBannerFailure(e.getMessage().toString());
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
        OkGo.get(url)
                .params(map)
                .execute(new HttpCallBack() {
                    /**
                     * 对返回数据进行操作的回调， UI线程
                     */
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        mainFragmentView.loadNewsTypeSuccess(s);
                    }

                    /**
                     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
                     */
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mainFragmentView.loadNewsTypeFailure(e.getMessage().toString());
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
        OkGo.get(url)
                .params(map)
                .execute(new HttpCallBack() {
                    /**
                     * 对返回数据进行操作的回调， UI线程
                     */
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        mainFragmentView.loadNewsSuccess(s);
                    }

                    /**
                     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
                     */
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mainFragmentView.loadNewsFailure(e.getMessage().toString());
                    }
                });
    }

}
