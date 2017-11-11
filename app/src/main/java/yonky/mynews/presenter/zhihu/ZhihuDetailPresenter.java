package yonky.mynews.presenter.zhihu;

import javax.inject.Inject;

import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.zhihu.ZhihuDetailContract;
import yonky.mynews.model.DataManager;
import yonky.mynews.model.bean.ZhihuDetailBean;
import yonky.mynews.util.RxUtil;
import yonky.mynews.widget.CommonSubscriber;

/**
 * Created by Administrator on 2017/10/31.
 */

public class ZhihuDetailPresenter extends RxPresenter<ZhihuDetailContract.View> implements ZhihuDetailContract.Presenter{
    private DataManager mDataManager;
//    private ZhihuDetailBean mData;

    @Inject
    public ZhihuDetailPresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public void getDetailData(int id) {
        addSubscribe(mDataManager.fetchDetailInfo(id)
                    .compose(RxUtil.<ZhihuDetailBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<ZhihuDetailBean>(mView){
                    @Override
                    public void onNext(ZhihuDetailBean zhihuDetailBean) {
//                        mData = zhihuDetailBean;
                        mView.showContent(zhihuDetailBean);
                    }
                })
        );
    }

}
