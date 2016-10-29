package app.wgx.com.aiYa.assistTool;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import app.wgx.com.aiYa.R;


/**
 * 提示类工具
 */
public class NoticeTool {
    private static Toast mToast;

    /**
     * 弹出框
     * @param context
     * @param msg
     * @param time
     */
    public static void showToast(Context context, String msg, int time) {
        if (null == mToast)
            mToast = Toast.makeText(context, msg, time);
        else
            mToast.setText(msg);
        mToast.show();
    }

    /**
     * 双击对话框
     * @param context
     * @param title
     * @param content
     * @param leftBT
     * @param rightBT
     * @param click
     */
    public static void bothDialog(Context context, String title, String content, String leftBT, String rightBT, final OnBothClickListener click) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton(leftBT, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        click.sure();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(rightBT, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        click.cancel();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * 单击对话框
     * @param context
     * @param title
     * @param content
     * @param bt
     * @param click
     */
    public static void singleDialog(Context context, String title, String content, String bt, final OnSingleClickListener click) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton(bt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        click.sure();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * 单选对话框
     *
     * @param title  标题
     * @param array  数组
     * @param select 首选
     * @return
     */
    public static void singleSelectDialog(Context context, String title, String[] array, int select,
                                          final OnSelectListener listener) {
        new AlertDialog.Builder(context).setTitle(title)
                .setSingleChoiceItems(array, select, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.select(which);
                        dialog.dismiss();
                    }
                }).setNegativeButton(context.getString(R.string.cancel),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    /**
     * 简单列表项
     *
     * @param title 标题
     * @param array 数组
     * @return
     */
    public static void simpleSelectDialog(Context context, String title, String[] array,
                                          final OnSelectListener listener) {
        new AlertDialog.Builder(context).setTitle(title).setItems(array, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                listener.select(which);
                dialog.dismiss();
            }
        }).setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }


    public interface OnBothClickListener {
        void sure();
        void cancel();
    }

    public interface OnSingleClickListener {
        void sure();
    }

    public interface OnSelectListener {
        void select(int which);
    }
}
