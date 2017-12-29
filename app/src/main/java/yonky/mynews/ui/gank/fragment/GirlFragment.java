package yonky.mynews.ui.gank.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.mynews.R;
import yonky.mynews.app.Constants;
import yonky.mynews.base.BaseFragment;
import yonky.mynews.base.contract.gank.GirlContract;
import yonky.mynews.di.component.FragmentComponent;
import yonky.mynews.model.bean.GankListBean;
import yonky.mynews.model.bean.SectionChildListBean;
import yonky.mynews.presenter.gank.GirlPresenter;
import yonky.mynews.ui.gank.activity.GirlDetailActivity;
import yonky.mynews.ui.gank.adapter.GirlAdapter;
import yonky.mynews.ui.zhihu.activity.CalendarActivity;

/**
 * Created by Administrator on 2017/11/7.
 */

public class GirlFragment extends BaseFragment<GirlPresenter> implements GirlContract.View {
    @BindView(R.id.view_main)
    RecyclerView rvGirlContent;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private static final int SPAN_COUNT = 2;

    GirlAdapter mAdapter;
    List<GankListBean.ResultsBean> mList;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    private boolean isLoadingMore = false;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_common_list;
    }

    @Override
    protected void initEventAndData() {
        mList = new ArrayList<>();
        mAdapter = new GirlAdapter(mContext,mList);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(SPAN_COUNT,StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        staggeredGridLayoutManager.setItemPrefetchEnabled(false);
        rvGirlContent.setLayoutManager(staggeredGridLayoutManager);
        rvGirlContent.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getGirlData();
            }
        });
        rvGirlContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] visibleItems = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
                int lastItem = Math.max(visibleItems[0],visibleItems[1]);
                if(lastItem>mAdapter.getItemCount()-5 &&!isLoadingMore &&dy>0){
                    isLoadingMore = true;
                    mPresenter.getMoreGirlData();
                }
            }
        });
        mAdapter.setOnItemClickListener(new GirlAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, GirlDetailActivity.class);
                intent.putExtra(Constants.IT_GANK_GRIL_URL,mList.get(position).getUrl());
                intent.putExtra(Constants.IT_GANK_GRIL_ID,mList.get(position).get_id());
                if(Build.VERSION.SDK_INT>=21){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity,view,"shareView");
                    mContext.startActivity(intent,options.toBundle());
                }else {
                    startActivity(intent);
                }

            }
        });
        stateLoading();
        mPresenter.getGirlData();
    }

    @Override
    public void stateError() {
        super.stateError();
        if(swipeRefresh.isRefreshing())
            swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showContent(List<GankListBean.ResultsBean> list) {
        if(swipeRefresh.isRefreshing())
            swipeRefresh.setRefreshing(false);
        stateMain();
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<GankListBean.ResultsBean> list) {
        isLoadingMore = false;
        mList.addAll(list);
        for(int i = mList.size()-GirlPresenter.NUM_OF_PAGE;i<mList.size();i++){
            mAdapter.notifyItemInserted(i);
        }
    }
}
