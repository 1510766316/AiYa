package app.wgx.com.aiYa.bean;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by wgx on 16-12-22.
 * <p>
 * 网络状态
 */

public class NetworkStateInfo {
    private static final AtomicReference<NetworkStateInfo> mInstance = new AtomicReference<NetworkStateInfo>();
    private boolean isConnected;
    private boolean isWifi;
    private boolean isMobile;

    public static NetworkStateInfo getInstance() {
        for (; ; ) {
            NetworkStateInfo current = mInstance.get();
            if (null != current) {
                return current;
            }
            current = new NetworkStateInfo();
            if (mInstance.compareAndSet(null, current)) {
                return current;
            }
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public boolean isWifi() {
        return isWifi;
    }

    public void setWifi(boolean isWifi) {
        this.isWifi = isWifi;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public void setMobile(boolean isMobile) {
        this.isMobile = isMobile;
    }
}
