package yonky.mynews.model.http.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import yonky.mynews.model.bean.DailyBeforeListBean;
import yonky.mynews.model.bean.DailyListBean;
import yonky.mynews.model.bean.HotListBean;
import yonky.mynews.model.bean.SectionChildListBean;
import yonky.mynews.model.bean.SectionListBean;
import yonky.mynews.model.bean.ThemeChildListBean;
import yonky.mynews.model.bean.ThemeListBean;
import yonky.mynews.model.bean.WelcomeBean;
import yonky.mynews.model.bean.ZhihuDetailBean;

/**
 * Created by Administrator on 2017/10/12.
 */

public interface ZhihuApis {
    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 启动界面图片
     */
    @GET("start-image/{res}")
    Flowable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    /**
     * 最新日报
     */
    @GET("news/latest")
    Flowable<DailyListBean> getDailyList();

    /**
     * 往期日报
     */
    @GET("news/before/{date}")
    Flowable<DailyBeforeListBean> getDailyBeforeList(@Path("date") String date);

    /**
     * 主题日报
     */
    @GET("themes")
    Flowable<ThemeListBean> getThemeList();

    /**
     * 主题日报详情
     */
    @GET("theme/{id}")
    Flowable<ThemeChildListBean> getThemeChildList(@Path("id") int id);

    /**
     * 专栏日报
     */
    @GET("sections")
    Flowable<SectionListBean> getSectionList();

    /**
     * 专栏日报详情
     */
    @GET("section/{id}")
    Flowable<SectionChildListBean> getSectionChildList(@Path("id") int id);

    /**
     * 热门日报
     */
    @GET("news/hot")
    Flowable<HotListBean> getHotList();

    /**
     * 日报详情
     */
    @GET("news/{id}")
    Flowable<ZhihuDetailBean> getDetailInfo(@Path("id") int id);

    /**
     * 日报的额外信息
     */
//    @GET("story-extra/{id}")
//    Flowable<DetailExtraBean> getDetailExtraInfo(@Path("id") int id);

    /**
     * 日报的长评论
     */
//    @GET("story/{id}/long-comments")
//    Flowable<CommentBean> getLongCommentInfo(@Path("id") int id);

    /**
     * 日报的短评论
     */
//    @GET("story/{id}/short-comments")
//    Flowable<CommentBean> getShortCommentInfo(@Path("id") int id);
}
