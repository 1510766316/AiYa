package app.wgxlove.com.doubao.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Create view by wgx
 *
 * @Describe 检测App更新和连接状态
 * <p>
 * Create at 2016/9/10 14:44
 */
public class CheckAppService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * @Describe 判断是否
     * <p>
     * Create at 2016/9/10 14:42
     */
    public static boolean isServiceRunning(Context context) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (serviceList == null || serviceList.size() == 0) return false;
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(CheckAppService.class.getName())) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * @Describe 检测App状态
     *
     * Create at 2016/9/10 15:34
     */
    public interface checkApp {
        void connectFailure();//连接服务器失败
    }

    /**
     * @Describe 更新App
     *
     * Create at 2016/9/10 15:34
     */
    public interface downApp {
        void beginDown();

        void downing(long progress);

        void finish();

        void failure();
    }
}
