package yonky.mynews.presenter.gold;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import yonky.mynews.base.RxPresenter;
import yonky.mynews.base.contract.gold.GoldContract;
import yonky.mynews.model.DataManager;
import yonky.mynews.model.bean.GoldListBean;
import yonky.mynews.model.http.response.GoldHttpResponse;
import yonky.mynews.util.RxUtil;

/**
 * Created by Administrator on 2017/11/7.
 */

public class GoldPresenter extends RxPresenter<GoldContract.View> implements GoldContract.Presenter {
    private static final int NUM_EACH_PAGE =20;

    private DataManager mDataManager;
    private List<GoldListBean> totalList = new ArrayList<>();

    private int currentPage = 0;
    private String mType;

    @Inject
    public GoldPresenter(DataManager mDataManager){
        this.mDataManager = mDataManager;
    }

    @Override
    public void getGoldData(String type) {
        mType = type;
        currentPage = 0;
        totalList.clear();
        Flowable<List<GoldListBean>>list = mDataManager.fetchGoldList(type,NUM_EACH_PAGE,currentPage)
                .compose(RxUtil.<GoldHttpResponse<List<GoldListBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GoldListBean>>handleGoldResult());
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE,-3);
    }

    @Override
    public void getMoreGoldData() {

    }
}
