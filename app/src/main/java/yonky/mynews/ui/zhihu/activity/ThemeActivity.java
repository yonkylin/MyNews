package yonky.mynews.ui.zhihu.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import yonky.mynews.R;
import yonky.mynews.app.Constants;
import yonky.mynews.base.RootActivity;
import yonky.mynews.base.contract.zhihu.ThemeChildContract;
import yonky.mynews.base.contract.zhihu.ThemeContract;
import yonky.mynews.component.ImageLoader;
import yonky.mynews.model.bean.ThemeChildListBean;
import yonky.mynews.presenter.zhihu.ThemeChildPresenter;
import yonky.mynews.ui.zhihu.adapter.ThemeChildAdapter;
import yonky.mynews.util.SystemUtil;

/**
 * Created by Administrator on 2017/11/1.
 */

public class ThemeActivity extends RootActivity<ThemeChildPresenter> implements ThemeChildContract.View {
    @BindView(R.id.view_main)
    RecyclerView rvThemeChildList;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_theme_child_blur)
    ImageView ivBlur;
    @BindView(R.id.iv_theme_child_origin)
    ImageView ivOrigin;
    @BindView(R.id.tv_theme_child_des)
    TextView tvDes;
    @BindView(R.id.theme_child_appbar)
    AppBarLayout appbar;

    ThemeChildAdapter mAdapter;
    List<ThemeChildListBean.StoriesBean> mList;
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_theme;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Intent intent = getIntent();
        final int id = intent.getExtras().getInt(Constants.IT_ZHIHU_THEME_ID);
        mList = new ArrayList<>();
        mAdapter = new ThemeChildAdapter(mContext,mList);
        rvThemeChildList.setLayoutManager(new LinearLayoutManager(mContext));
        rvThemeChildList.setAdapter(mAdapter);
        stateLoading();
        mPresenter.getThemeChildData(id);
        mAdapter.setOnItemClickListener(new ThemeChildAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View shareView) {
                mPresenter.insertReadToDB(mList.get(position).getId());
                mAdapter.setReadState(position,true);
                mAdapter.notifyItemChanged(position);
                Intent intent = new Intent();
                intent.setClass(mContext,ZhihuDetailActivity.class);
                intent.putExtra(Constants.IT_ZHIHU_DETAIL_ID,mList.get(position).getId());
                if(shareView !=null && Build.VERSION.SDK_INT>21){
                    mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mContext,shareView,"shareView").toBundle());
                }else{
                    startActivity(intent);
                }

            }
        });

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset>=0){
                    swipeRefresh.setEnabled(true);
                }else{
                    swipeRefresh.setEnabled(false);
                    float rate =(float)(SystemUtil.dp2px(mContext,256)+verticalOffset*2)/SystemUtil.dp2px(mContext,256);
                    if(rate>=0)
                        ivOrigin.setAlpha(rate);
                }
            }
        });


        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getThemeChildData(id);
            }
        });


    }


    @Override
    public void stateError() {
        super.stateError();
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showContent(ThemeChildListBean themeChildListBean) {
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        setToolBar(mToolbar,themeChildListBean.getName());
        mList.clear();
        mList.addAll(themeChildListBean.getStories());
        mAdapter.notifyDataSetChanged();
        ImageLoader.load(mContext,themeChildListBean.getBackground(),ivOrigin);
//        Glide.with(mContext)
//                .load(themeChildListBean.getBackground())
//                .bitmapTransform(new BlurTransformation(mContext))
//                .into(ivBlur);
        tvDes.setText(themeChildListBean.getDescription());
    }
}
