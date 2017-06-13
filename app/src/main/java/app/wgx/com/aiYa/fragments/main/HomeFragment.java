package app.wgx.com.aiYa.fragments.main;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.wgx.com.aiYa.R;
import app.wgx.com.aiYa.assistTool.JsonTool;
import app.wgx.com.aiYa.assistTool.NoticeTool;
import app.wgx.com.aiYa.bean.HomeBannerInfo;
import app.wgx.com.aiYa.commonTool.HttpConstant;
import app.wgx.com.aiYa.fragments.BaseFragment;
import app.wgx.com.aiYa.fragments.main.presenter.MainFragmentPresenter;
import app.wgx.com.aiYa.fragments.main.model.MainFragmentModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements MainFragmentModel, XBanner.XBannerAdapter {

    MainFragmentPresenter mainFragmentPresenter;
    @BindView(R.id.recyclerViewContent)
    RecyclerView mRecyclerViewContent;
    @BindView(R.id.banner)
    XBanner mBanner;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;

    private HomeBannerInfo mHomeBannerInfo;
    private int page = 1;
    private int pageSize = 10;

    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,mView);
        initData();
        setListener();
        return mView;
    }

    private void initData() {
        mainFragmentPresenter = new MainFragmentPresenter(mContext,this);
        Map<String, String> map = new HashMap<>();
        map.put("key", HttpConstant.API_KEY);
        mainFragmentPresenter.loadBanner(HttpConstant.HOME_BANNER_URL, map);
    }

    private void setListener() {
        mBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {

            }
        });
    }

    @Override
    public void loadBannerSuccess(String response) {
        mHomeBannerInfo = JsonTool.jsonToBean(response, HomeBannerInfo.class);
        mBanner.setPageTransformer(Transformer.Accordion);
        if (null != mHomeBannerInfo.getResult() && mHomeBannerInfo.getResult().size() > 0) {
            List<String> imgList = new ArrayList<>();
            List<String> tipsList = new ArrayList<>();
            for (HomeBannerInfo.ResultBean img : mHomeBannerInfo.getResult())
                imgList.add(img.getImageUrl());
            for (HomeBannerInfo.ResultBean tips : mHomeBannerInfo.getResult())
                tipsList.add(tips.getTitle());
            mBanner.setData(imgList, tipsList);
        }
        mBanner.setmAdapter(this);
        Map<String, String> map = new HashMap<>();
        mainFragmentPresenter.loadNewsType(HttpConstant.HOME_NEWSTYPE_URL, map);

    }

    @Override
    public void loadBannerFailure(String msg) {
        NoticeTool.showToast(getContext(), msg, 2000);
    }

    @Override
    public void loadNewsTypeSuccess(String response) {
        Map<String, String> map = new HashMap<>();
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
    public void onResume() {
        super.onResume();
        mBanner.startAutoPlay();
    }
    @Override
    public void onPause() {
        super.onPause();
        mBanner.stopAutoPlay();
    }

    @Override
    public void loadBanner(XBanner banner, View view, int position) {
        Glide.with(getActivity()).load(mHomeBannerInfo.getResult().get(position).getImageUrl()).placeholder(new ColorDrawable(getContext().getResources().getColor(R.color.color_FFFFFF)) {
        }).error(new ColorDrawable(getContext().getResources().getColor(R.color.color_FFFFFF))).into((ImageView) view);
    }

}
