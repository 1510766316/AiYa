package app.wgx.com.aiYa.fragments.main.view;

/**
 * Created by wgx on 16-10-18.
 */

public interface MainFragmentView {
    void loadBannerSuccess(String response);
    void loadBannerFailure(String msg);
    void loadNewsTypeSuccess(String response);
    void loadNewsTypeFailure(String msg);
    void loadNewsSuccess(String response);
    void loadNewsFailure(String msg);
}
