package yonky.mynews.presenter.wechat;



import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import yonky.mynews.app.Constants;
import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.wechat.WechatContract;
import yonky.mynews.component.RxBus;
import yonky.mynews.model.DataManager;
import yonky.mynews.model.bean.WXItemBean;
import yonky.mynews.model.event.SearchEvent;
import yonky.mynews.util.RxUtil;
import yonky.mynews.widget.CommonSubscriber;

/**
 * Created by Administrator on 2017/11/3.
 */

public class WechatPresenter extends RxPresenter<WechatContract.View> implements WechatContract.Presenter {

    private  static final int NUM_OF_PAGE=20;
    private int currentPage =1;
    private String queryStr = null;

    private DataManager mDataManager;

    @Inject
    public WechatPresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(WechatContract.View view) {
        super.attachView(view);
//        registerEvent();
    }

  /*  private void registerEvent(){
        addSubscribe(RxBus.getDefault().toFlowable(SearchEvent.class)
                .compose(RxUtil.<SearchEvent>rxSchedulerHelper())
                .filter(new Predicate<SearchEvent>() {
                    @Override
                    public boolean test(@NonNull SearchEvent searchEvent) throws Exception {
                        return searchEvent.getType() == Constants.TYPE_WECHAT;
                    }
                })
                .map(new Function<SearchEvent,String>() {
                    @Override
                    public String apply(@NonNull SearchEvent searchEvent) throws Exception {
                        return searchEvent.getQuery();
                    }
                })
                .subscribeWith(new CommonSubscriber<String>(mView,"搜索失败ヽ(≧Д≦)ノ"){
                    @Override
                    public void onNext(String s) {
                        queryStr = s;
//                        getSearchWechatData(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        registerEvent();
                    }
                })
        );
    }*/

    @Override
    public void getWechatData() {
        queryStr = null;
        currentPage = 1;
        addSubscribe(mDataManager.fetchWXDataInfo(NUM_OF_PAGE,currentPage)
                .compose(RxUtil.<WXItemBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<WXItemBean>(mView){
                    @Override
                    public void onNext(WXItemBean wxItemBean) {
                        mView.showContent(wxItemBean.getNewslist());
                    }
                })

        );
    }

    @Override
    public void getMoreWechatData() {
        Flowable<WXItemBean> observable;
//        if(queryStr != null){
//            observable = mDataManager
//        }else {
            observable = mDataManager.fetchWXDataInfo(NUM_OF_PAGE,++currentPage);
//        }
        addSubscribe(observable
                .compose(RxUtil.<WXItemBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<WXItemBean>(mView) {
                                   @Override
                                   public void onNext(WXItemBean wxItemBean) {
                                       mView.showMoreContent(wxItemBean.getNewslist());
                                   }
                               })
        );
    }
}
