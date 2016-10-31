
package app.wgx.com.aiYa.assistTool;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;


/**
 * @author wgx
 * @ClassName StorageTool
 * @Description
 * @date
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
    public static boolean isMount() {

        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * To get the storage directory. This is return String.
     *
     * @return
     */
    public static String getStorageDirectory() {

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
    public static File getStorageFile() {

        if (!isMount()) {
            return null;
        }
        return Environment.getExternalStorageDirectory();
    }

    public static File getStorageDir(Context context, String name) {
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
    public static float getStorageVolume(int format, int multiple) {

        float unit = DEFAULT_MULTIPLE;
        double total_volume = 0;
        File file = getStorageFile();
        StatFs sFs = new StatFs(file.getPath());
        long blockSize = sFs.getBlockSize();
        int total = sFs.getBlockCount();
        long size = total * blockSize;
        if (multiple == DISPLAY_1024_MULTIPLE) {
            unit = DEFAULT_MULTIPLE;
        } else if (multiple == DISPLAY_1000_MULTIPLE) {
            unit = MULTIPLE_1000;
        }
        switch (format) {
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
        return (float) Math.round(total_volume * KEEP_DECIMAL_POINT_MULTIPLE) / KEEP_DECIMAL_POINT_MULTIPLE;
    }

    /**
     * @return
     */
    public static float getStorageVolume() {

        return getStorageVolume(DEFAULT_DISPLAY_FORMAT,
                DEFAULT_DISPLAY_MULTIPLE);
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath 需要获取剩余空间大小的路径
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getStorageDirectory())) {
            filePath = getStorageDirectory();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 获取系统存储路径
     *
     * @return 系统存储路径
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }
}
