package yonky.mynews.base.contract.zhihu;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;
import yonky.mynews.model.bean.ThemeChildListBean;

/**
 * Created by Administrator on 2017/11/1.
 */

public interface ThemeChildContract {
    interface View extends BaseView {
        void showContent(ThemeChildListBean themeChildListBean);
    }
    interface Presenter extends BasePresenter<View>{
        void getThemeChildData(int id);
        void insertReadToDB(int id);
    }
}
