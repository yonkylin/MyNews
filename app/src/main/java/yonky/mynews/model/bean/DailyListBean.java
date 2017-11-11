package yonky.mynews.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class DailyListBean {

    /**
     * date : 20171012
     * stories : [{"images":["https://pic1.zhimg.com/v2-fd12866f4de117f8e1bb08d76ef3dcac.jpg"],"type":0,"id":9650912,"ga_prefix":"101214","title":"ONE 实验室团队解散了，别着急，非虚构还好着呢"},{"images":["https://pic3.zhimg.com/v2-9a97e14954f98e55ed5d00eb3c3bce96.jpg"],"type":0,"id":9650321,"ga_prefix":"101213","title":"微信悄悄地「松了口」，现在你可以在小程序购买虚拟商品了"},{"images":["https://pic2.zhimg.com/v2-6b5b2f6c22eb6f8d970aa4f17284fe25.jpg"],"type":0,"id":9649515,"ga_prefix":"101212","title":"大误 · 获得超能力的机会来了"},{"images":["https://pic2.zhimg.com/v2-d18e3a49655c19e69caf55d03a10253d.jpg"],"type":0,"id":9649734,"ga_prefix":"101211","title":"- 他都被锤成这样了你还信他？\r\n- 我爱他，我相信他"},{"images":["https://pic4.zhimg.com/v2-81e9feeeb18b8fbfc025bdf7fa65ea8b.jpg"],"type":0,"id":9649550,"ga_prefix":"101210","title":"总是上台紧张，担心台上会出错\u2026\u2026别急，郎朗也是这样"},{"images":["https://pic3.zhimg.com/v2-ab0c3b1dbab20c91adabe8990488a516.jpg"],"type":0,"id":9649990,"ga_prefix":"101209","title":"一不留神就出现的小情绪，怎么安抚才又快又有用？"},{"images":["https://pic2.zhimg.com/v2-c65859c44cf5f1e5d90fbaa1daf83611.jpg"],"type":0,"id":9649311,"ga_prefix":"101208","title":"每次只弄几个小惊喜和小槽点，一款「年货游戏」又完成了"},{"images":["https://pic2.zhimg.com/v2-497f7e283bd8168829b71fd26c2258d9.jpg"],"type":0,"id":9649306,"ga_prefix":"101207","title":"「J 币」让日本 70 家银行心连心，只为抵抗外国支付势力的进攻"},{"images":["https://pic4.zhimg.com/v2-40511735efa0f696e075d3b7f77aaff3.jpg"],"type":0,"id":9650308,"ga_prefix":"101207","title":"微软正式宣布 Windows Phone 死亡，最后 1% 的市场份额也守不住了"},{"images":["https://pic2.zhimg.com/v2-27936c40d07e75fd54f249cbb21f67b1.jpg"],"type":0,"id":9649527,"ga_prefix":"101207","title":"周航：我与易到的前半生"},{"images":["https://pic2.zhimg.com/v2-6712644682cf50e0bcb46717d2147dd9.jpg"],"type":0,"id":9650304,"ga_prefix":"101206","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-0d2fe710f12f2ec03af77325d2faed48.jpg","type":0,"id":9650912,"ga_prefix":"101214","title":"ONE 实验室团队解散了，别着急，非虚构还好着呢"},{"image":"https://pic4.zhimg.com/v2-07913645ad433cfa146e481357e4be57.jpg","type":0,"id":9649734,"ga_prefix":"101211","title":"- 他都被锤成这样了你还信他？\r\n- 我爱他，我相信他"},{"image":"https://pic4.zhimg.com/v2-718dd68a20db3f30e04f40bde95dbd63.jpg","type":0,"id":9650308,"ga_prefix":"101207","title":"微软正式宣布 Windows Phone 死亡，最后 1% 的市场份额也守不住了"},{"image":"https://pic3.zhimg.com/v2-3666e3610adaf51561c7eb4c5830c656.jpg","type":0,"id":9649527,"ga_prefix":"101207","title":"周航：我与易到的前半生"},{"image":"https://pic2.zhimg.com/v2-7f506da5ffcd011bf0d3b16aaf40e811.jpg","type":0,"id":9649678,"ga_prefix":"101116","title":"以为取了个洋气的英文名，其实听上去跟「李建国」差不多"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-fd12866f4de117f8e1bb08d76ef3dcac.jpg"]
         * type : 0
         * id : 9650912
         * ga_prefix : 101214
         * title : ONE 实验室团队解散了，别着急，非虚构还好着呢
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;
        private boolean readState;

        public boolean getReadState() {
            return readState;
        }

        public void setReadState(boolean readState) {
            this.readState = readState;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
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

    public static class TopStoriesBean {
        /**
         * image : https://pic1.zhimg.com/v2-0d2fe710f12f2ec03af77325d2faed48.jpg
         * type : 0
         * id : 9650912
         * ga_prefix : 101214
         * title : ONE 实验室团队解散了，别着急，非虚构还好着呢
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
