package yonky.mynews.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31.
 */

public class ZhihuDetailBean {

    /**
     * body : <div class="main-wrap content-wrap">
     <div class="headline">

     <div class="img-place-holder"></div>



     </div>

     <div class="content-inner">




     <div class="question">
     <h2 class="question-title">为什么越来越多大学生沉溺于游戏中？</h2>

     <div class="answer">

     <div class="meta">
     <img class="avatar" src="http://pic2.zhimg.com/v2-6efac062d385f2bbc70faedb52b2c531_is.jpg">
     <span class="author">京师心理大学堂，</span><span class="bio">打造最优质的心理科普平台</span>
     </div>

     <div class="content">

     * image_source : 植物大战僵尸
     * title : 一玩游戏就停不下来，你也这样对吧？
     * image : https://pic4.zhimg.com/v2-63a1f70fba112995c1748556fdcab15f.jpg
     * share_url : http://daily.zhihu.com/story/9654463
     * js : []
     * ga_prefix : 103109
     * images : ["https://pic2.zhimg.com/v2-d413a4f4bc5b5ac61df4867c1fbc8d25.jpg"]
     * type : 0
     * id : 9654463
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<?> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
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

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
