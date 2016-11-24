package app.wgx.com.aiYa.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import app.wgx.com.aiYa.R;
import app.wgx.com.aiYa.commonTool.HttpConstant;
import app.wgx.com.aiYa.fragments.BaseFragment;
import app.wgx.com.aiYa.fragments.main.presenter.MainFragmentPresenter;
import app.wgx.com.aiYa.fragments.main.view.MainFragmentView;
import okhttp3.Call;


public class HomeFragment extends BaseFragment implements MainFragmentView {

    MainFragmentPresenter mainFragmentPresenter;

    @Override
    protected View initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void initView(View view) {
        mainFragmentPresenter = new MainFragmentPresenter(this);
        Map<String, String> map = new HashMap<>();
        map.put("userId", "123456");
        mainFragmentPresenter.loadBanner(HttpConstant.test, map);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void start() {

    }

    @Override
    public void progress(long totalSize, float progress) {

    }

    @Override
    public void error(Call call, String msg) {

    }

    @Override
    public void success(String result) {

    }

    @Override
    public void finish() {

    }
}
