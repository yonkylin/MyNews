package yonky.mynews.model.bean;

/**
 * Created by Administrator on 2017/10/12.
 */

public class WelcomeBean {
    private String text;
    private String img;

    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text= text;
    }
    public String getImg(){
        return img;
    }
    public void setImg(String img){
        this.img = img;
    }
}
