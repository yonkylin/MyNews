package yonky.mynews.base.contract.zhihu;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;
import yonky.mynews.model.bean.DailyBeforeListBean;
import yonky.mynews.model.bean.DailyListBean;

/**
 * Created by Administrator on 2017/10/19.
 */

public interface DailyContract {
    interface View extends BaseView {
        void showContent(DailyListBean info);
        void showMoreContent(String date , DailyBeforeListBean info);
        void doInterval(int currentCount);
    }
    interface Presenter extends BasePresenter<View>{
        void getDailyData();
        void getBeforeData(String date);
        void startInterval();
        void stopInterval();
        void insertReadToDB(int id);
    }
}
