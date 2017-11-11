package yonky.mynews.base.contract.zhihu;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;
import yonky.mynews.model.bean.HotListBean;

/**
 * Created by Administrator on 2017/11/3.
 */

public interface HotContract {
    interface View extends BaseView{
        void showContent(HotListBean hotListBean);
    }
    interface Presenter extends BasePresenter<View>{
        void getHotData();
        void insertReadToDB(int id);
    }
}
