package app.wgx.com.aiYa.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import app.wgx.com.aiYa.R;
import app.wgx.com.aiYa.activities.main.MainActivity;

/**
 * Create view by wgx
 *
 * @Describe 起始界面
 * <p>
 * Create at 2016/8/16 11:33
 */
public class LoadingActivity extends BaseActivity {
    private boolean isFirstLoggin = false;
    private int[] images = {R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R.mipmap.p4};

    @Override
    protected void loadLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_loading);
    }

    @Override
    protected void initView() {

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
