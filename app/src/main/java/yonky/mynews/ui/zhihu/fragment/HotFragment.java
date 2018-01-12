package yonky.mynews.ui.zhihu.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.mynews.R;
import yonky.mynews.app.Constants;
import yonky.mynews.base.RootFragment;
import yonky.mynews.base.contract.zhihu.HotContract;
import yonky.mynews.model.bean.HotListBean;
import yonky.mynews.presenter.zhihu.HotPresenter;
import yonky.mynews.ui.zhihu.activity.ZhihuDetailActivity;
import yonky.mynews.ui.zhihu.adapter.HotAdapter;

/**
 * Created by Administrator on 2017/11/3.
 */

public class HotFragment extends RootFragment<HotPresenter> implements HotContract.View {
    @BindView(R.id.view_main)
    RecyclerView rvHotContent;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    List<HotListBean.RecentBean> mList;
    HotAdapter mAdapter;

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
        super.initEventAndData();
        mList = new ArrayList<>();
        stateLoading();
        mAdapter = new HotAdapter(mContext,mList);
//        rvHotContent.setVisibility(View.INVISIBLE);
        rvHotContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvHotContent.setAdapter(mAdapter);
        mPresenter.getHotData();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getHotData();
            }
        });
        mAdapter.setOnItemclickListener(new HotAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                mPresenter.insertReadToDB(mList.get(position).getNews_id());
                mAdapter.setReadState(position,true);
                mAdapter.notifyItemChanged(position);
                Intent intent = new Intent();
                intent.setClass(mContext, ZhihuDetailActivity.class);
                intent.putExtra(Constants.IT_ZHIHU_DETAIL_ID,mList.get(position).getNews_id());
                if(Build.VERSION.SDK_INT>21){
                    ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(mActivity,view,"shareView");
                    startActivity(intent,options.toBundle());
                }else{
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void showContent(HotListBean hotListBean) {
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
//       rvHotContent.setVisibility(View.VISIBLE);
        mList.clear();
        mList.addAll(hotListBean.getRecent());
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void stateError() {
        super.stateError();
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
    }
}
