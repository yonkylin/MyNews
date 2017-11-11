package yonky.mynews.model.http.api;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import yonky.mynews.model.bean.GoldListBean;
import yonky.mynews.model.http.response.GoldHttpResponse;

/**
 * Created by Administrator on 2017/11/7.
 */

public interface GoldApis {
    String HOST ="https://api.leancloud.cn/";

    @GET("1.1/classes/Entry")
    Flowable<GoldHttpResponse<List<GoldListBean>>> getGoldList(@Header("X-LC-Id")String id,
                                                               @Header("X-LC_Sign")String sign,
                                                               @Query("where")String where,
                                                               @Query("order")String order,
                                                               @Query("include")String include,
                                                               @Query("limit")int limit,
                                                               @Query("skip")int skip);

}
