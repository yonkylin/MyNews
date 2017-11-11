package yonky.mynews.presenter.zhihu;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.zhihu.HotContract;
import yonky.mynews.model.DataManager;
import yonky.mynews.model.bean.HotListBean;
import yonky.mynews.util.RxUtil;
import yonky.mynews.widget.CommonSubscriber;

/**
 * Created by Administrator on 2017/11/3.
 */

public class HotPresenter extends RxPresenter<HotContract.View> implements HotContract.Presenter{
    private DataManager mDataManager;

    @Inject
    public HotPresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public void getHotData() {
        addSubscribe(mDataManager.fetchHotListInfo()
                .compose(RxUtil.<HotListBean>rxSchedulerHelper())
                .map(new Function<HotListBean, HotListBean>() {
                    @Override
                    public HotListBean apply(@NonNull HotListBean hotListBean) throws Exception {
                        List<HotListBean.RecentBean> list = hotListBean.getRecent();
                        for(HotListBean.RecentBean item:list){
                            item.setReadState(mDataManager.queryNewsId(item.getNews_id()));
                        }
                        return hotListBean;
                    }
                }).subscribeWith(new CommonSubscriber<HotListBean>(mView){
                    @Override
                    public void onNext(HotListBean hotListBean) {
                        mView.showContent(hotListBean);
                    }
                })

        );
    }

    @Override
    public void insertReadToDB(int id) {
        mDataManager.insertNewsId(id);
    }
}
