package app.wgxlove.com.doubao.assistTool;


import com.orhanobut.logger.Logger;

/**
 * Create view by wgx
 *
 * @Describe debug调试工具
 *
 * Create at 2016/9/10 16:07
 */
public class MyLogger {

    private static boolean debugFlag = false;

    /**
     * @Describe 是否打开日志
     *
     * Create at 2016/9/10 16:19
     */
    public static void openLog(boolean flag) {
        debugFlag = flag;
        if (flag)
            Logger.init(DeviceTool.getAppName());
    }

    public static void w(String msg) {
        if (debugFlag)
            Logger.w(msg);
    }

    public static void e(String msg) {
        if (debugFlag)
            Logger.e(msg);
    }
    public static void i(String msg) {
        if (debugFlag)
            Logger.i(msg);
    }
    public static void d(String msg) {
        if (debugFlag)
            Logger.d(msg);
    }
    public static void v(String msg) {
        if (debugFlag)
            Logger.v(msg);
    }

    public static void json(String jsonString) {
        if (debugFlag)
            Logger.json(jsonString);
    }

    public static void xml(String xmlString) {
        if (debugFlag)
            Logger.xml(xmlString);
    }
}
