package yonky.mynews.model.http;

import java.util.List;

import io.reactivex.Flowable;
import yonky.mynews.model.bean.DailyBeforeListBean;
import yonky.mynews.model.bean.DailyListBean;
import yonky.mynews.model.bean.GankListBean;
import yonky.mynews.model.bean.GoldListBean;
import yonky.mynews.model.bean.HotListBean;
import yonky.mynews.model.bean.SectionChildListBean;
import yonky.mynews.model.bean.SectionListBean;
import yonky.mynews.model.bean.ThemeChildListBean;
import yonky.mynews.model.bean.ThemeListBean;
import yonky.mynews.model.bean.WXItemBean;
import yonky.mynews.model.bean.WelcomeBean;
import yonky.mynews.model.bean.ZhihuDetailBean;
import yonky.mynews.model.http.response.GoldHttpResponse;

/**
 * Created by Administrator on 2017/10/12.
 */

public interface HttpHelper {

    //知乎
    Flowable<DailyListBean> fetchDailyListInfo();
    Flowable<DailyBeforeListBean> fetchDailyBeforeListInfo(String date);
    Flowable<WelcomeBean> fetchWelcomeInfo(String res);
    Flowable<ZhihuDetailBean> fetchDetailInfo(int id);
    Flowable<ThemeListBean> fetchThemeListInfo();
    Flowable<ThemeChildListBean>fetchThemeChildListInfo(int id);
    Flowable<SectionListBean>fetchSectionListInfo();
    Flowable<SectionChildListBean>fetchSectionChildListInfo(int id);
    Flowable<HotListBean>fetchHotListInfo();
    //微信
    Flowable<WXItemBean>fetchWXDataInfo(int num,int page);

//    干货
    Flowable<GankListBean>fetchGankDataInfo(String tech,int num,int page);
    Flowable<GankListBean>fetchGirlListInfo(int num,int page);
    Flowable<GankListBean>fetchRandomGirlInfo(int num);

//    掘金
    Flowable<GoldHttpResponse<List<GoldListBean>>>fetchGoldList(String type,int num,int page);

}
