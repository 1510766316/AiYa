package app.wgxlove.com.doubao;

import android.app.Application;
import android.content.Context;

import app.wgxlove.com.doubao.assistTool.ILoaderConfig;
import app.wgxlove.com.doubao.assistTool.MyLogger;

/**
 * Create view by wgx
 *
 * @Describe 全局应用
 *
 * Create at 2016/8/16 11:34
 */
public class MyApplication extends Application {
    public static Context mContext,mCurrentContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        ILoaderConfig.initImageLoader(mContext,true);
    }
}
