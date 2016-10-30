package app.wgx.com.aiYa.fragments.main.view;

import okhttp3.Call;

/**
 * Created by imotor on 16-10-18.
 */

public interface MainFragmentView {
    void refreshView(int index);
    void showFailureView();
    void start();
    void progress(long totalSize,float progress);
    void error(Call call,String msg);
    void success(String result);
    void finish();
}
