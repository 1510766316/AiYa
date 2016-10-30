package app.wgx.com.aiYa.fragments.main.presenter;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import app.wgx.com.aiYa.fragments.main.view.MainFragmentView;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * 主页 4 个fragment presenter
 */
public class MainFragmentPresenter {
    MainFragmentView mainFragmentView;

    public void initPresenter(MainFragmentView m){
        this.mainFragmentView=m;
    }

    public void loadData(String url, Map<String,String> map){
        OkHttpUtils.get()
                .url(url)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mainFragmentView.error(call,e.getMessage().toString());
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        mainFragmentView.progress(total,progress);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mainFragmentView.success(response.toString());
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
