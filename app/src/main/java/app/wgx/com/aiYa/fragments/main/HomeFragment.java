package app.wgx.com.aiYa.fragments.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.util.HashMap;
import java.util.Map;

import app.wgx.com.aiYa.R;
import app.wgx.com.aiYa.commonTool.HttpConstant;
import app.wgx.com.aiYa.fragments.BaseFragment;
import app.wgx.com.aiYa.fragments.main.presenter.MainFragmentPresenter;
import app.wgx.com.aiYa.fragments.main.view.MainFragmentView;
import app.wgx.com.aiYa.widget.bannerView.BannerView;
import butterknife.BindView;


public class HomeFragment extends BaseFragment implements MainFragmentView {

    MainFragmentPresenter mainFragmentPresenter;
    @BindView(R.id.banner)
    BannerView mBanner;
    @BindView(R.id.recyclerViewList)
    RecyclerView mRecyclerViewList;
    @BindView(R.id.recyclerViewContent)
    RecyclerView mRecyclerViewContent;

    @Override
    protected View initLayout(LayoutInflater inflater,ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void initData() {
        mainFragmentPresenter = new MainFragmentPresenter(this);
        Map<String, String> map = new HashMap<>();
        map.put("userId", "123456");
        mainFragmentPresenter.loadBanner(HttpConstant.HOME_BANNER_URL, map);
    }

    @Override
    protected void setListener() {

    }


    @Override
    public void loadBegin() {

    }

    @Override
    public void loadBannerSuccess(Object response) {

    }

    @Override
    public void loadBannerFailure(String msg) {

    }

    @Override
    public void loadNewsSuccess(Object response) {

    }

    @Override
    public void loadNewsFailure(String msg) {

    }

    @Override
    public void loadFinish() {

    }
}
