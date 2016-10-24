package app.wgxlove.com.doubao.activities.main.presenter;

import app.wgxlove.com.doubao.activities.main.view.Main;

/**
 * Created by imotor on 16-10-18.
 */

public class MainPresenter {
    private Main mMain;

    public void initMainPresenter(Main main){
        this.mMain = main;
    }

    public void switchFragment(int index){
        mMain.switcherFragment(index);
    }
    public void refreshFragment(int index){
        mMain.refreshFragment(index);
    }
}
