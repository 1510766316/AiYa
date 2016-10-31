package app.wgx.com.aiYa.assistTool;

/**
 * @author wgx
 * @ClassName VariableTool
 * @Description 常量
 */
public class VariableTool {
    public static final String ROOT_DIRECTORY = StorageTool.getStorageDirectory()+"/aiYa/"; // 程序储存器上的根目录
    public static final String DB_SAVE_DIRECTORY = ROOT_DIRECTORY + "/db/";//数据库缓存目录
    public static final String IMAGE_SAVE_DIRECTORY = ROOT_DIRECTORY + "/image/"; // 图片缓存目录
    public static final String FILE_SAVE_DIRECTORY = ROOT_DIRECTORY + "/data/";//文件存储目录
    public static final String LOG_SAVE_DIRECTORY = ROOT_DIRECTORY + "/log/";//日志缓存目录
    public static final String APK_SAVE_DIRECTORY = ROOT_DIRECTORY + "/updateAPK/";//apk更新缓存目录
    public static final String apkSave = APK_SAVE_DIRECTORY + "douBao.apk"; // ａｐｋ完整路径名
    /****************************************
     * sqlite数据库
     ********************************************************************/

    public static final String TAB_HMOE_BANNER = "tab_home_banner";//HomeFragment bannerView 表
    /************************
     * SharedPreferences 数据保存
     *************************/
    public static final String TAB_USER = "tab_user"; // 用户 数据库表
    public static final String TAB_MSG = "tab_msg"; // 消息 数据库表
}
