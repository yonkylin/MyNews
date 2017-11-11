package yonky.mynews.component;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import yonky.mynews.util.RxUtil;

/**
 * Created by Administrator on 2017/10/13.
 */

public class RxBus {
    // 主题
    private final FlowableProcessor<Object> bus;
    // PublishSubject只会把在订阅发生的时间点之后来自原始Flowable的数据发射给观察者
    private RxBus(){
        bus = PublishProcessor.create().toSerialized();
    }

    public static RxBus getDefault(){
        return RxBusHolder.sInstance;
    }

    private static class RxBusHolder{
        private static final RxBus sInstance = new RxBus();
    }
//提供了一个新的事件
    public  void post(Object o){
        bus.onNext(o);
    }
//    根据传递的eventType 类型，返回特定类型（eventtype）的被观察者
    public <T> Flowable<T> toFlowable(Class<T> eventType){
        return bus.ofType(eventType);
    }

//    封装默认订阅
    public <T> Disposable toDefaultFlowable(Class<T> eventType, Consumer<T> act){
        return bus.ofType(eventType).compose(RxUtil.<T>rxSchedulerHelper()).subscribe(act);
    }
}
