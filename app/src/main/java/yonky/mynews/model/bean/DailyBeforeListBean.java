package yonky.mynews.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class DailyBeforeListBean {

    /**
     * date : 20170531
     * stories : [{"images":["https://pic1.zhimg.com/v2-21f9760ec5edf57506815cd7b0181cec.jpg"],"type":0,"id":8694680,"ga_prefix":"053122","title":"小事 · 后来我们长大了"},{"images":["https://pic1.zhimg.com/v2-6a1c1357c82473ccfd1ec9965b2aeee0.jpg"],"type":0,"id":9450944,"ga_prefix":"053121","title":"看完这些电影，我觉得自己都快神经质了"},{"title":"不夸张地说，这几部热血漫画影响了日本汽车业的发展","ga_prefix":"053120","images":["https://pic4.zhimg.com/v2-e3014e6b6426eb4fa888c1a42eb23327.jpg"],"multipic":true,"type":0,"id":9450704},{"images":["https://pic1.zhimg.com/v2-914caef90df4b9ebd3f169afd8ba9340.jpg"],"type":0,"id":9450729,"ga_prefix":"053119","title":"- 怎么巩固市场老大的地位？\r\n- 让整个市场无利可图"},{"images":["https://pic4.zhimg.com/v2-a2024ab5370ac29dd1870499b0de33bb.jpg"],"type":0,"id":9448998,"ga_prefix":"053118","title":"注意到了吗，乔布斯和罗永浩演讲都会用这种「渐变背景」"},{"title":"违和感中透着搞笑，历史上每一幅烂画都是一个表情包","ga_prefix":"053117","images":["https://pic4.zhimg.com/v2-f7c7da93b081d5de75926c792168615b.jpg"],"multipic":true,"type":0,"id":9446836},{"images":["https://pic2.zhimg.com/v2-b7a7f8a2790b2120b907ebfb2b96194d.jpg"],"type":0,"id":9447232,"ga_prefix":"053116","title":"围棋史上最会创新的棋手，应该就是阿尔法狗了"},{"images":["https://pic3.zhimg.com/v2-c0022dacd2d1c5affc55212450b56f9e.jpg"],"type":0,"id":9449047,"ga_prefix":"053115","title":"明明工作这么努力，怎么还不给我升职加薪？"},{"images":["https://pic2.zhimg.com/v2-ad4f7646e7c48f50e6916a932b3d4649.jpg"],"type":0,"id":9449464,"ga_prefix":"053112","title":"大误 · 呀，我手机怎么碎了"},{"title":"太低不行太大也不行，选眼镜除了好看还需要考虑什么？","ga_prefix":"053111","images":["https://pic1.zhimg.com/v2-60b3f1f9960ed51985aca441ef3fe0a0.jpg"],"multipic":true,"type":0,"id":9448983},{"images":["https://pic4.zhimg.com/v2-5ab36a9f94ddfb164f96a545e7cc4adf.jpg"],"type":0,"id":9448918,"ga_prefix":"053110","title":"刻板印象能有多荒谬，让这部「善恶颠倒」的音乐剧告诉你"},{"images":["https://pic2.zhimg.com/v2-c868907914c8a642cc4453a160c5de69.jpg"],"type":0,"id":9449645,"ga_prefix":"053109","title":"我们擅长的，AI 还有一些办不到；\r\nAI 擅长的，我们永远也办不到"},{"images":["https://pic1.zhimg.com/v2-0ed36680b360f9279c1246f1719d1df0.jpg"],"type":0,"id":9449025,"ga_prefix":"053108","title":"想考注册会计师，首先你得知道它难在哪"},{"images":["https://pic3.zhimg.com/v2-8f8a1f9368d860f3b69d72c7ce9ffa96.jpg"],"type":0,"id":9442796,"ga_prefix":"053107","title":"学了七年的茶，现在来告诉你学茶的正确步骤"},{"images":["https://pic4.zhimg.com/v2-d100270b55a965faf1458be491b69957.jpg"],"type":0,"id":9449394,"ga_prefix":"053107","title":"传说中像天堂一样的北欧，真的比别的地方更平等吗？"},{"images":["https://pic4.zhimg.com/v2-0a1fdc948e11898be740773bcd2678f3.jpg"],"type":0,"id":9449109,"ga_prefix":"053107","title":"当你说「我有一个朋友\u2026\u2026」，我意味深长地笑了"},{"images":["https://pic4.zhimg.com/v2-afca8f815a640712f0171283fb7ba183.jpg"],"type":0,"id":9449303,"ga_prefix":"053106","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<DailyListBean.StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DailyListBean.StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<DailyListBean.StoriesBean> stories) {
        this.stories = stories;
    }

}
