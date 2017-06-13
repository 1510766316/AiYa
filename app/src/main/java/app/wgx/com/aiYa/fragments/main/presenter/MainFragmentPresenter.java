package app.wgx.com.aiYa.fragments.main.presenter;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.Map;

import app.wgx.com.aiYa.callback.HttpStringCallBack;
import app.wgx.com.aiYa.fragments.main.model.MainFragmentModel;

/**
 * 主页 4 个fragment presenter
 */
public class MainFragmentPresenter {
    private Context mContext;
    MainFragmentModel mainFragmentModel;

    public MainFragmentPresenter(Context context, MainFragmentModel m) {
        mContext = context;
        mainFragmentModel = m;
    }


    /**
     * 加载bannerView
     *
     * @param url
     * @param map
     */
    public void loadBanner(String url, Map<String, String> map) {
        OkGo.<String>post(url).params(map).tag(mContext).execute(new HttpStringCallBack(mContext) {
            @Override
            public void onSuccess(Response<String> response) {

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
        OkGo.<String>post(url).params(map).tag(mContext).execute(new HttpStringCallBack(mContext) {
            @Override
            public void onSuccess(Response<String> response) {

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
        OkGo.<String>post(url).params(map).tag(mContext).execute(new HttpStringCallBack(mContext) {
            @Override
            public void onSuccess(Response<String> response) {

            }
        });
    }


}
