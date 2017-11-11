package yonky.mynews.ui.wechat.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.mynews.R;
import yonky.mynews.base.RootFragment;
import yonky.mynews.base.contract.wechat.WechatContract;
import yonky.mynews.model.bean.WXItemBean;
import yonky.mynews.presenter.wechat.WechatPresenter;
import yonky.mynews.ui.wechat.adapter.WechatAdapter;

/**
 * Created by Administrator on 2017/11/3.
 */

public class WechatMainFragment extends RootFragment<WechatPresenter> implements WechatContract.View {
    @BindView(R.id.view_main)
    RecyclerView rvWechatList;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    WechatAdapter mAdapter;
    List<WXItemBean.NewslistBean> mList;

    boolean isLoadingMore = false;
    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.view_common_list;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mList = new ArrayList<>();
        mAdapter = new WechatAdapter(mContext,mList);
        rvWechatList.setLayoutManager(new LinearLayoutManager(mContext));
        rvWechatList.setAdapter(mAdapter);
        rvWechatList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager)rvWechatList.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = rvWechatList.getLayoutManager().getItemCount();
                if(lastVisibleItem>=totalItemCount-2 && dy>0){
                    //还剩两个时加载更多
                    if(!isLoadingMore){
                        isLoadingMore = true;
                        mPresenter.getMoreWechatData();
                    }
                }
            }
        });


        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getWechatData();
            }
        });
        stateLoading();
        mPresenter.getWechatData();
    }

    @Override
    public void showContent(List<WXItemBean.NewslistBean> list) {
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<WXItemBean.NewslistBean> list) {
        stateMain();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        isLoadingMore = false;

    }

    @Override
    public void stateError() {
        super.stateError();
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
    }
}
