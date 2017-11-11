package yonky.mynews.base.contract.zhihu;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;
import yonky.mynews.model.bean.SectionChildListBean;

/**
 * Created by Administrator on 2017/11/2.
 */

public interface SectionChildContract {
    interface View extends BaseView {
        void showContent(SectionChildListBean sectionChildListBean);
    }
    interface Presenter extends BasePresenter<View>{
        void getSectionChildData(int id);
        void insertReadToData(int id);
    }
}
