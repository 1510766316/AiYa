package app.wgx.com.aiYa.widget.bannerView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import app.wgx.com.aiYa.R;

/**
 * Created by imotor on 16-10-24.
 */

public class BannerView extends FrameLayout {

    private List<BannerInfo> mList;
    private BannerPagerAdapter mBannerPagerAdapter;
    private BannerPoint mBannerPoint;
    private ViewPager mViewPager;

    private int mPosition = 0;
    //  private int direction;
    private int num = 1;
    private boolean startFlag = false;

    public static final int TIMER_MSG = 1;
    //  public static final int POINT_MSG = 2;

    public static long delayTime = 1000 * 2;

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

    private void initValue() {
        View.inflate(getContext(), R.layout.banner_view, this);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mBannerPoint = (BannerPoint) findViewById(R.id.banner_point);
        mList = new ArrayList<>();
    }

    public void addAllBanner(List<BannerInfo> list) {
        if (null == list || list.size() == 0)
            throw new RuntimeException("Banner list can not empty");
        mList.add(list.get(list.size() - 1));
        mList.addAll(list);
        mList.add(list.get(0));
        mBannerPagerAdapter = new BannerPagerAdapter(getContext(), mList, handler);
        mViewPager.setAdapter(mBannerPagerAdapter);
        mViewPager.setCurrentItem(1, false);
        mViewPager.clearOnPageChangeListeners();
        setPagerListener();
    }


    private void setPagerListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPosition = position % mList.size();
                if (positionOffsetPixels == 0.0) {
                    if (mPosition == mList.size() - 1) {
                        mViewPager.setCurrentItem(1, false);
                    } else if (mPosition == 0) {
                        mViewPager.setCurrentItem(mList.size() - 2, false);
                    } else {
                        mViewPager.setCurrentItem(mPosition);
                    }
                }
                num = mPosition;
                mBannerPoint.setPointParams(mList.size() - 2, toRealPosition(mPosition));
            }

            @Override
            public void onPageSelected(int position) {
                if (!handler.hasMessages(BannerView.TIMER_MSG))
                    handler.sendEmptyMessageDelayed(BannerView.TIMER_MSG, BannerView.delayTime);
            }


            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 开始自动轮播
     */
    public void startBanner(long time) {
        startFlag=true;
        delayTime = time;
        handler.sendEmptyMessage(TIMER_MSG);
    }

    /**
     * 停止轮播
     */
    public void stopBanner() {
        startFlag=false;
        handler.removeMessages(TIMER_MSG);
    }

    public List<BannerInfo> getBannerList() {
        return mBannerPagerAdapter.getList();
    }

    public void setBannerListener(BannerPagerAdapter.BannerClickListener l) {
        if (null != mBannerPagerAdapter)
            mBannerPagerAdapter.setOnBannerClickListener(l);
    }

    // handler类接收数据
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (!startFlag)
                return;
            if (msg.what == TIMER_MSG) {
                mViewPager.setCurrentItem(num);
                num++;
                handler.sendEmptyMessageDelayed(TIMER_MSG, delayTime);
            }

        }
    };

    /**
     * 返回真实的位置
     *
     * @param position
     * @return
     */
    private int toRealPosition(int position) {
        int realPosition;
        realPosition = (position - 1) % mList.size();
        if (realPosition < 0)
            realPosition += mList.size();
        return realPosition;
    }

}
