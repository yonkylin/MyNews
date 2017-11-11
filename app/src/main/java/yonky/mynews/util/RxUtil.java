package yonky.mynews.util;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import yonky.mynews.model.http.exception.ApiException;
import yonky.mynews.model.http.response.GoldHttpResponse;

/**
 * Created by Administrator on 2017/10/13.
 */

public class RxUtil {
    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T>FlowableTransformer<T,T> rxSchedulerHelper(){
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
/**
 * 统一返回结果处理
 * @param <T>
 * @return
 */
public static <T> FlowableTransformer<GoldHttpResponse<T>,T>handleGoldResult(){
    return new FlowableTransformer<GoldHttpResponse<T>, T>() {
        @Override
        public Flowable<T> apply(Flowable<GoldHttpResponse<T>> httpResponseFlowable) {
            return httpResponseFlowable.flatMap(new Function<GoldHttpResponse<T>, Flowable<T>>() {
                @Override
                public Flowable<T> apply(@NonNull GoldHttpResponse<T> tGoldHttpResponse) throws Exception {
                    if(tGoldHttpResponse.getResults()!=null){
                        return createData(tGoldHttpResponse.getResults());
                    }else{
                        return Flowable.error(new ApiException("服务器返回"));
                    }
                }
            });
        }
    };
}

    /**
     * 生成Flowable
     * @param <T>
     * @return
     */
public static <T> Flowable<T> createData(final T t){
    return Flowable.create(new FlowableOnSubscribe<T>() {
        @Override
        public void subscribe(FlowableEmitter<T> e) throws Exception {
            try{
                e.onNext(t);
                e.onComplete();
            }catch (Exception exception){
                e.onError(exception);
            }
        }
    }, BackpressureStrategy.BUFFER);
}

}
