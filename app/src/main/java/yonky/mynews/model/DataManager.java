package yonky.mynews.model;

import java.util.List;

import io.reactivex.Flowable;
import yonky.mynews.model.bean.DailyBeforeListBean;
import yonky.mynews.model.bean.DailyListBean;
import yonky.mynews.model.bean.GankListBean;
import yonky.mynews.model.bean.GoldListBean;
import yonky.mynews.model.bean.HotListBean;
import yonky.mynews.model.bean.RealmLikeBean;
import yonky.mynews.model.bean.SectionChildListBean;
import yonky.mynews.model.bean.SectionListBean;
import yonky.mynews.model.bean.ThemeChildListBean;
import yonky.mynews.model.bean.ThemeListBean;
import yonky.mynews.model.bean.WXItemBean;
import yonky.mynews.model.bean.WelcomeBean;
import yonky.mynews.model.bean.ZhihuDetailBean;
import yonky.mynews.model.db.DBHelper;
import yonky.mynews.model.http.HttpHelper;
import yonky.mynews.model.http.response.GoldHttpResponse;
import yonky.mynews.model.prefs.PreferencesHelper;

/**
 * Created by Administrator on 2017/10/13.
 */

public class DataManager implements HttpHelper,DBHelper,PreferencesHelper {
    HttpHelper mHttpHelper;
    DBHelper mDbHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper,DBHelper dbHelper,PreferencesHelper preferencesHelper){
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
        mPreferencesHelper =preferencesHelper;
    }

    @Override
    public void insertNewsId(int id) {
        mDbHelper.insertNewsId(id);
    }

    @Override
    public boolean queryNewsId(int id) {
        return mDbHelper.queryNewsId(id);
    }

//知乎
    @Override
    public Flowable<DailyListBean> fetchDailyListInfo() {
        return mHttpHelper.fetchDailyListInfo();
    }

    @Override
    public Flowable<DailyBeforeListBean> fetchDailyBeforeListInfo(String date) {
        return mHttpHelper.fetchDailyBeforeListInfo(date);
    }
    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mHttpHelper.fetchWelcomeInfo(res);
    }

    @Override
    public void insertLikeBean(RealmLikeBean bean) {
        mDbHelper.insertLikeBean(bean);
    }

    @Override
    public void deleteLikeBean(String id) {
        mDbHelper.deleteLikeBean(id);
    }

    @Override
    public boolean queryLikeId(String id) {
        return mDbHelper.queryLikeId(id);
    }

    @Override
    public List<RealmLikeBean> getLikeList() {
        return mDbHelper.getLikeList();
    }

    @Override
    public void changeLikeTime(String id, long time, boolean isPlus) {
        mDbHelper.changeLikeTime(id,time,isPlus);
    }

    @Override
    public Flowable<ZhihuDetailBean> fetchDetailInfo(int id) {
        return mHttpHelper.fetchDetailInfo(id);
    }

    @Override
    public Flowable<ThemeListBean> fetchThemeListInfo() {
        return mHttpHelper.fetchThemeListInfo();
    }

    @Override
    public Flowable<ThemeChildListBean> fetchThemeChildListInfo(int id) {
        return mHttpHelper.fetchThemeChildListInfo(id);
    }

    @Override
    public Flowable<SectionListBean> fetchSectionListInfo() {
        return mHttpHelper.fetchSectionListInfo();
    }

    @Override
    public Flowable<SectionChildListBean> fetchSectionChildListInfo(int id) {
        return mHttpHelper.fetchSectionChildListInfo(id);
    }

    @Override
    public Flowable<HotListBean> fetchHotListInfo() {
        return mHttpHelper.fetchHotListInfo();
    }
//    微信

    @Override
    public Flowable<WXItemBean> fetchWXDataInfo(int num, int page) {
        return mHttpHelper.fetchWXDataInfo(num,page);
    }

    @Override
    public Flowable<WXItemBean> fetchWechatSearchListInfo(int num, int page, String word) {
        return mHttpHelper.fetchWechatSearchListInfo(num,page,word);
    }

    @Override
    public int getCurrentItem() {
        return mPreferencesHelper.getCurrentItem();
    }

    @Override
    public void setCurrentItem(int item) {
        mPreferencesHelper.setCurrentItem(item);
    }

//    干货

    @Override
    public Flowable<GankListBean> fetchGankDataInfo(String tech, int num, int page) {
        return mHttpHelper.fetchGankDataInfo(tech,num,page);
    }

    @Override
    public Flowable<GankListBean> fetchGirlListInfo(int num, int page) {
        return mHttpHelper.fetchGirlListInfo(num,page);
    }

    @Override
    public Flowable<GankListBean> fetchRandomGirlInfo(int num) {
        return mHttpHelper.fetchRandomGirlInfo(num);
    }

    @Override
    public Flowable<GankListBean> fetchGankSearchList(String query, String type, int num, int page) {
        return mHttpHelper.fetchGankSearchList(query,type,num,page);
    }

    //    掘金


    @Override
    public Flowable<GoldHttpResponse<List<GoldListBean>>> fetchGoldList(String type, int num, int page) {
        return mHttpHelper.fetchGoldList(type,num,page);
    }
}
