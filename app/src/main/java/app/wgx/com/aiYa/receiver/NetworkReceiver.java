package app.wgx.com.aiYa.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import app.wgx.com.aiYa.MyApplication;
import app.wgx.com.aiYa.bean.NetworkStateInfo;

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
                NetworkStateInfo.getInstance().setConnected(true);
                if (activeNetwork.isConnected()) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        NetworkStateInfo.getInstance().setMobile(false);
                        NetworkStateInfo.getInstance().setWifi(true);
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        NetworkStateInfo.getInstance().setMobile(true);
                        NetworkStateInfo.getInstance().setWifi(false);
                    }
                } else {
                    NetworkStateInfo.getInstance().setConnected(false);
                    NetworkStateInfo.getInstance().setMobile(false);
                    NetworkStateInfo.getInstance().setWifi(false);
                }
            } else {   // not connected to the internet
                NetworkStateInfo.getInstance().setConnected(false);
                NetworkStateInfo.getInstance().setMobile(false);
                NetworkStateInfo.getInstance().setWifi(false);
            }
        }
    }
}
