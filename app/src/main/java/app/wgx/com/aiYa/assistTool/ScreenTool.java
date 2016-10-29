package app.wgx.com.aiYa.assistTool;

import android.content.Context;
import android.view.WindowManager;

/**
 * 屏幕相关的工具类
 */
public class ScreenTool {

    /**
     * 根据屏幕密度，将一个dp值转换成对应的px值
     *
     * @param context 上下文对象
     * @param dp      需要转换的dp值
     * @return dp值对应的px值
     */
    public static int dp2px(Context context, float dp) {
        return (int) (0.5F + context.getResources().getDisplayMetrics().density * dp);
    }

    /**
     * 将一个px转换成dp值
     *
     * @param context 上下文对象
     * @param px      需要转换的px值
     * @return px对应的dp值
     */
    public static int px2dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取封装屏幕宽高的Point对象
     *
     * @param context 上下文对象
     * @return 封装尺寸的对象
     */
    public static int[] getScreenSize(Context context) {
        int[] screen=new int[2];
        WindowManager wm = (WindowManager)context
                .getSystemService(Context.WINDOW_SERVICE);

        screen[0]= wm.getDefaultDisplay().getWidth();
        screen[1] = wm.getDefaultDisplay().getHeight();
        return screen;
    }


}
