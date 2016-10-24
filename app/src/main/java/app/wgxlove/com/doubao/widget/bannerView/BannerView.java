package app.wgxlove.com.doubao.widget.bannerView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.wgxlove.com.doubao.R;
import app.wgxlove.com.doubao.assistTool.MyLogger;

/**
 * Created by imotor on 16-10-24.
 */

public class BannerView extends FrameLayout {

    private List<BannerInfo> mList;
    private BannerPagerAdapter mBannerPagerAdapter;
    private BannerPoint mBannerPoint;
    private ViewPager mViewPager;

    private int mPosition;

    public BannerView(Context context) {
        super(context);
        initValue();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initValue();
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initValue();
    }

    private void initValue(){
        View.inflate(getContext(), R.layout.banner_view, this);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mBannerPoint = (BannerPoint) findViewById(R.id.banner_point);
        mList = new ArrayList<>();

    }

    public void addAllBanner(List<BannerInfo> list) {
        if (null == list || list.size() == 0)
            throw new RuntimeException("Banner list can not empty");
        mList.add(list.get(list.size()-1));
        mList.addAll(list);
        mList.add(list.get(0));
        mBannerPagerAdapter = new BannerPagerAdapter(getContext(), mList);
        mViewPager.setAdapter(mBannerPagerAdapter);
        mViewPager.setCurrentItem(1, false);
        mViewPager.clearOnPageChangeListeners();
        setPagerListener();
    }


    private void setPagerListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                if (positionOffsetPixels == 0.0) {
                    if (position == mList.size() - 1) {
                        mViewPager.setCurrentItem(1, false);
                    } else if (position == 0) {
                        mViewPager.setCurrentItem(mList.size() - 2, false);
                    } else {
                        mViewPager.setCurrentItem(position);
                    }
                }
                mBannerPoint.setPointParams(mList.size()-2, position);
                MyLogger.e("position=="+position);
            }

            @Override
            public void onPageSelected(int position) {
                mPosition=position;


            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 开始自动轮播
     */
    public void startBanner() {

    }


    public void setBannerListener(BannerPagerAdapter.BannerClickListener l) {
        if (null != mBannerPagerAdapter)
            mBannerPagerAdapter.setOnBannerClickListener(l);
    }
}
