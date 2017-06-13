package app.wgx.com.aiYa.assistTool;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import app.wgx.com.aiYa.MyApplication;

/**
 * Create view by wgx
 *
 * @Describe 设备工具类
 * <p>
 * Create at 2016/9/10 15:59
 */
public class DeviceTool {


    /**
     * @Describe 获取本机IP地址
     * <p>
     * Create at 2016/9/10 16:00
     */
    public static String getLocalIP(Context context) {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }


    /**
     * @Describe 获取本机MAC地址
     * <p>
     * Create at 2016/9/10 16:01
     */
    public static String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String address = "";
        if (info != null) {
            address = info.getMacAddress();
        }
        return address == null ? "" : address;
    }

    /**
     * @Describe 获取当前app的版本号
     * <p>
     * Create at 2016/9/10 16:01
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Describe 获取项目名称AppName
     * <p>
     * Create at 2016/9/10 16:01
     */
    public static String getAppName(Context context) {
        PackageManager pm = context.getPackageManager();
        String appName = context.getApplicationInfo().loadLabel(pm).toString();
        return appName;
    }
}
