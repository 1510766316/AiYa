package app.wgx.com.aiYa.widget.bannerView;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.wgx.com.aiYa.R;
import app.wgx.com.aiYa.bean.HomeBannerInfo;

/**
 * banner viewpager adapter
 */
public class BannerPagerAdapter extends PagerAdapter {

    private Context mContext;
    List<HomeBannerInfo.ResultBean> mInfo = new ArrayList<>();
    List<BannerLayout> mLayouts = new ArrayList<>();
    private BannerClickListener mBannerClickListener;
    private Handler mHandler;

    public BannerPagerAdapter(Context context, List<HomeBannerInfo.ResultBean> list, Handler handler) {
        mContext = context;
        mInfo = list;
        mHandler = handler;
        getLayout();
    }

    public List<HomeBannerInfo.ResultBean> getList() {
        return mInfo;
    }

    private void getLayout() {
        if (null != mInfo && mInfo.size() > 0)
            for (HomeBannerInfo.ResultBean info : mInfo) {
                BannerLayout bannerLayout = new BannerLayout(mContext);
                  bannerLayout.setTextColor(R.color.color_FFFFFF);
                bannerLayout.setBannerEntity(info);
                mLayouts.add(bannerLayout);
            }
    }


    @Override
    public int getCount() {
        return mInfo.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        BannerLayout bannerLayout = mLayouts.get(position);
        bannerLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler.hasMessages(BannerView.TIMER_MSG))
                            mHandler.removeMessages(BannerView.TIMER_MSG);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        return false;
                    case MotionEvent.ACTION_UP:
                        if (null != mBannerClickListener)
                            mBannerClickListener.click(position);
                        if (!mHandler.hasMessages(BannerView.TIMER_MSG))
                            mHandler.sendEmptyMessageDelayed(BannerView.TIMER_MSG, BannerView.delayTime);
                        break;
                }
                return true;
            }
        });


        container.addView(bannerLayout, 0);
        return mLayouts.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mLayouts.get(position));
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    public void setOnBannerClickListener(BannerClickListener clickListener) {
        this.mBannerClickListener = clickListener;
    }

    public interface BannerClickListener {
        void click(int position);
    }
}
