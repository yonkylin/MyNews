package yonky.mynews.presenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.main.WelcomeContract;
import yonky.mynews.model.DataManager;
import yonky.mynews.model.bean.WelcomeBean;
import yonky.mynews.util.RxUtil;

/**
 * Created by Administrator on 2017/10/13.
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {
    private static  final String RES = "1080*1776";
    private static final int COUNT_DOWN_TIME=2200;
    private DataManager mDataManager;

    @Inject
    public WelcomePresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public void getWelcomeData() {
        addSubscribe(mDataManager.fetchWelcomeInfo(RES)
        .compose(RxUtil.<WelcomeBean>rxSchedulerHelper())
        .subscribe(new Consumer<WelcomeBean>() {
            @Override
            public void accept(@NonNull WelcomeBean welcomeBean) throws Exception {
                mView.jumpToMain();
//                mView.showContent(welcomeBean);
//                startCountDown();
            }
        },new Consumer<Throwable>(){
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                mView.jumpToMain();
            }
        })
        );
    }
    private void startCountDown(){
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
        .compose(RxUtil.<Long>rxSchedulerHelper())
        .subscribe(new Consumer<Long>(){
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                mView.jumpToMain();
            }
        }));

    }
}
