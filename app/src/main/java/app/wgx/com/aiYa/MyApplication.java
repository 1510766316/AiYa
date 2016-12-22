package app.wgx.com.aiYa;

import android.app.Application;
import android.content.Context;

import com.aspsine.multithreaddownload.DownloadConfiguration;
import com.aspsine.multithreaddownload.DownloadManager;
import com.wenming.library.LogReport;
import com.wenming.library.save.imp.CrashWriter;
import com.wenming.library.upload.http.HttpReporter;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import app.wgx.com.aiYa.assistTool.VariableTool;
import app.wgx.com.aiYa.bean.NetworkStateInfo;
import okhttp3.OkHttpClient;

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
        initOkHttp();
    //    initCrashReport();
    }


    /**
     * 初始化下载框架
     */
    private void initDownloader() {
        DownloadConfiguration configuration = new DownloadConfiguration();
        configuration.setMaxThreadNum(10);
        configuration.setThreadNum(3);
        DownloadManager.getInstance().init(getApplicationContext(), configuration);
    }
    /**************************
    // first: build a DownloadRequest:
    final DownloadRequest request = new DownloadRequest.Builder()
            .setName(appInfo.getName() + ".apk")
            .setUri(appInfo.getUrl())
            .setFolder(mDownloadDir)
            .build();

// download:
// the tag here, you can simply use download uri as your tag;
    DownloadManager.getInstance().download(request, tag, new CallBack() {
        @Override
        public void onStarted() {

        }

        @Override
        public void onConnecting() {

        }

        @Override
        public void onConnected(long total, boolean isRangeSupport) {

        }

        @Override
        public void onProgress(long finished, long total, int progress) {

        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onDownloadPaused() {

        }

        @Override
        public void onDownloadCanceled() {

        }

        @Override
        public void onFailed(DownloadException e) {

        }
    });

//pause
    DownloadManager.getInstance().pause(tag);

//pause all
    DownloadManager.getInstance().pauseAll();

//cancel
    DownloadManager.getInstance().cancel(tag);

//cancel all
    DownloadManager.getInstance().cancelAll();

//delete
    DownloadManager.getInstance().delete(tag);
   **************/

    /**
     * 初始化okHttpUtils
     */
    private void initOkHttp(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(1000*8L, TimeUnit.MILLISECONDS)
                .readTimeout(1000*8L,TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);

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
