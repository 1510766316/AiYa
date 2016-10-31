package com.wgxLove.okhttpLibrary;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by imotor on 16-10-28.
 */

public class OkHttpManager {

    private OkHttpConfiguration configuration;
    private static OkHttpManager mOkManager;
    private ExecutorService mExecutorService;


    public static OkHttpManager getInstance() {
        if (mOkManager == null) {
            synchronized (OkHttpManager.class) {
                if (mOkManager == null) {
                    mOkManager = new OkHttpManager();
                }
            }
        }
        return mOkManager;
    }

    public void init(Context context) {
        init(context, new OkHttpConfiguration());
    }

    public void init(Context context, OkHttpConfiguration config) {
        configuration = config;
        mExecutorService = Executors.newFixedThreadPool(configuration.getMaxThreadNum());
    }

    public void downLoad(){

    }

}
