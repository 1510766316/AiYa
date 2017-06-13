package app.wgx.com.aiYa.commonTool;

/**
 * 网络请求接口
 */
public class HttpConstant {
    public static final String BASE_URL="http://192.168.31.1/api/";
    public static final String API_KEY="68545b90359223109807aef3dc8c4f71";
    /**
     * 首页轮播图
     */
    public static final String HOME_BANNER_URL=BASE_URL+"home/getHomeBanner";

    /**
     * 首页新闻类型列表
     */
    public static final String HOME_NEWSTYPE_URL=BASE_URL+"news/getNewsType";

    /**
     * 首页新闻
     */
    public static final String HOME_NEWS_URL=BASE_URL+"news/getNews";

    /**
     * 首页根据类型获取新闻
     */
    public static final String HOME_NEWSBYTYPE_URL=BASE_URL+"news/getNewsByType";
}
