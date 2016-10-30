package app.wgx.com.aiYa;

import android.app.Application;
import android.content.Context;

import com.aspsine.multithreaddownload.DownloadConfiguration;
import com.aspsine.multithreaddownload.DownloadManager;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

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


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initOkHttp();
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
}
