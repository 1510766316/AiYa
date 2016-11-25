package app.wgx.com.aiYa.fragments.main.view;

import okhttp3.Call;

/**
 * Created by imotor on 16-10-18.
 */

public interface MainFragmentView {
    void loadBegin();
    void loadBannerSuccess(Object response);
    void loadBannerFailure(String msg);
    void loadNewsSuccess(Object response);
    void loadNewsFailure(String msg);
    void loadFinish();
}
