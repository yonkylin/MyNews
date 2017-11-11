package yonky.mynews.base.contract.zhihu;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;
import yonky.mynews.model.bean.ThemeListBean;

/**
 * Created by Administrator on 2017/11/1.
 */

public interface ThemeContract {
    interface View extends BaseView{
        void showContent(ThemeListBean themeListBean);

    }
    interface Presenter extends BasePresenter<View>{
        void getThemeData();
    }
}
