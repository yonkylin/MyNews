package yonky.mynews.ui.zhihu.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.mynews.R;
import yonky.mynews.base.RootFragment;
import yonky.mynews.base.contract.zhihu.SectionContract;
import yonky.mynews.model.bean.SectionListBean;
import yonky.mynews.presenter.zhihu.SectionPresenter;
import yonky.mynews.ui.zhihu.adapter.SectionAdapter;

/**
 * Created by Administrator on 2017/11/2.
 */

public class SectionFragment extends RootFragment<SectionPresenter> implements SectionContract.View {
    @BindView(R.id.view_main)
    RecyclerView rvSectionList;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    List<SectionListBean.DataBean> mList;
    SectionAdapter mAdapter;

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
        mAdapter = new SectionAdapter(mContext,mList);
        rvSectionList.setLayoutManager(new GridLayoutManager(mContext,2));
        rvSectionList.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getSectionData();
            }
        });
        mPresenter.getSectionData();
        stateLoading();
    }

    @Override
    public void stateError() {
        super.stateError();
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showContent(SectionListBean sectionListBean) {
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        mList.clear();
        mList.addAll(sectionListBean.getData());
        mAdapter.notifyDataSetChanged();

    }
}
