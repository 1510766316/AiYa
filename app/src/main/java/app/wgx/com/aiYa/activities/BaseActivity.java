package app.wgx.com.aiYa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.wgx.com.aiYa.MyApplication;
import app.wgx.com.aiYa.service.CheckAppService;
import butterknife.ButterKnife;

/**
 * Create view by wgx
 *
 * @Describe 基类
 * <p>
 * Create at 2016/8/16 11:31
 */
public abstract class BaseActivity extends AppCompatActivity {
    CheckAppService checkAppService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.mCurrentContext = this;
        loadLayout(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        setListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startService(new Intent(BaseActivity.this, CheckAppService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.mCurrentContext = this;
    }

    protected abstract void loadLayout(@Nullable Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void setListener();

    @Override
    protected void onDestroy() {
        stopService(new Intent(BaseActivity.this, CheckAppService.class));
        super.onDestroy();
    }
}
