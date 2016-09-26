
package app.wgxlove.com.doubao.assistTool;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import app.wgxlove.com.doubao.MyApplication;

/*
 * This class is used to manage the file and the file operations.
 */

public class FileTool {

	public final static int DEFAULT_FILE_OPERATE_MODE = 0;

	public final static int IGNORE_NOT_RECREATE_MODE = 1;

	public final static int IGNORE_AND_RECREATE_MODE = 2;

	public final static int NOT_IGNORE_RECREATE_MODE = 3;

	private final static boolean DEFAULT_IGNORE_STYLE = false;

	private final static boolean DEFAULT_AUTO_CREATE_DIRECTORY = true;

	// ============== create file and dirctory==================
	/**
	 * @param file
	 * @param 'ignore'
	 *            if ignore is true,file is simple. if ignore is false,file is
	 *            added sdcard dirctory.
	 * @throws IOException
	 */
	public static void createFile(File file, int mode) throws IOException {

		if (file == null || StringTool.isEmpty(file.getAbsolutePath()) || file.isDirectory()) {
			return;
		}
		if (mode == IGNORE_NOT_RECREATE_MODE || mode == IGNORE_AND_RECREATE_MODE) {
			file = new File(StorageTool.getStorageFile(), file.getAbsolutePath());
		}
		if (mode == IGNORE_AND_RECREATE_MODE) {
			deleteFile(file);
		}
		file.createNewFile();
	}

	/**
	 * @param filePath
	 * @param mode
	 * @throws IOException
	 */
	public static void createFile(String filePath, int mode) throws IOException {

		createFile(new File(filePath), mode);
	}

	/**
	 * @param filePath
	 * @throws IOException
	 */
	public static void createFile(File filePath) throws IOException {

		createFile(filePath, DEFAULT_FILE_OPERATE_MODE);
	}

	/**
	 * @param filePath
	 * @throws IOException
	 */
	public static void createFile(String filePath) throws IOException {

		createFile(new File(filePath));
	}

	/**
	 * To create a folder or more.
	 * 
	 * @param folder
	 * @param mode
	 */
	public static void createFolder(File folder, int mode) {

		if (folder == null || StringTool.isEmpty(folder.getAbsolutePath())) {
			return;
		}
		if (folder.isFile()) {
			return;
		}
		if (mode == IGNORE_NOT_RECREATE_MODE || mode == IGNORE_AND_RECREATE_MODE) {
			folder = new File(StorageTool.getStorageFile(), folder.getAbsolutePath());
		}
		if (mode == IGNORE_AND_RECREATE_MODE) {
			deleteFolder(folder);
		}
		folder.mkdirs();

	}

	/**
	 * @param folder
	 * @param mode
	 */
	public static void createFolder(String folder, int mode) {

		createFolder(new File(folder), mode);
	}

	/**
	 * @param folder
	 */
	public static void createFolder(File folder) {

		createFolder(folder, DEFAULT_FILE_OPERATE_MODE);
	}

	/**
	 * @param folder
	 */
	public static void createFolder(String folder) {

		createFolder(new File(folder));
	}

	// ============== delete file and dirctory==================
	/**
	 * delete a file.
	 * 
	 * @param file
	 */
	public static void deleteFile(File file) {

		if (file == null || StringTool.isEmpty(file.getAbsolutePath())) {
			MyLogger.e("delete failure");
			return;
		}
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			}
		}
	}

	/**
	 * @param filePath
	 */
	public static void deleteFile(String filePath) {

		if (!StringTool.isEmpty(filePath)) {
			deleteFile(new File(filePath));
		}
	}

	/**
	 * delete folder.
	 * 
	 * @param folder
	 */
	public static void deleteFolder(File folder) {

		if (folder == null || StringTool.isEmpty(folder.getAbsolutePath())) {
			return;
		}
		if (folder.exists()) {
			if (folder.isDirectory()) {
				File[] files = folder.listFiles();
				if (files != null) {
					for (File file : files) {
						deleteFolder(file);
					}
				}
				folder.delete();
			} else {
				deleteFile(folder);
			}
		}
	}

	/**
	 * @param folderPath
	 */
	public static void deleteFolder(String folderPath) {

		if (!StringTool.isEmpty(folderPath)) {
			deleteFolder(new File(folderPath));
		}
	}

	// ===========find file in order to extendsions at the end=================
	/**
	 * In order to "end" At the end.
	 * 
	 * @param 'end'
	 * @return
	 */
	public static List<File> getAllWithEnd(File file, boolean ignore, String... extensions) {

		if (StringTool.isEmpty(file.getAbsolutePath())) {
			return null;
		}
		for (String extension : extensions) {
			if (StringTool.isEmpty(extension)) {
				return null;
			}
		}
		if (ignore) {
			file = new File(StorageTool.getStorageFile(), file.getAbsolutePath());
		}
		if ((!file.exists()) && file.isDirectory()) {
			return null;
		}
		List<File> files = new ArrayList<File>();
		fileFilter(file, files, extensions);
		return files;

	}

	/**
	 * @param path
	 * @param extensions
	 * @param ignore
	 * @return
	 */
	public static List<File> getAllWithEnd(String path, boolean ignore, String... extensions) {

		return getAllWithEnd(new File(path), ignore, extensions);
	}

	/**
	 * @param file
	 * @param extensions
	 * @return
	 */
	public static List<File> getAllWithEnd(File file, String... extensions) {

		return getAllWithEnd(file, DEFAULT_IGNORE_STYLE, extensions);
	}

	/**
	 * @param file
	 * @param extensions
	 * @return
	 */
	public static List<File> getAllWithEnd(String file, String... extensions) {

		return getAllWithEnd(new File(file), DEFAULT_IGNORE_STYLE, extensions);
	}

	/**
	 * filter the file.
	 * 
	 * @param file
	 * @param extensions
	 * @param files
	 */
	public static void fileFilter(File file, List<File> files, String... extensions) {

		if (!file.isDirectory()) {
			return;
		}
		File[] allFiles = file.listFiles();
		File[] allExtensionFiles = file.listFiles(new MyFileFilter(extensions));
		if (allExtensionFiles != null) {
			for (File single : allExtensionFiles) {
				files.add(single);
			}
		}
		if (allFiles != null) {
			for (File single : allFiles) {
				if (single.isDirectory()) {
					fileFilter(single, files, extensions);
				}
			}
		}
	}

	// ===========copy assert to a storage=================
	/**
	 * The assert directory, copy files to a storage card.
	 * 
	 * @param strAssetsFilePath
	 * @param strDesFilePath
	 * @return
	 */
	public boolean assetsCopyData(String strAssetsFilePath, String strDesFilePath) {

		boolean bIsSuc = true;
		InputStream inputStream = null;
		OutputStream outputStream = null;

		File file = new File(strDesFilePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
				Runtime.getRuntime().exec("chmod 766 " + file);
			} catch (IOException e) {
				bIsSuc = false;
			}

		} else {// ����
			return true;
		}

		try {
			inputStream = MyApplication.mContext.getAssets().open(strAssetsFilePath);
			outputStream = new FileOutputStream(file);

			int nLen = 0;

			byte[] buff = new byte[1024 * 1];
			while ((nLen = inputStream.read(buff)) > 0) {
				outputStream.write(buff, 0, nLen);
			}

			// ���
		} catch (IOException e) {
			bIsSuc = false;
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}

				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				bIsSuc = false;
			}

		}

		return bIsSuc;
	}

	// ===========copy single file.=================
	/**
	 * To copy single file.
	 * 
	 * @param src
	 * @param dst
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFile(File src, File dst) throws IOException {

		if ((!src.exists()) || src.isDirectory() || dst.isDirectory()) {
			return false;
		}
		if (!dst.exists()) {
			dst.createNewFile();
			// return false;
		}
		FileInputStream inputStream = null;
		inputStream = new FileInputStream(src);
		return copyFile(inputStream, dst);
	}

	/**
	 *拷贝文件
	 * 
	 * @param inputStream
	 * @param dst
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFile(InputStream inputStream, File dst) throws IOException {

		FileOutputStream outputStream = null;
		outputStream = new FileOutputStream(dst);
		int readLen = 0;
		byte[] buf = new byte[1024];
		while ((readLen = inputStream.read(buf)) != -1) {
			outputStream.write(buf, 0, readLen);
		}
		outputStream.flush();
		inputStream.close();
		outputStream.close();
		return true;
	}

	/**
	 * @param src
	 * @param dst
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFile(String src, String dst) throws IOException {

		return copyFile(new File(src), new File(dst));
	}

	// ===========copy folder to storage=================
	/**
	 * To copy the folder.
	 * 
	 * @param srcDir
	 * @param destDir
	 * @param auto
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFolder(File srcDir, File destDir, boolean auto) throws IOException {

		if ((!srcDir.exists())) {
			return false;
		}
		if (srcDir.isFile() || destDir.isFile())
			return false;
		if (!destDir.exists()) {
			if (auto) {
				destDir.mkdirs();
			} else {
				return false;
			}
		}
		File[] srcFiles = srcDir.listFiles();
		int len = srcFiles.length;
		for (int i = 0; i < len; i++) {
			if (srcFiles[i].isFile()) {
				// ���Ŀ���ļ�
				File destFile = new File(destDir.getPath() + "//" + srcFiles[i].getName());
				copyFile(srcFiles[i], destFile);
			} else if (srcFiles[i].isDirectory()) {
				File theDestDir = new File(destDir.getPath() + "//" + srcFiles[i].getName());
				copyFolder(srcFiles[i], theDestDir, auto);
			}
		}
		return true;
	}

	/**
	 * @param srcDir
	 * @param desDir
	 * @param auto
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFolder(String srcDir, String desDir, boolean auto) throws IOException {

		return copyFolder(new File(srcDir), new File(desDir), auto);
	}

	/**
	 * @param srcDir
	 * @param desDir
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFolder(File srcDir, File desDir) throws IOException {

		return copyFolder(srcDir, desDir, DEFAULT_AUTO_CREATE_DIRECTORY);
	}

	/**
	 * @param srcDir
	 * @param desDir
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFolder(String srcDir, String desDir) throws IOException {

		return copyFolder(srcDir, desDir, DEFAULT_AUTO_CREATE_DIRECTORY);
	}

	// ===========move single file to storage=================
	/**
	 * To move the single file.
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean moveFile(File src, File dst) {

		boolean isCopy = false;
		try {
			isCopy = copyFile(src, dst);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!isCopy) {
			return false;
		}
		deleteFile(src);
		return true;
	}

	/**
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean moveFile(String src, String dst) {

		return moveFile(new File(src), new File(dst));
	}

	// ===========move folder to storage=================
	/**
	 * To move the folder.
	 * 
	 * @param srcDir
	 * @param destDir
	 * @param auto
	 * @return
	 */
	public static boolean moveFolder(File srcDir, File destDir, boolean auto) {

		if (srcDir.isFile() || destDir.isFile()) {
			return false;
		}
		if (!srcDir.exists()) {
			return false;
		}
		if (!destDir.exists()) {
			if (auto) {
				destDir.mkdirs();
			} else {
				return false;
			}
		}
		File[] srcDirFiles = srcDir.listFiles();
		int len = srcDirFiles.length;
		if (len <= 0) {
			srcDir.delete();
		}
		for (int i = 0; i < len; i++) {
			if (srcDirFiles[i].isFile()) {
				File oneDestFile = new File(destDir.getPath() + "//" + srcDirFiles[i].getName());
				moveFile(srcDirFiles[i], oneDestFile);
			} else if (srcDirFiles[i].isDirectory()) {
				File oneDestFile = new File(destDir.getPath() + "//" + srcDirFiles[i].getName());
				moveFolder(srcDirFiles[i], oneDestFile, auto);
				deleteFolder(srcDirFiles[i]);
			}

		}
		if (srcDir.exists()) {
			srcDir.delete();
		}
		return true;
	}

	/**
	 * @param src
	 * @param dst
	 * @param auto
	 * @return
	 */
	public static boolean moveFolder(String src, String dst, boolean auto) {

		return moveFolder(new File(src), new File(dst));
	}

	/**
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean moveFolder(File src, File dst) {

		return moveFolder(src, dst, DEFAULT_AUTO_CREATE_DIRECTORY);
	}

	/**
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean moveFolder(String src, String dst) {

		return moveFolder(new File(src), new File(dst), DEFAULT_AUTO_CREATE_DIRECTORY);
	}

	// ===========get private directory=================
	/**
	 * To get private directory.
	 * 
	 * @return
	 */
	public static File getPrivateDir() {

		return MyApplication.mContext.getFilesDir();
	}

	public static File getGoodsFilePath(String goodsId, String url, String ver) {
		return new File(StorageTool.getStorageFile(),
				VariableTool.FILE_SAVE_DIRECTORY + "/goods_" + goodsId + "/" + MD5Tool.getMD5Str(url) + "_" + ver);
	}

	/**
	 * 获取文件大小
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static long getFileSize(File file) throws Exception {

		long size = 0;
		if (file.exists()) {
			size = file.length();
		} else {
			file.createNewFile();
		}
		return size;
	}

	/**
	 * 获取文件大小
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getFileSizes(File f) throws Exception {

		long size = 0;
		File flist[] = f.listFiles();
		if (flist == null) {
			return 0;
		}
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSizes(flist[i]);
			} else {
				size = size + getFileSize(flist[i]);
			}
		}
		return size;
	}

	public static void CopyStream(InputStream is, OutputStream os) {
		final int buffer_size = 1024;
		try {
			byte[] bytes = new byte[buffer_size];
			for (;;) {
				int count = is.read(bytes, 0, buffer_size);
				if (count == -1)
					break;
				os.write(bytes, 0, count);
			}
		} catch (Exception ex) {
		}
	}

}
