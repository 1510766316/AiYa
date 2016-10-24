package app.wgxlove.com.doubao.widget.bannerView;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.wgxlove.com.doubao.R;

/**
 * banner viewpager adapter
 */
public class BannerPagerAdapter extends PagerAdapter {

    private Context mContext;
    List<BannerInfo> mInfo = new ArrayList<>();
    List<BannerLayout> mLayouts = new ArrayList<>();
    private BannerClickListener mBannerClickListener;

    public BannerPagerAdapter(Context context, List<BannerInfo> list) {
        mContext = context;
        mInfo = list;
        getLayout();
    }

    public List<BannerInfo> getList(){
        return mInfo;
    }

    private void getLayout() {
        if (null != mInfo && mInfo.size() > 0)
            for (BannerInfo info : mInfo) {
                BannerLayout bannerLayout = new BannerLayout(mContext);
              //  bannerLayout.setTextColor(R.color.color_666666);
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
        bannerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBannerClickListener != null) {
                    mBannerClickListener.click(position - 1);
                }
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
