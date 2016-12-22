package app.wgx.com.aiYa.bean;

/**
 * Created by wgx on 16-12-22.
 *
 * 网络状态
 */

public class NetworkStateInfo {
    private boolean canConnect;
    private boolean canWifi;
    private boolean canMobile;

    public boolean isCanConnect() {
        return canConnect;
    }

    public void setCanConnect(boolean canConnect) {
        this.canConnect = canConnect;
    }

    public boolean isCanWifi() {
        return canWifi;
    }

    public void setCanWifi(boolean canWifi) {
        this.canWifi = canWifi;
    }

    public boolean isCanMobile() {
        return canMobile;
    }

    public void setCanMobile(boolean canMobile) {
        this.canMobile = canMobile;
    }
}
