package yonky.mynews.presenter.main;

import javax.inject.Inject;

import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.main.SettingContract;
import yonky.mynews.model.DataManager;

/**
 * Created by Administrator on 2018/1/2.
 */

public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public SettingPresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public boolean getAutoCacheState() {
        return mDataManager.getAutoCacheState();
    }

    @Override
    public void setAutoCacheState(boolean state) {
        mDataManager.setAutoCacheState(state);
    }

    @Override
    public boolean getNoImageState() {
        return mDataManager.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean state) {
        mDataManager.setNoImageState(state);
    }


}
