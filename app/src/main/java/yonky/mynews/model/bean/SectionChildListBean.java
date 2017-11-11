package yonky.mynews.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/2.
 */

public class SectionChildListBean {
    /**
     * timestamp : 1490914797
     * stories : [{"images":["https://pic3.zhimg.com/v2-99abc50a93a0d45a1d084c2db2bf3252.jpg"],"date":"20170928","display_date":"9 月 28 日","id":9635440,"title":"这里是广告 · 如何应对一部行走的「十万个为什么」？"},{"images":["https://pic1.zhimg.com/v2-da65215d2439da7aa0e3412f94b965ec.jpg"],"date":"20170828","display_date":"8 月 28 日","id":9584763,"title":"这里是广告 · 跨过时空的大桥，大山深处体验极客生活"},{"images":["https://pic4.zhimg.com/v2-a63932b612abff0557e909ef74ade5a3.jpg"],"date":"20170827","display_date":"8 月 27 日","id":9588868,"title":"这里是广告 · 在这个国家，时间过得很慢"},{"images":["https://pic2.zhimg.com/v2-aa36deead4e8ba5fe21044c65cee37a1.jpg"],"date":"20170825","display_date":"8 月 25 日","id":9586188,"title":"这里是广告 · 为什么 RAP\r\n 要唱那么快？"},{"images":["https://pic4.zhimg.com/v2-dc4cd29af78f65a6815ee6bb1948e6b7.jpg"],"date":"20170821","display_date":"8 月 21 日","id":9573769,"title":"这里是广告 · 在技术不先进的情况下，是怎么造桥的？"},{"images":["https://pic4.zhimg.com/v2-3f15419bad4a523f8f5186a1ff71bc6b.jpg"],"date":"20170808","display_date":"8 月 8 日","id":9551885,"title":"这里是广告 · 完美求婚作战，你只差这一枚钻戒"},{"images":["https://pic1.zhimg.com/v2-4fd0510d4ef0766c07605e1bd3c6bfc4.jpg"],"date":"20170807","display_date":"8 月 7 日","id":9558150,"title":"这里是广告 · 蜀道有栈桥何惧上青天"},{"images":["https://pic1.zhimg.com/v2-65074c5255d737a88e6687a87208cb40.jpg"],"date":"20170726","display_date":"7 月 26 日","id":9538982,"title":"这里是广告 · 为什么你给女朋友拍的照片她从不发朋友圈？"},{"images":["https://pic1.zhimg.com/v2-062ff916eba117116668d9c6a6504774.jpg"],"date":"20170724","display_date":"7 月 24 日","id":9535606,"title":"这里是广告 · 把桥当工艺品来建造是种什么样的体验?"},{"images":["https://pic2.zhimg.com/v2-0b63ecae67fb62a9ac8728acd36acc0d.jpg"],"date":"20170721","display_date":"7 月 21 日","id":9533500,"title":"这里是广告 · 一包未来定制牛奶里能有多少高科技？"},{"images":["https://pic4.zhimg.com/v2-1f82410598b4f5d25c1876f88c54b087.jpg"],"date":"20170711","display_date":"7 月 11 日","id":9517585,"title":"这里是广告 · 如何缓解追更的痛苦？"},{"images":["https://pic3.zhimg.com/v2-1b3761365ab3383a396764f35954d992.jpg"],"date":"20170710","display_date":"7 月 10 日","id":9512986,"title":"这里是广告 · 玩游戏也是一项正经运动？"},{"images":["https://pic2.zhimg.com/v2-0595bd1a6a816b9b9d704be602c5f91d.jpg"],"date":"20170524","display_date":"5 月 24 日","id":9436593,"title":"这里是广告 · 你从什么时候开始用不了学生证？"},{"images":["https://pic1.zhimg.com/v2-e2010f27a11ec0a0f36c4e048a2d4398.jpg"],"date":"20170503","display_date":"5 月 3 日","id":9395459,"title":"这里是广告 · 青年节充值计划"},{"images":["https://pic3.zhimg.com/v2-ab95664e64ae89d0bfbf52e1d3326f76.jpg"],"date":"20170427","display_date":"4 月 27 日","id":9383848,"title":"这里是广告 · 从电影的世界里看 AI"},{"images":["https://pic3.zhimg.com/v2-83b6b582406a52606341606be4e28f62.jpg"],"date":"20170426","display_date":"4 月 26 日","id":9381796,"title":"这里是广告 · 急等！男/女朋友有脚气，要不要分手？"},{"images":["https://pic2.zhimg.com/v2-c6a041a50c936268cf486ef52fe401dd.jpg"],"date":"20170421","display_date":"4 月 21 日","id":9371304,"title":"这里是广告 · 一生只做一件事，是什么体验？"},{"images":["https://pic1.zhimg.com/v2-b43dd2feb6695cb33371519721928420.jpg"],"date":"20170418","display_date":"4 月 18 日","id":9364348,"title":"这里是广告 · 为什么身边总是有同龄人比你年轻？"},{"images":["https://pic4.zhimg.com/v2-ba8f3aec901371f6321ce128b91aee63.jpg"],"date":"20170416","display_date":"4 月 16 日","id":9358562,"title":"这里是广告 · 电是什么颜色？"},{"images":["https://pic3.zhimg.com/v2-c42d1a366899b7eaac49be2171db61ae.jpg"],"date":"20170331","display_date":"3 月 31 日","id":9323006,"title":"这里是广告 · 在程序员的世界里，这些词的意义大不同"}]
     * name : 这里是广告
     */

    private int timestamp;
    private String name;
    private List<StoriesBean> stories;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic3.zhimg.com/v2-99abc50a93a0d45a1d084c2db2bf3252.jpg"]
         * date : 20170928
         * display_date : 9 月 28 日
         * id : 9635440
         * title : 这里是广告 · 如何应对一部行走的「十万个为什么」？
         */

        private String date;
        private String display_date;
        private int id;
        private String title;
        private List<String> images;
        private Boolean readState;

        public Boolean getReadState() {
            return readState;
        }

        public void setReadState(Boolean readState) {
            this.readState = readState;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDisplay_date() {
            return display_date;
        }

        public void setDisplay_date(String display_date) {
            this.display_date = display_date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
