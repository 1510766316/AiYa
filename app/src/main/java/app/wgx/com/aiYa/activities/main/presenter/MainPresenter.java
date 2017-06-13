package app.wgx.com.aiYa.activities.main.presenter;

import app.wgx.com.aiYa.activities.main.model.MainModel;
/**
 * Created by imotor on 16-10-18.
 */

public class MainPresenter {
    private MainModel mMain;

    public void initMainPresenter(MainModel main){
        this.mMain = main;
    }

    /**
     * 检测版本
     */
    public void checkAppVersion(){

    }

    public void switchFragment(int index){
        mMain.switcherFragment(index);
    }
    public void refreshFragment(int index){
        mMain.refreshFragment(index);
    }
}
