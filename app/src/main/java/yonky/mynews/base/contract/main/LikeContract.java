package yonky.mynews.base.contract.main;

import java.util.List;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;
import yonky.mynews.model.bean.RealmLikeBean;

/**
 * Created by Administrator on 2017/12/28.
 */

public interface LikeContract {
    interface View extends BaseView {
        void showContent(List<RealmLikeBean> mList);
    }
    interface Presenter extends BasePresenter<View>{
        void getLikeData();
        void deleteLikeData(String id);
        void changeLikeTime(String id,long time,boolean isPlus);
//        boolean getLikePoint();
//        void setLikePoint(boolean b);
    }
}
