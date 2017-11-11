package yonky.mynews.base.contract.gank;

import java.util.List;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;
import yonky.mynews.model.bean.GankListBean;

/**
 * Created by Administrator on 2017/11/6.
 */

public interface TechContract {
    interface View extends BaseView {
        void showContent(List<GankListBean.ResultsBean> mList);
        void showMoreContent(List<GankListBean.ResultsBean> mList);
        void showGirlImage(String url,String copyright);
    }
    interface Presenter extends BasePresenter<View>{
        void getGankData(String tech,int type);
        void getMoreGankData(String tech);
        void getGirlImage();
    }
}
