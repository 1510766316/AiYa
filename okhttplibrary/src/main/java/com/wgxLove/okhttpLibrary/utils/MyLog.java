package com.wgxLove.okhttpLibrary.utils;


import com.orhanobut.logger.Logger;

/**
 * 调试工具
 */
public class MyLog {
    private static final boolean flag = true;
    private static final String TAG = "okHttpLibrary";

    {
        if (flag)
            Logger.init(TAG);
    }

    public static void i(String msg) {
        if (flag)
            Logger.i(msg);
    }

    public static void e(String msg) {
        if (flag)
            Logger.e(msg);
    }

    public static void w(String msg) {
        if (flag)
            Logger.w(msg);
    }
}
