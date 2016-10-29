package app.wgx.com.aiYa.assistTool;

import android.os.Environment;

/**
 * @ClassName VariableTool
 * @Description 常量
 * @author wgx
 */
public class VariableTool {
	public static final String ROOT_DERECTORY = "douBao"; // 程序储存器上的根目录
	public static final String IMAGE_ACHE_DIRECTORY = ROOT_DERECTORY + "/Image"; // 图片缓存目录
	public static final String FILE_SAVE_DIRECTORY = ROOT_DERECTORY + "/data";
	public static final String savePath = Environment.getExternalStorageDirectory().getPath() + "/updateAPK/";
	// apk保存到SD卡的路径
	public static final String apkSave = savePath + "douBao.apk"; // 完整路径名
	/**************************************** sqlite数据库 ********************************************************************/
	public static final String PUSHLIST_DB = "pushlist_db"; // 推送数据库
	public static final boolean SQLITE_LIBRARY_IS_LOCAL = false; // 是否是存储器里的数据库
	public static final String LOCAL_DB_NAME = "dbao_new.db"; // 新数据库名称
	/************************SharedPreferences 数据保存*************************/
	public static final String TAB_USER = "tab_user"; // 用户 数据库表
	public static final String TAB_MSG = "tab_msg"; // 消息 数据库表
}
