
package app.wgxlove.com.doubao.assistTool;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import com.orhanobut.logger.Logger;


/** 
 * @ClassName StorageTool
 * @Description  �洢������
 * @author wgx
 * @date  2016��3��30�� ����12:03:12  
 */
public class StorageTool {

	private final static int DEFAULT_DISPLAY_FORMAT = 0;  //byte

	public final static int DISPLAY_BYTE_FORMAT = 0; // byte

	public final static int DISPLAY_KB_FORMAT = 1; // KB

	public final static int DISPLAY_MB_FORMAT = 2; // MB

	public final static int DISPLAY_GB_FORMAT = 3; // GB

	public final static int DISPLAY_TB_FORMAT = 4; // TB

	private final static float DEFAULT_MULTIPLE = 1024.0f; // 1024 unit

	private final static float MULTIPLE_1000 = 1000.0f;   // 1000 unit

	private final static int DEFAULT_DISPLAY_MULTIPLE = 0; // defualt 1024 unit

	public final static int DISPLAY_1000_MULTIPLE = 1; // 1000 unit

	public final static int DISPLAY_1024_MULTIPLE = 0; // 1024 unit
	
	private final static int KEEP_DECIMAL_POINT_MULTIPLE = 100;

	/**
	 * To judge the storage is mounted.
	 * 
	 * @return
	 */
	public static boolean isMount()
	{

		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/**
	 * To get the storage directory. This is return String.
	 * 
	 * @return
	 */
	public static String getStorageDirectory()
	{

		File file = getStorageFile();
		if (file == null) {
			return null;
		}
		return file.getAbsolutePath();
	}

	/**
	 * To get the storage directory This is return File.
	 * 
	 * @return
	 */
	public static File getStorageFile()
	{

		if (!isMount()) {
			return null;
		}
		return Environment.getExternalStorageDirectory();
	}

	public static File getStorageDir(Context context,String name) {
		File file = new File(context.getExternalFilesDir(
				Environment.MEDIA_MOUNTED), name);
		if (!file.exists()) {
			if (!file.mkdirs()) {
				MyLogger.e("Directory not created");
			}
		}
		return file;
	}
	/**
	 * To get the storage total volume.
	 * 
	 * @param format
	 * @param multiple 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static float getStorageVolume(int format, int multiple)
	{

		float unit = DEFAULT_MULTIPLE;
		double total_volume = 0;
		File file = getStorageFile();
		StatFs sFs = new StatFs(file.getPath());
		long blockSize = sFs.getBlockSize();
		int total = sFs.getBlockCount();
		long size = total * blockSize;
		if (multiple == DISPLAY_1024_MULTIPLE) {
			unit = DEFAULT_MULTIPLE;
		}
		else if (multiple == DISPLAY_1000_MULTIPLE) {
			unit = MULTIPLE_1000;
		}
		switch (format)
		{
			case DISPLAY_BYTE_FORMAT:
				total_volume = size;
				break;

			case DISPLAY_KB_FORMAT:
				total_volume = size / unit;
				break;

			case DISPLAY_MB_FORMAT:
				total_volume = size / unit / unit;
				break;

			case DISPLAY_GB_FORMAT:
				total_volume = size / unit / unit / unit;
				break;

			case DISPLAY_TB_FORMAT:
				total_volume = size / unit / unit / unit / unit;
				break;
		}
		return (float)Math.round(total_volume*KEEP_DECIMAL_POINT_MULTIPLE)/KEEP_DECIMAL_POINT_MULTIPLE;
	}

	/**
	 * @return
	 */
	public static float getStorageVolume()
	{

		return getStorageVolume(DEFAULT_DISPLAY_FORMAT,
				DEFAULT_DISPLAY_MULTIPLE);
	}

	/**
	 * To get the storage avialable volume.
	 * 
	 * @param format
	 * @param multiple
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static float getUsableVolumn(int format, int multiple)
	{

		float unit = DEFAULT_MULTIPLE;
		double avialable_volume = 0;
		File file = getStorageFile();
		StatFs sFs = new StatFs(file.getPath());
		long blockSize = sFs.getBlockSize();
		int avialable_blocks = sFs.getAvailableBlocks();
		long avialable = avialable_blocks * blockSize;
		if (multiple == DISPLAY_1024_MULTIPLE) {
			unit = DEFAULT_MULTIPLE;
		}
		else if (multiple == DISPLAY_1000_MULTIPLE) {
			unit = MULTIPLE_1000;
		}
		switch (format)
		{
			case DISPLAY_BYTE_FORMAT:
				avialable_volume = avialable;
				break;

			case DISPLAY_KB_FORMAT:
				avialable_volume = avialable / unit;
				break;

			case DISPLAY_MB_FORMAT:
				avialable_volume = avialable / unit / unit;
				break;

			case DISPLAY_GB_FORMAT:
				avialable_volume = avialable / unit / unit / unit;
				break;

			case DISPLAY_TB_FORMAT:
				avialable_volume = avialable / unit / unit / unit / unit;
				break;
		}
		return (float)Math.round(avialable_volume*KEEP_DECIMAL_POINT_MULTIPLE)/KEEP_DECIMAL_POINT_MULTIPLE;
	}

	/**
	 * @return
	 */
	public static float getUsableVolumn()
	{

		return getUsableVolumn(DEFAULT_DISPLAY_FORMAT, DEFAULT_DISPLAY_MULTIPLE);
	}
}
