package yonky.mynews.ui.zhihu.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.mynews.R;
import yonky.mynews.app.Constants;
import yonky.mynews.base.RootFragment;
import yonky.mynews.base.contract.zhihu.ThemeContract;
import yonky.mynews.model.bean.ThemeListBean;
import yonky.mynews.presenter.zhihu.ThemePresenter;
import yonky.mynews.ui.zhihu.activity.ThemeActivity;
import yonky.mynews.ui.zhihu.adapter.ThemeAdapter;

/**
 * Created by Administrator on 2017/11/1.
 */

public class ThemeFragment extends RootFragment<ThemePresenter> implements ThemeContract.View {
    @BindView(R.id.view_main)
    RecyclerView rvThemeList;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    ThemeAdapter mAdapter;
    List<ThemeListBean.OthersBean> mList = new ArrayList<>();

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
        mAdapter =new ThemeAdapter(mContext,mList);
        rvThemeList.setLayoutManager(new GridLayoutManager(mContext,2));
        rvThemeList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ThemeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent();
                intent.setClass(mContext,ThemeActivity.class);
                intent.putExtra(Constants.IT_ZHIHU_THEME_ID,id);
                mContext.startActivity(intent);
            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getThemeData();
            }
        });
        mPresenter.getThemeData();
        stateLoading();
    }

    @Override
    public void showContent(ThemeListBean themeListBean) {
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        mList.clear();
        mList.addAll(themeListBean.getOthers());
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
