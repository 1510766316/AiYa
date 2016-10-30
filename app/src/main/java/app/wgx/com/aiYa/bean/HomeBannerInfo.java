package app.wgx.com.aiYa.bean;

import java.util.List;

/**
 * 首页fragment 轮播图
 */
public class HomeBannerInfo {
    /**
     * reason : success
     * msg : 获取数据成功
     * result : [{"id":"1","serverUrl":"http://192.168.1.102/","title":"全面从严治党进入新时代","imageUrl":"apptwo/picture/home_banner/l1.jpg","webUrl":"http://news.163.com/16/1030/15/C4KTEKSI000189FH.html","createtime":"2016-10-30","endtime":"2016-12-01","updatetime":"2016-10-31","is_enabled":"1"},{"id":"2","serverUrl":"http://192.168.1.102/","title":"增强\u201c四个意识\u201d","imageUrl":"apptwo/picture/home_banner/l2.jpg","webUrl":"http://news.163.com/16/1029/21/C4IUUH5K000189FH.html","createtime":"2016-10-30","endtime":"2017-01-06","updatetime":"2016-10-31","is_enabled":"1"},{"id":"3","serverUrl":"http://192.168.1.102/","title":"中国创新缘何被世界一再点赞？","imageUrl":"apptwo/picture/home_banner/l3.jpg","webUrl":"http://news.163.com/16/1029/20/C4ISOMCM000189FH.html","createtime":"2016-11-01","endtime":"2016-12-01","updatetime":"2016-12-01","is_enabled":"1"},{"id":"4","serverUrl":"http://192.168.1.102/","title":"落马\"吃货\"高官","imageUrl":"apptwo/picture/home_banner/l4.jpg","webUrl":"http://news.163.com/16/1030/12/C4KHQSIL0001875N.html","createtime":"2016-10-30","endtime":"2017-02-01","updatetime":"2016-11-04","is_enabled":"1"}]
     * status : 1
     */

    private String reason;
    private String msg;
    private int status;
    /**
     * id : 1
     * serverUrl : http://192.168.1.102/
     * title : 全面从严治党进入新时代
     * imageUrl : apptwo/picture/home_banner/l1.jpg
     * webUrl : http://news.163.com/16/1030/15/C4KTEKSI000189FH.html
     * createtime : 2016-10-30
     * endtime : 2016-12-01
     * updatetime : 2016-10-31
     * is_enabled : 1
     */

    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String id;
        private String serverUrl;
        private String title;
        private String imageUrl;
        private String webUrl;
        private String createTime;
        private String endTime;
        private String updateTime;
        private String is_enabled;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getServerUrl() {
            return serverUrl;
        }

        public void setServerUrl(String serverUrl) {
            this.serverUrl = serverUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createtime) {
            this.createTime = createtime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endtime) {
            this.endTime = endtime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updatetime) {
            this.updateTime = updatetime;
        }

        public String getIs_enabled() {
            return is_enabled;
        }

        public void setIs_enabled(String is_enabled) {
            this.is_enabled = is_enabled;
        }
    }
}
