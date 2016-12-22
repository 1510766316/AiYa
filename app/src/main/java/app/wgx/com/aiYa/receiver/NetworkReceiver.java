package app.wgx.com.aiYa.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import app.wgx.com.aiYa.MyApplication;

/**
 * Created by wgx on 16-12-22.
 *
 * 网路状态监听
 */

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction().trim();

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            if (activeNetwork != null) {
                MyApplication.mNetworkStateInfo.setCanConnect(true);
                if (activeNetwork.isConnected()) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        MyApplication.mNetworkStateInfo.setCanWifi(true);
                        MyApplication.mNetworkStateInfo.setCanMobile(false);
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        MyApplication.mNetworkStateInfo.setCanWifi(false);
                        MyApplication.mNetworkStateInfo.setCanMobile(true);
                    }
                } else {
                    MyApplication.mNetworkStateInfo.setCanConnect(false);
                    MyApplication.mNetworkStateInfo.setCanWifi(false);
                    MyApplication.mNetworkStateInfo.setCanMobile(false);
                }
            } else {   // not connected to the internet
                MyApplication.mNetworkStateInfo.setCanConnect(false);
                MyApplication.mNetworkStateInfo.setCanWifi(false);
                MyApplication.mNetworkStateInfo.setCanMobile(false);
            }
        }
    }
}
