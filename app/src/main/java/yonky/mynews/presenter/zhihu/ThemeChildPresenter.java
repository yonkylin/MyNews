package yonky.mynews.presenter.zhihu;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.zhihu.ThemeChildContract;
import yonky.mynews.model.DataManager;
import yonky.mynews.model.bean.ThemeChildListBean;
import yonky.mynews.util.RxUtil;
import yonky.mynews.widget.CommonSubscriber;

/**
 * Created by Administrator on 2017/11/1.
 */

public class ThemeChildPresenter extends RxPresenter<ThemeChildContract.View> implements ThemeChildContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public ThemeChildPresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }


    @Override
    public void getThemeChildData(int id) {
        addSubscribe(mDataManager.fetchThemeChildListInfo(id)
        .compose(RxUtil.<ThemeChildListBean>rxSchedulerHelper())
                .map(new Function<ThemeChildListBean, ThemeChildListBean>() {
                    @Override
                    public ThemeChildListBean apply(@NonNull ThemeChildListBean themeChildListBean) throws Exception {
                        List<ThemeChildListBean.StoriesBean> list = themeChildListBean.getStories();
                        for(ThemeChildListBean.StoriesBean item:list){
                            item.setReadState(mDataManager.queryNewsId(item.getId()));
                    }
                    return themeChildListBean;
                }
                })
                .subscribeWith(new CommonSubscriber<ThemeChildListBean>(mView){
                    @Override
                    public void onNext(ThemeChildListBean themeChildListBean) {
                        mView.showContent(themeChildListBean);
                    }
                })

        );
    }

    @Override
    public void insertReadToDB(int id) {
        mDataManager.insertNewsId(id);
    }
}
