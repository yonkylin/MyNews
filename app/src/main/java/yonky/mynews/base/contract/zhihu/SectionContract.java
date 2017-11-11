package yonky.mynews.base.contract.zhihu;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;
import yonky.mynews.model.bean.SectionListBean;

/**
 * Created by Administrator on 2017/11/2.
 */

public interface SectionContract {
    interface View extends BaseView {
        void showContent(SectionListBean mList);
    }
    interface Presenter extends BasePresenter<View>{
        void getSectionData();
    }
}
