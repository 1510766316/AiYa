package app.wgx.com.aiYa;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import app.wgx.com.aiYa.assistTool.DeviceTool;
import app.wgx.com.aiYa.assistTool.NetWorkTool;
import app.wgx.com.aiYa.bean.NetworkStateInfo;
import app.wgx.com.aiYa.commonTool.HttpConstant;
import okhttp3.OkHttpClient;
/**
 * Create view by wgx
 *
 * @Describe 全局应用
 * <p>
 * Create at 2016/8/16 11:34
 */
public class MyApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initNetwork();
        initOkGo();
    }

    /**
     * 判断网络
     */
    private void initNetwork(){
        if (NetWorkTool.isNetworkAvailable(mContext)){
            NetworkStateInfo.getInstance().setConnected(true);
            if (NetWorkTool.isMobileConnected(mContext)){
                NetworkStateInfo.getInstance().setMobile(true);
                NetworkStateInfo.getInstance().setWifi(false);
            }else if (NetWorkTool.isWifiConnected(mContext)){
                NetworkStateInfo.getInstance().setMobile(false);
                NetworkStateInfo.getInstance().setWifi(true);
            }
        }else {
            NetworkStateInfo.getInstance().setConnected(false);
            NetworkStateInfo.getInstance().setMobile(false);
            NetworkStateInfo.getInstance().setWifi(false);
        }
    }

    /**
     * 初始化网络框架
     */
    private void initOkGo() {
        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders();
        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文，不允许有特殊字符
        headers.put("commonHeaderKey2", "commonHeaderValue2");
        HttpParams params = new HttpParams();
        params.put("packageName", DeviceTool.getAppName(mContext));     //param支持中文,直接传,不要自己编码
        params.put("mac", DeviceTool.getMacAddress(mContext));
        params.put("appKey", HttpConstant.API_KEY);
        //----------------------------------------------------------------------------------------//

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);
        //builder.addInterceptor(new ChuckInterceptor(this));                       //第三方的开源库，使用通知显示当前请求的log

        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
        //builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
        //builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失

        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //设置OkHttpClient
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params);                       //全局公共参数
    }

}
