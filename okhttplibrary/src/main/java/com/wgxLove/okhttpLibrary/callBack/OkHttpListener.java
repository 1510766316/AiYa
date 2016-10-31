package com.wgxLove.okhttpLibrary.callBack;


/**
 * okHttp two types interface
 */
public class OkHttpListener {
    /**
     * 异步请求数据
     */
    interface AsyncListener {
        void start();

        void downLoading(int progress, long totalSize, long currentSize);

        void success(Object response);

        void error(Exception e, String msg);

        void finish();
    }

    /**
     * 下载文件使用
     */
    interface DownLoadListener {
        void start();

        void downLoading(int progress, long totalSize, long currentSize);

        void pause();

        void cancel();

        void success();

        void error(Exception e, String msg);

        void finish();
    }

}
