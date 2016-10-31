package com.wgxLove.okhttpLibrary;


import android.content.Context;
import android.os.Environment;

import com.wgxLove.okhttpLibrary.utils.MyLog;

import java.io.File;

/**
 * okhttp 基本配置
 */
public class OkHttpConfiguration {
    private static final int DEFAULT_MAX_THREAD_NUMBER = 6;//最大线程数

    private static final int DEFAULT_THREAD_NUMBER = 1;//默认线程数

    private static final String DEFAULT_SAVE_PATH="okHttpDownLoad/";//默认下载存储地址

    /**
     *最大线程数
     */
    private int maxThreadNum;

    /**
     * 线程数
     */
    private int threadNum;

    /**
     * 存储位置
     */
    private String savePath;

    public OkHttpConfiguration() {
        this.maxThreadNum = DEFAULT_MAX_THREAD_NUMBER;
        this.threadNum = DEFAULT_THREAD_NUMBER;
        this.savePath = DEFAULT_SAVE_PATH;
    }

    public void setMaxThreadNum(int maxThreadNum) {
        this.maxThreadNum = maxThreadNum;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public int getMaxThreadNum() {
        return maxThreadNum;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public String getSavePath() {
        return savePath;
    }

    private  File getStorageDir(Context context, String name) {
        File file = new File(context.getExternalFilesDir(
                Environment.MEDIA_MOUNTED), name);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                MyLog.e("Directory not created");
            }
        }
        return file;
    }

}
