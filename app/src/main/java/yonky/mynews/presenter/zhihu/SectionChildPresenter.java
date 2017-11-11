package yonky.mynews.presenter.zhihu;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.zhihu.SectionChildContract;
import yonky.mynews.model.DataManager;
import yonky.mynews.model.bean.SectionChildListBean;
import yonky.mynews.util.RxUtil;
import yonky.mynews.widget.CommonSubscriber;

/**
 * Created by Administrator on 2017/11/2.
 */

public class SectionChildPresenter extends RxPresenter<SectionChildContract.View> implements SectionChildContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public SectionChildPresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public void getSectionChildData(int id) {
        addSubscribe(mDataManager.fetchSectionChildListInfo(id)
                .compose(RxUtil.<SectionChildListBean>rxSchedulerHelper())
                .map(new Function<SectionChildListBean, SectionChildListBean>() {
                    @Override
                    public SectionChildListBean apply(@NonNull SectionChildListBean sectionChildListBean) throws Exception {
                        List<SectionChildListBean.StoriesBean> list = sectionChildListBean.getStories();
                        for(SectionChildListBean.StoriesBean item:list){
                            item.setReadState(mDataManager.queryNewsId(item.getId()));
                        }
                        return sectionChildListBean;
                    }
                })
                .subscribeWith(new CommonSubscriber<SectionChildListBean>(mView){
                    @Override
                    public void onNext(SectionChildListBean sectionChildListBean) {
                        mView.showContent(sectionChildListBean);
                    }
                })

        );
    }

    @Override
    public void insertReadToData(int id) {
        mDataManager.insertNewsId(id);
    }
}
