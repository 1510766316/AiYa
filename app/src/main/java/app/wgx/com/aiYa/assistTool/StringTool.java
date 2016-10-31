package app.wgx.com.aiYa.assistTool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class StringTool {
    private final static String QQ_REGULAR_EXPRESSION = "[1-9][0-9]{4,14}";

    private final static String EMAIL_REGULAR_EXPRESSION = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

	/*
     * private final static String PHONE_REGULAR_EXPRESSION =
	 * "^((13[0-9])|(15[^4,\\D])|(18[0,2,5-9]))\\d{8}$";
	 */

    private final static String PHONE_REGULAR_EXPRESSION = "^((17[0,6,7,8])|(13[0-9])|(15[^4,\\D])|(18[0,2,3,5-9]))\\d{8}$";

    // private final static String PHONE_REGULAR_EXPRESSION = "^(1)\\d{10}$";

    private final static String ZIPCODE_REGULAR_EXPRESSION = "[1-9]\\d{5}(?!\\d)";// ��֤�ʱ�

    private final static String EMPTY = "";

    /**
     * @Describe 判断字符串是否为空
     * <p>
     * Create at 2016/9/10 14:59
     */
    public static boolean isEmpty(Object input) {

        return input == null || input.toString().trim().length() == 0 || "null".equals(input.toString().trim());
    }

    /**
     * @Describe 判断字符串是否符合QQ规范
     * <p>
     * Create at 2016/9/10 14:59
     */
    public static boolean isQQ(String QQ) {

        if (isEmpty(QQ)) {
            return false;
        }
        Pattern QQer = Pattern.compile(QQ_REGULAR_EXPRESSION);
        return QQer.matcher(QQ).matches();
    }

    /**
     * @Describe 判断字符串是否符合Email规范
     * <p>
     * Create at 2016/9/10 14:59
     */
    public static boolean isEmail(String email) {

        if (isEmpty(email)) {
            return false;
        }
        Pattern emailer = Pattern.compile(EMAIL_REGULAR_EXPRESSION);
        return emailer.matcher(email).matches();
    }

    /**
     * @Describe 判断字符串是否符合Phone规范
     * <p>
     * Create at 2016/9/10 14:59
     */
    public static boolean isPhoneNumber(String phone) {

        if (isEmpty(phone)) {
            return false;
        }
        Pattern emailer = Pattern.compile(PHONE_REGULAR_EXPRESSION);
        return emailer.matcher(phone).matches();
    }

    public static boolean isZipCode(String ZipCode) {

        if (isEmpty(ZipCode)) {
            return false;
        }
        Pattern emailer = Pattern.compile(ZIPCODE_REGULAR_EXPRESSION);
        return emailer.matcher(ZipCode).matches();
    }

    /**
     * @Describe 字符串置空
     * <p>
     * Create at 2016/9/10 15:02
     */
    public static String clean(Object str) {

        return isEmpty(str) ? EMPTY : str.toString();
    }

    /**
     * @Describe 字符串转码UTF-8
     * <p>
     * Create at 2016/9/10 15:03
     */
    public static String utf8Encode(String str) {

        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * @Describe 与MD5加密后的文字对照
     * <p>
     * Create at 2016/9/10 14:57
     */
    public static boolean md5StrCompare(Object org, String md5Str) {

        String md5_org = EncryptTool.md5(org.toString());
        return md5_org.equals(md5Str);
    }

    /**
     * @Describe 截取字符串
     * <p>
     * Create at 2016/9/10 14:57
     */
    public static String getNumber(String str, String format) {

        if (str.indexOf(format) < 0) {
            return null;
        }
        return str.substring(str.indexOf(format) + format.length(), str.length());
    }


    /**
     * @Describe 是否包含表情字符
     * <p>
     * Create at 2016/9/10 14:55
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Describe 是否是表情文字
     * <p>
     * Create at 2016/9/10 14:55
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
}
