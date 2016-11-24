package app.wgx.com.aiYa.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.wgx.com.aiYa.MyApplication;
import app.wgx.com.aiYa.service.CheckAppService;
import app.wgx.com.aiYa.service.CheckAppService.CheckBinder;
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

    /**
     * Dispatch onStart() to all fragments.  Ensure any created loaders are
     * now started.
     */
    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(BaseActivity.this, CheckAppService.class), serviceConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            checkAppService = ((CheckBinder) service).getService();
            if (null != checkAppService)
                checkAppService.showNote();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            checkAppService = null;
        }
    };

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
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
