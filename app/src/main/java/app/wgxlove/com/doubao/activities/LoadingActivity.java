package app.wgxlove.com.doubao.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import app.wgxlove.com.doubao.R;

/**
 * Create view by wgx
 *
 * @Describe 起始界面
 *
 * Create at 2016/8/16 11:33
 */
public class LoadingActivity extends  BaseActivity {
    //是否是第一次登录
    private boolean isFirstLoggin=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

    }
}
