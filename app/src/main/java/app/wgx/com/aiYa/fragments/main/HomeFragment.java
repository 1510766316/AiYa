package app.wgx.com.aiYa.fragments.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

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
import app.wgx.com.aiYa.widget.bannerView.BannerPagerAdapter;
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

    private int page=1;
    private int pageSize=10;
    @Override
    protected View initLayout(LayoutInflater inflater,ViewGroup container) {
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
        HomeBannerInfo info= JsonTool.jsonToBean(response,HomeBannerInfo.class);
        SQLiteUtil.getInstance().saveBanner(info.getResult());
        if (null != info.getResult() && info.getResult().size()>0)
            mBanner.addAllBanner(info.getResult());
        mBanner.startBanner(2000);
        mBanner.setBannerListener(new BannerPagerAdapter.BannerClickListener() {
            @Override
            public void click(int position) {
                NoticeTool.showToast(getContext(),mBanner.getBannerList().get(position).getTitle(),2000);
            }
        });
        Map<String, String> map = new HashMap<>();
        map.put("key", HttpConstant.API_KEY);
        mainFragmentPresenter.loadNewsType(HttpConstant.HOME_NEWSTYPE_URL, map);

    }

    @Override
    public void loadBannerFailure(String msg) {
        NoticeTool.showToast(getContext(),msg,2000);
    }

    @Override
    public void loadNewsTypeSuccess(String response) {
        Map<String, String> map = new HashMap<>();
        map.put("key", HttpConstant.API_KEY);
        map.put("page", page+"");
        map.put("pageSize", pageSize+"");
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
        NoticeTool.showToast(getContext(),msg,2000);
    }

    @Override
    public void loadFinish() {

    }
}
