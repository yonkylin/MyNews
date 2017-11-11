package yonky.mynews.presenter.gank;

import android.util.Log;

import javax.inject.Inject;

import yonky.mynews.app.Constants;
import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.gank.TechContract;
import yonky.mynews.model.DataManager;
import yonky.mynews.model.bean.GankListBean;
import yonky.mynews.ui.gank.fragment.GankMainFragment;
import yonky.mynews.util.RxUtil;
import yonky.mynews.widget.CommonSubscriber;

/**
 * Created by Administrator on 2017/11/6.
 */

public class TechPresenter extends RxPresenter<TechContract.View> implements TechContract.Presenter {
    private DataManager mDataManager;
    private static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;
    private String currentTech = GankMainFragment.tabTitle[0];
    private int currentType = Constants.TYPE_ANDROID;

    @Inject
    public TechPresenter (DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public void getGankData(String tech, int type) {
        currentPage = 1;
        currentTech = tech;
        currentType = type;
//        Log.e("yonkylin",tech);
        addSubscribe(mDataManager.fetchGankDataInfo(tech,NUM_OF_PAGE,currentPage)
                .compose(RxUtil.<GankListBean> rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<GankListBean>(mView){
                    @Override
                    public void onNext(GankListBean gankListBean) {
                        mView.showContent(gankListBean.getResults());
                    }
                })
        );
    }

    @Override
    public void getMoreGankData(String tech) {

        addSubscribe(mDataManager.fetchGankDataInfo(tech,NUM_OF_PAGE,++currentPage)
                .compose(RxUtil.<GankListBean> rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<GankListBean>(mView){
                    @Override
                    public void onNext(GankListBean gankListBean) {
                        mView.showMoreContent(gankListBean.getResults());
                    }
                })
        );
    }

    @Override
    public void getGirlImage() {
        addSubscribe(mDataManager.fetchRandomGirlInfo(1)
                .compose(RxUtil.<GankListBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<GankListBean>(mView,"加载封面失败", false) {
                    @Override
                    public void onNext(GankListBean gankListBean) {
                        mView.showGirlImage(gankListBean.getResults().get(0).getUrl(), gankListBean.getResults().get(0).getWho());
                    }
                })

        );
    }
}
