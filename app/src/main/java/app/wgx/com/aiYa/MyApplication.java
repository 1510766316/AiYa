package app.wgx.com.aiYa;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.wenming.library.LogReport;
import com.wenming.library.save.imp.CrashWriter;
import com.wenming.library.upload.http.HttpReporter;

import java.util.logging.Level;

import app.wgx.com.aiYa.assistTool.VariableTool;
import app.wgx.com.aiYa.bean.NetworkStateInfo;

/**
 * Create view by wgx
 *
 * @Describe 全局应用
 * <p>
 * Create at 2016/8/16 11:34
 */
public class MyApplication extends Application {
    public static Context mContext, mCurrentContext;
    public static NetworkStateInfo mNetworkStateInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mNetworkStateInfo =new NetworkStateInfo();
        initHttp();
    }


    /**
     * 初始化网络框架
     */
    private void initHttp() {
        OkGo.init(this);
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()
                    // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                    // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                    .debug("OkGo", Level.INFO, true)
                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initCrashReport() {
        LogReport.getInstance()
                .setCacheSize(30 * 1024 * 1024)//支持设置缓存大小，超出后清空
                .setLogDir(getApplicationContext(), VariableTool.LOG_SAVE_DIRECTORY)//定义log路径
                .setWifiOnly(false)//设置只在Wifi状态下上传，设置为false为Wifi和移动网络都上传
                .setLogSaver(new CrashWriter(getApplicationContext()))//支持自定义保存崩溃信息的样式
                //.setEncryption(new AESEncode()) //支持日志到AES加密或者DES加密，默认不开启
                .init(getApplicationContext());
      //  initHttpReporter();
    }
    /**
     * 使用HTTP发送日志
     */
    private void initHttpReporter() {
        HttpReporter http = new HttpReporter(this);
        http.setUrl("http://crashreport.jd-app.com/your_receiver");//发送请求的地址
        http.setFileParam("fileName");//文件的参数名
        http.setToParam("to");//收件人参数名
        http.setTo("你的接收邮箱");//收件人
        http.setTitleParam("subject");//标题
        http.setBodyParam("message");//内容
        LogReport.getInstance().setUploadType(http);
    }
}
