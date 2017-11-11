package yonky.mynews.base.contract.gold;

import java.util.List;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;
import yonky.mynews.model.bean.GoldListBean;

/**
 * Created by Administrator on 2017/11/7.
 */

public interface GoldContract {

    interface View extends BaseView {
        void showContent(List<GoldListBean> goldListBean);
        void showMoreContent(List<GoldListBean> goldMoreListBean,int start,int end);
    }
    interface Presenter extends BasePresenter<View>{
        void getGoldData(String type);
        void getMoreGoldData();
    }

}
