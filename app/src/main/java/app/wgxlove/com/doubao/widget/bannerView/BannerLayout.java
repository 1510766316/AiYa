package app.wgxlove.com.doubao.widget.bannerView;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import app.wgxlove.com.doubao.R;


/**
 * banner single layout
 */
public class BannerLayout extends FrameLayout {
    private ImageView  mIvBanner;
    private TextView mTvBanner;
    private int textColor= ContextCompat.getColor(getContext(),R.color.color_5a5a5a);
    public BannerLayout(Context context) {
        this(context, null);
    }

    public BannerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(getContext(), R.layout.banner_layout, this);
        mIvBanner = (ImageView)findViewById(R.id.iv_banner);
        mTvBanner = (TextView)findViewById(R.id.tv_banner);
    }

    public void setBannerEntity(BannerInfo info){
        ImageLoader.getInstance().displayImage(info.bannerImageUrl,mIvBanner);
        mTvBanner.setTextColor(textColor);
        mTvBanner.setText(info.bannerTitle);
    }

    public void setTextColor(int color){
        textColor= ContextCompat.getColor(getContext(),color);
    }
}
