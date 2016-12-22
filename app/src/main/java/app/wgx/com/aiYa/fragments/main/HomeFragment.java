package app.wgx.com.aiYa.fragments.main;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.HashMap;
import java.util.Map;

import app.wgx.com.aiYa.R;
import app.wgx.com.aiYa.assistTool.JsonTool;
import app.wgx.com.aiYa.assistTool.NoticeTool;
import app.wgx.com.aiYa.bean.HomeBannerInfo;
import app.wgx.com.aiYa.commonTool.HttpConstant;
import app.wgx.com.aiYa.dataBase.SQLiteUtil;
import app.wgx.com.aiYa.fragments.BaseFragment;
import app.wgx.com.aiYa.fragments.main.presenter.MainFragmentPresenter;
import app.wgx.com.aiYa.fragments.main.view.MainFragmentView;
import butterknife.BindView;

public class HomeFragment extends BaseFragment implements MainFragmentView {

    MainFragmentPresenter mainFragmentPresenter;
    @BindView(R.id.recyclerViewContent)
    RecyclerView mRecyclerViewContent;
    @BindView(R.id.banner)
    XBanner mBanner;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;

    private int page = 1;
    private int pageSize = 10;

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void initData() {
        mainFragmentPresenter = new MainFragmentPresenter(this);
        Map<String, String> map = new HashMap<>();
        map.put("key", HttpConstant.API_KEY);
        mainFragmentPresenter.loadBanner(HttpConstant.HOME_BANNER_URL, map);
    }

    @Override
    protected void setListener() {

    }


    @Override
    public void loadBegin() {

    }

    @Override
    public void loadBannerSuccess(String response) {
        HomeBannerInfo info = JsonTool.jsonToBean(response, HomeBannerInfo.class);
        SQLiteUtil.getInstance().saveBanner(info.getResult());
        //   if (null != info.getResult() && info.getResult().size()>0)
        mBanner.setPageTransformer(Transformer.Accordion);
        Map<String, String> map = new HashMap<>();
        map.put("key", HttpConstant.API_KEY);
        mainFragmentPresenter.loadNewsType(HttpConstant.HOME_NEWSTYPE_URL, map);

    }

    @Override
    public void loadBannerFailure(String msg) {
        NoticeTool.showToast(getContext(), msg, 2000);
    }

    @Override
    public void loadNewsTypeSuccess(String response) {
        Map<String, String> map = new HashMap<>();
        map.put("key", HttpConstant.API_KEY);
        map.put("page", page + "");
        map.put("pageSize", pageSize + "");
        mainFragmentPresenter.loadNews(HttpConstant.HOME_NEWS_URL, map);
    }

    @Override
    public void loadNewsTypeFailure(String msg) {

    }

    @Override
    public void loadNewsSuccess(String response) {

    }

    @Override
    public void loadNewsFailure(String msg) {
        NoticeTool.showToast(getContext(), msg, 2000);
    }

    @Override
    public void loadFinish() {

    }

}
