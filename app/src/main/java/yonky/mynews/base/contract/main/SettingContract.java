package yonky.mynews.base.contract.main;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;

/**
 * Created by Administrator on 2018/1/2.
 */

public interface SettingContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<SettingContract.View>{
        void setNoImageState(boolean state);
        boolean getNoImageState();
        void setAutoCacheState(boolean state);
        boolean getAutoCacheState();
    }
}
