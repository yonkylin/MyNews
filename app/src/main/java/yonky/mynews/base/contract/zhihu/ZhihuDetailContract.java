package yonky.mynews.base.contract.zhihu;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;
import yonky.mynews.model.bean.ZhihuDetailBean;

/**
 * Created by Administrator on 2017/10/30.
 */

public interface ZhihuDetailContract {
    interface View extends BaseView {
        void showContent(ZhihuDetailBean zhihuDetailBean);

//        void showExtraInfo(DetailExtrBean detailExtrBean);

//        void setLikeButtonState(boolean isLiked);
    }
    interface  Presenter extends BasePresenter<View>{
        void getDetailData(int id);
//        void getExtraData(int id);
//        void insertLikeData();
//        void deleteLikeData();
//        void queryLikeData(int id);
//        boolean getNoImageState();
//        boolean getAutoCacheState();
    }
}
