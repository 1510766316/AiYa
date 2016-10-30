package app.wgx.com.aiYa.fragments.main.presenter;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.wgx.com.aiYa.assistTool.MyLogger;
import app.wgx.com.aiYa.bean.HomeBannerInfo;
import app.wgx.com.aiYa.callback.HttpCallBack;
import app.wgx.com.aiYa.dataBase.SQLiteUtil;
import app.wgx.com.aiYa.fragments.main.view.MainFragmentView;
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
        OkHttpUtils.get()
                .url(url)
                .build().execute(new HttpCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                call.cancel();
                MyLogger.e(e.getMessage().toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                List<HomeBannerInfo.ResultBean> resultBeen;
                resultBeen =( List<HomeBannerInfo.ResultBean> )gson.fromJson(response, new TypeToken<List<HomeBannerInfo.ResultBean>>() {
                }.getRawType());

                for (HomeBannerInfo.ResultBean bean : resultBeen){
                    MyLogger.e(bean.getCreateTime());
                 //   SQLiteUtil.getInstance().saveBanner(bean);
                }
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
