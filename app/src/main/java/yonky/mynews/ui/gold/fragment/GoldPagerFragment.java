package yonky.mynews.ui.gold.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.mynews.R;
import yonky.mynews.app.Constants;
import yonky.mynews.base.RootFragment;
import yonky.mynews.base.contract.gold.GoldContract;
import yonky.mynews.model.bean.GoldListBean;
import yonky.mynews.presenter.gold.GoldPresenter;
import yonky.mynews.ui.gold.adapter.GoldListAdapter;

/**
 * Created by Administrator on 2017/11/7.
 */

public class GoldPagerFragment extends RootFragment<GoldPresenter>
implements GoldContract.View{
    @BindView(R.id.view_main)
    RecyclerView rvGoldList;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private GoldListAdapter mAdapter;
    private boolean isLoadingMore = false;
    private String mType;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_page;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mType = getArguments().getString(Constants.IT_GOLD_TYPE);
        mAdapter = new GoldListAdapter(mContext,new ArrayList<GoldListBean>(),getArguments().getString(Constants.IT_GOLD_TYPE_STR));
        rvGoldList.setLayoutManager(new LinearLayoutManager(mContext));
        rvGoldList.setAdapter(mAdapter);


        stateLoading();
        mPresenter.getGoldData(mType);
    }
    @Override
    public void showContent(List<GoldListBean> goldListBean) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        mAdapter.updateData(goldListBean);
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void showMoreContent(List<GoldListBean> goldMoreListBean, int start, int end) {
        mAdapter.updateData(goldMoreListBean);
        mAdapter.notifyItemRangeInserted(start, end);
        isLoadingMore = false;
    }
}
