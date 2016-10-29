package app.wgx.com.aiYa.widget.bannerView;


import java.io.Serializable;

/**
 * banner view bean
 */
public class BannerInfo implements Serializable{
    private static final long serialVersionUID = -874150537011583903L;
    /**
     *　标题
     */
    public String bannerTitle;
    /**
     *图片地址
     */
    public String bannerImageUrl;
    /**
     *点击图片跳转地址
     */
    public String bannerSkipUrl;
    /**
     *　图片更新时间
     */
    public String bannerUpdateTime;
    /**
     *　图片截止日期
     */
    public String bannerEndTime;
    /**
     *　图片是否可用
     */
    public String bannerIsEnable;
}
