package yonky.mynews.model.http;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import yonky.mynews.app.Constants;
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
import yonky.mynews.model.http.api.GankApis;
import yonky.mynews.model.http.api.GoldApis;
import yonky.mynews.model.http.api.WechatApis;
import yonky.mynews.model.http.api.ZhihuApis;
import yonky.mynews.model.http.response.GoldHttpResponse;

import static yonky.mynews.app.Constants.KEY_API;

/**
 * Created by Administrator on 2017/10/12.
 */

public class RetrofitHelper implements HttpHelper {
    private ZhihuApis mZhihuApiService;
    private WechatApis mWechatApiService;
    private GankApis mGankApiService;
    private GoldApis mGoldApiService;


    @Inject
    public RetrofitHelper(ZhihuApis mZhihuApiService,WechatApis mWechatApiService,GankApis mGankApiService,GoldApis mGoldApiService){
        this.mZhihuApiService=mZhihuApiService;
        this.mWechatApiService = mWechatApiService;
        this.mGankApiService = mGankApiService;
        this.mGoldApiService = mGoldApiService;
    }



//    知乎获取
    @Override
    public Flowable<DailyListBean> fetchDailyListInfo() {
        return mZhihuApiService.getDailyList();
    }

    @Override
    public Flowable<DailyBeforeListBean> fetchDailyBeforeListInfo(String date) {
        return mZhihuApiService.getDailyBeforeList(date);
    }

    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mZhihuApiService.getWelcomeInfo(res);
    }

    @Override
    public Flowable<ZhihuDetailBean> fetchDetailInfo(int id) {
        return mZhihuApiService.getDetailInfo(id);
    }

    @Override
    public Flowable<ThemeListBean> fetchThemeListInfo() {
        return mZhihuApiService.getThemeList();
    }

    @Override
    public Flowable<ThemeChildListBean> fetchThemeChildListInfo(int id) {
        return mZhihuApiService.getThemeChildList(id);
    }

    @Override
    public Flowable<SectionListBean> fetchSectionListInfo() {
        return mZhihuApiService.getSectionList();
    }

    @Override
    public Flowable<SectionChildListBean> fetchSectionChildListInfo(int id) {
        return mZhihuApiService.getSectionChildList(id);
    }

    @Override
    public Flowable<HotListBean> fetchHotListInfo() {
        return mZhihuApiService.getHotList();
    }

//    微信获取

    @Override
    public Flowable<WXItemBean> fetchWXDataInfo(int num, int page) {
        return mWechatApiService.getWXHot(KEY_API,num,page);
    }

    @Override
    public Flowable<WXItemBean> fetchWechatSearchListInfo(int num, int page, String word) {
        return mWechatApiService.getWXHotSearch(KEY_API,num,page,word);
    }

    //    干货

    @Override
    public Flowable<GankListBean> fetchGankDataInfo(String tech, int num, int page) {
        return mGankApiService.getTechList(tech,num,page);
    }

    @Override
    public Flowable<GankListBean> fetchGirlListInfo(int num, int page) {
        return mGankApiService.getGirlList(num,page);
    }

    @Override
    public Flowable<GankListBean> fetchRandomGirlInfo(int num) {
        return mGankApiService.getRandomGirl(num);
    }

    @Override
    public Flowable<GankListBean> fetchGankSearchList(String query, String type, int num, int page) {
        return mGankApiService.getSearchList(query,type,num,page);
    }

    //    掘金

    @Override
    public Flowable<GoldHttpResponse<List<GoldListBean>>> fetchGoldList(String type, int num, int page) {
        return mGoldApiService.getGoldList(Constants.LEANCLOUD_ID,Constants.LEANCLOUD_SIGN,
                "{\"category\":\"" + type + "\"}", "-createdAt", "user,user.installation", num, page * num);
    }
}
