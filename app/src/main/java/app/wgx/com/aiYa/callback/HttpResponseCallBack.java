package app.wgx.com.aiYa.callback;

/**
 * Created by imotor on 16-10-31.
 */

public interface HttpResponseCallBack {
    void start();
    void progress(long totalSize,float progress);
    void error(String msg);
    void success(String result);
    void finish();
}
