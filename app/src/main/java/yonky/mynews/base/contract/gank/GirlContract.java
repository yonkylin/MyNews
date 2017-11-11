package yonky.mynews.base.contract.gank;

import java.util.List;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;
import yonky.mynews.model.bean.GankListBean;

/**
 * Created by Administrator on 2017/11/7.
 */

public interface GirlContract {
    interface  View extends BaseView {
        void showContent(List<GankListBean.ResultsBean> list);
        void showMoreContent(List<GankListBean.ResultsBean> list);
    }
    interface Presenter extends BasePresenter<View>{
        void getGirlData();
        void getMoreGirlData();
    }
}
