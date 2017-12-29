package yonky.mynews.presenter.main;

import android.util.Log;

import javax.inject.Inject;

import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.main.LikeContract;
import yonky.mynews.model.DataManager;

/**
 * Created by Administrator on 2017/12/28.
 */

public class LikePresenter extends RxPresenter<LikeContract.View> implements LikeContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public LikePresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public void getLikeData() {
        mView.showContent(mDataManager.getLikeList());
    }

    @Override
    public void deleteLikeData(String id) {
        mDataManager.deleteLikeBean(id);
    }

    @Override
    public void changeLikeTime(String id, long time, boolean isPlus) {
        mDataManager.changeLikeTime(id,time,isPlus);
    }

//    @Override
//    public boolean getLikePoint() {
//        return mDataManager.getLikePoint();
//    }
//
//    @Override
//    public void setLikePoint(boolean b) {
//        mDataManager.setLikePoint(b);
//    }
}
