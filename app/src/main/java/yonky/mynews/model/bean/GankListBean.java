package yonky.mynews.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/6.
 */

public class GankListBean {

    /**
     * error : false
     * results : [{"_id":"59f8553f421aa90fef2034e9","createdAt":"2017-10-31T18:49:35.980Z","desc":"深度学习js与安卓的交互以及WebView的那些坑","images":["http://img.gank.io/d1d4f7b4-9291-499a-8b20-c3c485c46119"],"publishedAt":"2017-11-01T14:20:59.209Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/b9164500d3fb","used":true,"who":"阿韦"},{"_id":"59f92869421aa90fe50c01c1","createdAt":"2017-11-01T09:50:33.515Z","desc":"Android启动页黑屏及最优解决方案","publishedAt":"2017-11-01T14:20:59.209Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247487886&idx=1&sn=6fbe4d971e873ee351aef213eedba0ae","used":true,"who":"陈宇明"},{"_id":"59f92b44421aa90fe50c01c3","createdAt":"2017-11-01T10:02:44.598Z","desc":"可设定阴影颜色的shadow-layout","images":["http://img.gank.io/d3acd780-a1a6-4529-a026-b8bd7967626a"],"publishedAt":"2017-11-01T14:20:59.209Z","source":"chrome","type":"Android","url":"https://github.com/dmytrodanylyk/shadow-layout","used":true,"who":"galois"},{"_id":"59f937f1421aa90fe50c01c4","createdAt":"2017-11-01T10:56:49.711Z","desc":"LeetCode的Java题解(updating)","publishedAt":"2017-11-01T14:20:59.209Z","source":"web","type":"Android","url":"https://github.com/Blankj/awesome-java-leetcode","used":true,"who":"Mengjie Cai"},{"_id":"59f95971421aa90fef2034ec","createdAt":"2017-11-01T13:19:45.187Z","desc":"Facebook面经记","images":["http://img.gank.io/7aa86243-57c4-43a4-80ba-de2809dd106e"],"publishedAt":"2017-11-01T14:20:59.209Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/fd8d3478f6ee","used":true,"who":"Mengjie Cai"},{"_id":"59f6a9ef421aa90fef2034dd","createdAt":"2017-10-30T12:26:23.779Z","desc":"Android Studio3.0填坑指南","images":["http://img.gank.io/64396106-8bf6-4038-9c14-0e309835296b"],"publishedAt":"2017-10-31T12:25:55.217Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/b45d68c98828","used":true,"who":"阿韦"},{"_id":"59f7d6bc421aa90fe50c01b5","createdAt":"2017-10-31T09:49:48.830Z","desc":"Android 仿微信微博的展开全文功能","publishedAt":"2017-10-31T12:25:55.217Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247487876&idx=1&sn=7406cceabe26925307dae53a881c41aa","used":true,"who":"陈宇明"},{"_id":"59f7dd59421aa90fe2f02c09","createdAt":"2017-10-31T10:18:01.749Z","desc":"拿到了新浪微博、小米和京东的 offer，应该如何做选择？","publishedAt":"2017-10-31T12:25:55.217Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzU4MjAzNTAwMA==&mid=2247483802&idx=1&sn=fe11c99170f648f7648fe05b61e05796&chksm=fdbf32cdcac8bbdbff22b6a9444c631c791cfb379a42f887d6fb5402b124c7df1d35efb19f6e#rd","used":true,"who":null},{"_id":"59eedfc2421aa90fe2f02be9","createdAt":"2017-10-24T14:37:54.794Z","desc":"自定义View实现卷尺效果，博客实现原理分析+github开源","publishedAt":"2017-10-27T12:02:30.376Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/06e65ef3f3f1","used":true,"who":null},{"_id":"59eef58d421aa90fef2034c8","createdAt":"2017-10-24T16:10:53.831Z","desc":"Idea 插件，OK, Gradle! 用于快速搜索 maven 库引用","images":["http://img.gank.io/7fb55566-e8c5-4235-8147-df2d7b80b60f"],"publishedAt":"2017-10-27T12:02:30.376Z","source":"web","type":"Android","url":"https://github.com/scana/ok-gradle","used":true,"who":"drakeet"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 59f8553f421aa90fef2034e9
         * createdAt : 2017-10-31T18:49:35.980Z
         * desc : 深度学习js与安卓的交互以及WebView的那些坑
         * images : ["http://img.gank.io/d1d4f7b4-9291-499a-8b20-c3c485c46119"]
         * publishedAt : 2017-11-01T14:20:59.209Z
         * source : web
         * type : Android
         * url : http://www.jianshu.com/p/b9164500d3fb
         * used : true
         * who : 阿韦
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;
        private boolean readState;
        private int height;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public boolean getReadState() {
            return readState;
        }

        public void setReadState(boolean readState) {
            this.readState = readState;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
