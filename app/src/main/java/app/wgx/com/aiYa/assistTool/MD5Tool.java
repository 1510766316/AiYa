package app.wgx.com.aiYa.assistTool;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Create view by wgx
 *
 * @Describe 文字，字符加密工具
 *
 * Create at 2016/9/10 15:04
 */
public class MD5Tool {

	/**
	 * @Describe MD5加密
	 *
	 * Create at 2016/9/10 15:04
	 */
    public static String getMD5Str(Object...objects){
    	StringBuilder stringBuilder=new StringBuilder();
    	for (Object object : objects) {
			stringBuilder.append(object.toString());
		}
    	return getMD5Str(stringBuilder.toString());
    }


	/**
	 * @Describe MD5加密方式
	 *
	 * Create at 2016/9/10 15:04
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			MyLogger.e("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}
}
