package yonky.mynews.model.http.api;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yonky.mynews.model.bean.WXItemBean;

/**
 * Created by Administrator on 2017/11/3.
 */

public interface WechatApis {
    String HOST="http://api.tianapi.com/";
    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Flowable<WXItemBean> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);
    /**
     * 微信精选列表
     */
//    @GET("wxnew")
//    Flowable<WXHttpResponse<List<WXItemBean>>> getWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

}
