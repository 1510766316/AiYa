package app.wgxlove.com.doubao.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.wgxlove.com.doubao.MyApplication;

/**
 * Create view by wgx
 *
 * @Describe 基类
 *
 * Create at 2016/8/16 11:31
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.mCurrentContext=this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.mCurrentContext=this;
    }
}
