package app.wgx.com.aiYa.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.wgx.com.aiYa.assistTool.MyLogger;
import app.wgx.com.aiYa.service.CheckAppService;

/**
 * Create view by wgx
 *
 * @Describe 基类
 * <p>
 * Create at 2016/8/16 11:31
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context mCurrentContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentContext = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyLogger.i("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentContext = this;
    }

    @Override
    protected void onDestroy() {
        MyLogger.i("onDestroy");
        super.onDestroy();
    }
}
