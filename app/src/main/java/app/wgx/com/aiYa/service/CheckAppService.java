package app.wgx.com.aiYa.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.List;

import app.wgx.com.aiYa.R;
import app.wgx.com.aiYa.activities.main.MainActivity;

/**
 * Create view by wgx
 *
 * @Describe 检测App更新和连接状态
 * <p>
 * Create at 2016/9/10 14:44
 */
public class CheckAppService extends Service {

    private static final int NOTIFICATION_ID = 1; // 如果id设置为0,会导致不能设置为前台service
    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void showNote(){
        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent, 0);
        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
       // builder.setTicker("前台服务开始");
        builder.setContentTitle("前台服务");
        builder.setContentText("使这个服务运行在前台.");
        Notification notification = builder.build();

        startForeground(NOTIFICATION_ID, notification);
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
     * <p>
     * Create at 2016/9/10 15:34
     */
    public interface checkApp {
        void connectFailure();//连接服务器失败
    }
}
