package yonky.mynews.base.contract.main;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;
import yonky.mynews.model.bean.WelcomeBean;

/**
 * Created by Administrator on 2017/10/13.
 */

public interface WelcomeContract {
    interface View extends BaseView {
        void showContent(WelcomeBean welcomeBean);

        void jumpToMain();

    }
    interface Presenter extends BasePresenter<View>{

        void getWelcomeData();
    }
}
