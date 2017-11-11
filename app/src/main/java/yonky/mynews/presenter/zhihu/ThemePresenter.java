package yonky.mynews.presenter.zhihu;

import javax.inject.Inject;

import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.zhihu.ThemeContract;
import yonky.mynews.model.DataManager;
import yonky.mynews.model.bean.ThemeListBean;
import yonky.mynews.util.RxUtil;
import yonky.mynews.widget.CommonSubscriber;

/**
 * Created by Administrator on 2017/11/1.
 */

public class ThemePresenter extends RxPresenter<ThemeContract.View> implements ThemeContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public ThemePresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public void getThemeData() {
        mDataManager.fetchThemeListInfo()
                .compose(RxUtil.<ThemeListBean>rxSchedulerHelper())
                .subscribe(new CommonSubscriber<ThemeListBean>(mView) {
                    @Override
                    public void onNext(ThemeListBean themeListBean) {
                        mView.showContent(themeListBean);
                    }
                });
    }
}
