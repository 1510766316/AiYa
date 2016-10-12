package app.wgxlove.com.doubao.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import app.wgxlove.com.doubao.R;

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

    @Override
    protected void loadLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_loading);
    }

    @Override
    protected void initView() {
        skip();
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
