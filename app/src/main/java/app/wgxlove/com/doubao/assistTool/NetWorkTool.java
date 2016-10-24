package app.wgxlove.com.doubao.assistTool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import app.wgxlove.com.doubao.MyApplication;

/**
 * Create view by wgx
 *
 * @Describe 网络工具
 *
 * Create at 2016/9/10 14:46
 */
public class NetWorkTool {

  /**
   * @Describe 检测是否网络可用
   *
   * Create at 2016/9/10 14:49
   */
    public static boolean CheckNetwork() {
        boolean flag = false;
        ConnectivityManager cwjManager = (ConnectivityManager) MyApplication.mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cwjManager != null) {
            NetworkInfo info = cwjManager.getActiveNetworkInfo();
            if (info != null) {
                flag = info.isAvailable();
            }
        }
        return flag;
    }
    /**
     * @Describe 获取当前的网络类型
     *
     * Create at 2016/9/10 16:00
     */
    public static String getCurrentNetType() {
        String type = "";
        ConnectivityManager cm = (ConnectivityManager) MyApplication.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            type = "null";
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            type = "wifi";
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA || subType == TelephonyManager.NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                type = "2g";
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                type = "3g";
            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {
                type = "4g";
            }
        }
        return type;
    }

    /**
     * @Describe 判断是否是3G网络
     *
     * Create at 2016/9/10 16:00
     */
    public static boolean is3rd() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * @Describe 判断是否是WIFI网络
     *
     * Create at 2016/9/10 16:00
     */
    public static boolean isWifi() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

}
