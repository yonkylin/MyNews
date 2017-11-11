package yonky.mynews.ui.zhihu.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.mynews.R;
import yonky.mynews.base.RootActivity;
import yonky.mynews.base.contract.zhihu.SectionChildContract;
import yonky.mynews.model.bean.SectionChildListBean;
import yonky.mynews.presenter.zhihu.SectionChildPresenter;
import yonky.mynews.ui.zhihu.adapter.SectionChildAdapter;

import static yonky.mynews.app.Constants.IT_ZHIHU_SECTION_ID;
import static yonky.mynews.app.Constants.IT_ZHIHU_SECTION_TITLE;

/**
 * Created by Administrator on 2017/11/2.
 */

public class SectionActivity extends RootActivity<SectionChildPresenter> implements SectionChildContract.View {
    @BindView(R.id.view_main)
    RecyclerView rvSectionContent;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    List<SectionChildListBean.StoriesBean> mList;
    SectionChildAdapter mAdapter;

    int id;
    String  title;
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_section;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Intent intent = getIntent();
        id = intent.getIntExtra(IT_ZHIHU_SECTION_ID,0);
        title = intent.getStringExtra(IT_ZHIHU_SECTION_TITLE);
        setToolBar(mToolBar,title);
        mList = new ArrayList<>();
        mAdapter = new SectionChildAdapter(mContext,mList);
        rvSectionContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvSectionContent.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getSectionChildData(id);
            }
        });

        mPresenter.getSectionChildData(id);
        stateLoading();
    }

    @Override
    public void showContent(SectionChildListBean sectionChildListBean) {
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        mList.clear();
        mList.addAll(sectionChildListBean.getStories());
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
