package yonky.mynews.presenter.main;

import android.Manifest;

import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.main.MainContract;
import yonky.mynews.component.RxBus;
import yonky.mynews.model.DataManager;

/**
 * Created by Administrator on 2017/10/14.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements  MainContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
//        registerEvent();
    }

    @Override
    public void checkPermissions(RxPermissions rxPermissions) {
        addSubscribe(rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                if(aBoolean){
                    mView.startDownloadservice();
                }else{
                    mView.showErrorMsg("下载应用需要文件写入权限");
                }

            }
        }));
    }

    @Override
    public void setCurrentItem(int index) {
        mDataManager.setCurrentItem(index);
    }

    @Override
    public int getCurrentItem() {
        return mDataManager.getCurrentItem();
    }
}
