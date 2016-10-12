package app.wgxlove.com.doubao.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.wgxlove.com.doubao.MyApplication;
import butterknife.ButterKnife;

/**
 * Create view by wgx
 *
 * @Describe 基类
 *
 * Create at 2016/8/16 11:31
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.mCurrentContext=this;
        loadLayout(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.mCurrentContext=this;
    }

    protected abstract void loadLayout(@Nullable Bundle savedInstanceState);
    protected abstract void initView();
    protected abstract void setListener();
}
