package app.wgx.com.aiYa.assistTool;

import android.os.Environment;

/**
 * @ClassName VariableTool
 * @Description 常量
 * @author wgx
 */
public class VariableTool {
	public static final String ROOT_DIRECTORY = "douBao"; // 程序储存器上的根目录
	public static final String IMAGE_ACHE_DIRECTORY = ROOT_DIRECTORY + "/Image"; // 图片缓存目录
	public static final String FILE_SAVE_DIRECTORY = ROOT_DIRECTORY + "/data";
	public static final String savePath = Environment.getExternalStorageDirectory().getPath() + "/updateAPK/";
	// apk保存到SD卡的路径
	public static final String apkSave = savePath + "douBao.apk"; // 完整路径名
	/**************************************** sqlite数据库 ********************************************************************/

	public static final String TAB_HMOE_BANNER="tab_home_banner";//HomeFragment bannerView 表
	/************************SharedPreferences 数据保存*************************/
	public static final String TAB_USER = "tab_user"; // 用户 数据库表
	public static final String TAB_MSG = "tab_msg"; // 消息 数据库表
}
