package yonky.mynews.model.http.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import yonky.mynews.model.bean.GankListBean;

/**
 * Created by Administrator on 2017/11/6.
 */

public interface GankApis {
    String HOST="http://gank.io/api/";

//    技术文章列表
    @GET("data/{tech}/{num}/{page}")
    Flowable<GankListBean> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);

//    妹纸列表
    @GET("data/福利/{num}/{page}")
    Flowable<GankListBean> getGirlList(@Path("num") int num,@Path("page") int page);

//    随机妹纸图
    @GET("random/data/福利/{num}")
    Flowable<GankListBean> getRandomGirl(@Path("num")int num);

    //搜索
    @GET("search/query/{query}/category/{type}/count/{count}/page/{page}")
    Flowable<GankListBean>getSearchList(@Path("query") String query, @Path("type") String type,@Path("count") int num , @Path("page") int page );
}
