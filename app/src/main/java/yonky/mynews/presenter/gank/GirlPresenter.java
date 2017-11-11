package yonky.mynews.presenter.gank;

import javax.inject.Inject;

import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.gank.GirlContract;
import yonky.mynews.model.DataManager;
import yonky.mynews.model.bean.GankListBean;
import yonky.mynews.util.RxUtil;
import yonky.mynews.widget.CommonSubscriber;

/**
 * Created by Administrator on 2017/11/7.
 */

public class GirlPresenter extends RxPresenter<GirlContract.View> implements GirlContract.Presenter {
    private DataManager mDataManager;
    public static final int NUM_OF_PAGE = 20;
    private int currentPage = 1;

    @Inject
    public GirlPresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public void getGirlData() {
        currentPage = 1;
        addSubscribe(mDataManager.fetchGirlListInfo(NUM_OF_PAGE,currentPage)
                .compose(RxUtil.<GankListBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<GankListBean>(mView){
                    @Override
                    public void onNext(GankListBean gankListBean) {
                        mView.showContent(gankListBean.getResults());
                    }
                })
        );
    }

    @Override
    public void getMoreGirlData() {
        addSubscribe(mDataManager.fetchGirlListInfo(NUM_OF_PAGE,++currentPage)
                .compose(RxUtil.<GankListBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<GankListBean>(mView){
                    @Override
                    public void onNext(GankListBean gankListBean) {
                        mView.showMoreContent(gankListBean.getResults());
                    }
                })
        );

    }
}
