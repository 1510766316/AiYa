package app.wgx.com.aiYa.assistTool;

import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

public class NetWorkTool {

    private static final String ETHERNET = "eth0";
    private static final String WLAN = "wlan0";

    private static final String DNS1 = "[net.dns1]";
    private static final String DNS2 = "[net.dns2]";
    private static final String ETHERNET_GATEWAY = "[dhcp.eth0.gateway]";
    private static final String WLAN_GATEWAY = "[dhcp.wlan0.gateway]";
    private static final String ETHERNET_MASK = "[dhcp.eth0.mask]";
    private static final String WLAN_MASK = "[dhcp.wlan0.mask]";

    /**
     * Judge whether current network is available
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cwjManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cwjManager.getActiveNetworkInfo();
        if (info != null) {
            return info.isAvailable();
        }
        return false;
    }

    /**
     * Get wlan mac address
     *
     * @return
     */
    public static String getWlanMacAddress() {
        return getMacAddress(WLAN);
    }

    /**
     * Get ethernet mac address
     *
     * @return
     */
    public static String getEthernetMacAddress() {
        return getMacAddress(ETHERNET);
    }

    /**
     * Get mac address
     *
     * @param interfaceName
     * @return
     */
    private static String getMacAddress(String interfaceName) {
        try {
            List<NetworkInterface> interfaces = Collections
                    .list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (interfaceName != null) {
                    if (!intf.getName().equalsIgnoreCase(interfaceName))
                        continue;
                }
                byte[] mac;
                mac = intf.getHardwareAddress();
                if (mac == null)
                    return "";
                StringBuilder buf = new StringBuilder();
                for (int idx = 0; idx < mac.length; idx++)
                    buf.append(String.format("%02X:", mac[idx]));
                if (buf.length() > 0)
                    buf.deleteCharAt(buf.length() - 1);
                return buf.toString();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Get dns1
     *
     * @return
     */
    public static String getDNS1() {
        return getPropInfo(DNS1);
    }

    /**
     * Get dns2
     *
     * @return
     */
    public static String getDNS2() {
        return getPropInfo(DNS2);
    }

    /**
     * Get ethernet gateway
     *
     * @return
     */
    public static String getEthernetGateway() {
        return getPropInfo(ETHERNET_GATEWAY);
    }

    /**
     * Get wlan gateway
     *
     * @return
     */
    public static String getWlanGateway() {
        return getPropInfo(WLAN_GATEWAY);
    }

    /**
     * Get ethernet mask
     *
     * @return
     */
    public static String getEthernetMask() {
        return getPropInfo(ETHERNET_MASK);
    }

    /**
     * Get wlan mask
     *
     * @return
     */
    public static String getWlanMask() {
        return getPropInfo(WLAN_MASK);
    }

    /**
     * Get prop information by different interface name
     *
     * @param interfaceName
     * @return
     */
    private static String getPropInfo(String interfaceName) {
        String re = "";
        try {
            Process process = Runtime.getRuntime().exec("getprop");
            Properties pr = new Properties();
            pr.load(process.getInputStream());
            re = pr.getProperty(interfaceName, "");
            if (!StringTool.isEmpty(re) && re.length() > 6) {
                re = re.substring(1, re.length() - 1);
                return re;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return re;
    }

    /**
     * @param context
     * @return boolean
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager != null) {
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                int networkInfoType = networkInfo.getType();
                if (networkInfoType == ConnectivityManager.TYPE_WIFI || networkInfoType == ConnectivityManager.TYPE_ETHERNET) {
                    return networkInfo.isConnected();
                }
            }
        }
        return false;
    }

    /**
     * @param context
     * @return boolean
     */
    public static boolean isMobileConnected(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager != null) {
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                int networkInfoType = networkInfo.getType();
                if (networkInfoType == ConnectivityManager.TYPE_MOBILE) {
                    return networkInfo.isConnected();
                }
            }
        }
        return false;
    }


    /**
     * open Settings-Activity
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

}