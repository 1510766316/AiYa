package app.wgxlove.com.doubao.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import app.wgxlove.com.doubao.R;
import app.wgxlove.com.doubao.activities.main.MainActivity;
import app.wgxlove.com.doubao.widget.bannerView.BannerInfo;
import app.wgxlove.com.doubao.widget.bannerView.BannerView;

/**
 * Create view by wgx
 *
 * @Describe 起始界面
 * <p>
 * Create at 2016/8/16 11:33
 */
public class LoadingActivity extends BaseActivity {
    //是否是第一次登录
    private boolean isFirstLoggin = false;

    private BannerView mBannerView;

    @Override
    protected void loadLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_loading);
    }

    @Override
    protected void initView() {

        mBannerView= (BannerView) findViewById(R.id.banner_view);

       // skip();
        List<BannerInfo> infos=new ArrayList<>();
        BannerInfo info=new BannerInfo();
        info.bannerImageUrl="http://pic3.bbzhi.com/fengjingbizhi/gaoqingkuanpingfengguangsheyingps/show_fengjingta_281299_11.jpg";
        info.bannerTitle="风景８８８８８";
        infos.add(info);
        BannerInfo info1=new BannerInfo();
        info1.bannerImageUrl="http://e.hiphotos.baidu.com/image/h%3D200/sign=1e8feb8facd3fd1f2909a53a004f25ce/c995d143ad4bd113eff4cf935eafa40f4bfb0551.jpg";
        info1.bannerTitle="nnafsnfn";
        infos.add(info1);
        BannerInfo info2=new BannerInfo();
        info2.bannerImageUrl="http://www.bz55.com/uploads1/allimg/120312/1_120312100435_8.jpg";
        info2.bannerTitle="风景123123";
        infos.add(info2);
        BannerInfo info3=new BannerInfo();
        info3.bannerImageUrl="http://b.hiphotos.baidu.com/image/h%3D200/sign=9b711189efc4b7452b94b016fffd1e78/3c6d55fbb2fb4316fc06edda24a4462309f7d371.jpg";
        info3.bannerTitle="啊护发护肤";
        infos.add(info3);

        mBannerView.addAllBanner(infos);
    }

    @Override
    protected void setListener() {

    }

    private void skip() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                LoadingActivity.this.finish();
            }
        }).start();
    }
}
