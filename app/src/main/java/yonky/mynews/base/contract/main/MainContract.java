package yonky.mynews.base.contract.main;

import com.tbruyelle.rxpermissions2.RxPermissions;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;

/**
 * Created by Administrator on 2017/10/14.
 */

public interface MainContract {
    interface View extends BaseView {
//        void showUpdateDialog(String versionContent);
        void startDownloadservice();
    }

    interface Presenter extends BasePresenter<View>{

//        void checkVersion(String currentVersion);

        void checkPermissions(RxPermissions rxPermissions);

//        void setNightModeState(boolean b);

        void setCurrentItem(int index);

        int getCurrentItem();

//        void setVersionPoint(boolean b);
//        boolean getVersionPoint();

    }
}
