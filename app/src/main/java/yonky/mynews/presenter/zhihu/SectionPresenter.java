package yonky.mynews.presenter.zhihu;

import javax.inject.Inject;

import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.zhihu.SectionContract;
import yonky.mynews.model.DataManager;
import yonky.mynews.model.bean.SectionListBean;
import yonky.mynews.util.RxUtil;
import yonky.mynews.widget.CommonSubscriber;

/**
 * Created by Administrator on 2017/11/2.
 */

public class SectionPresenter extends RxPresenter<SectionContract.View>implements SectionContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public SectionPresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public void getSectionData() {
        addSubscribe(mDataManager.fetchSectionListInfo()
            .compose(RxUtil.<SectionListBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<SectionListBean>(mView){
                    @Override
                    public void onNext(SectionListBean sectionListBean) {
                        mView.showContent(sectionListBean);
                    }
                })
        );
    }
}
