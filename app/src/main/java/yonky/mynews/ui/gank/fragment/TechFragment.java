package yonky.mynews.ui.gank.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import yonky.mynews.R;
import yonky.mynews.app.Constants;
import yonky.mynews.base.RootFragment;
import yonky.mynews.base.contract.gank.TechContract;
import yonky.mynews.component.ImageLoader;
import yonky.mynews.model.bean.GankListBean;
import yonky.mynews.presenter.gank.TechPresenter;
import yonky.mynews.ui.TechDetailActivity;
import yonky.mynews.ui.gank.adapter.TechAdapter;
import yonky.mynews.util.SystemUtil;

/**
 * Created by Administrator on 2017/11/6.
 */

public class TechFragment extends RootFragment<TechPresenter> implements TechContract.View{
    @BindView(R.id.view_main)
    RecyclerView rvTechContent;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.iv_tech_blur)
    ImageView ivBlur;
    @BindView(R.id.iv_tech_origin)
    ImageView ivOrigin;
    @BindView(R.id.tv_tech_copyright)
    TextView tvCopyrigth;
    @BindView(R.id.tech_appbar)
    AppBarLayout appbar;

    List<GankListBean.ResultsBean> mList;
    TechAdapter mAdapter;

    boolean isLoadingMore = false;
    String tech;
    int type;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tech;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mPresenter.getGirlImage();
        mList = new ArrayList<>();
        tech = getArguments().getString(Constants.IT_GANK_TYPE);
        type =getArguments().getInt(Constants.IT_GANK_TYPE_CODE);
        mAdapter = new TechAdapter(mContext,mList,tech);
        rvTechContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvTechContent.setAdapter(mAdapter);
        stateLoading();




        mPresenter.getGankData(tech,type);
        mAdapter.setOnItemClickListener(new TechAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                TechDetailActivity.launch (new TechDetailActivity.Builder()
                        .setContext(mContext)
                        .setId(mList.get(position).get_id())
                        .setTitle(mList.get(position).getDesc())
                        .setUrl(mList.get(position).getUrl())
                        .setType(type)
                        .setAnimConfig(mActivity,view)
                );
            }
        });
        rvTechContent.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager)rvTechContent.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = rvTechContent.getLayoutManager().getItemCount();
                if(lastVisibleItem>=totalItemCount-2 &&dy>0){
                    if(!isLoadingMore){
                        isLoadingMore = true;
                        mPresenter.getMoreGankData(tech);
                    }
                }

            }
        });
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset>=0){
                swipeRefresh.setEnabled(true);
                }else{
                    swipeRefresh.setEnabled(false);
                    float rate=(float)(SystemUtil.dp2px(mContext,256)+verticalOffset *2)/ SystemUtil.dp2px(mContext,256);
                    if(rate >=0)
                     ivOrigin.setAlpha(rate);
                }
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getGankData(tech,type);
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
    public void showContent(List<GankListBean.ResultsBean> list) {
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<GankListBean.ResultsBean> list) {
        stateMain();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        isLoadingMore = false;
    }

    @Override
    public void showGirlImage(String url, String copyright) {
        ImageLoader.load(mContext,url,ivOrigin);
        Glide.with(mContext)
                .load(url)
                .bitmapTransform(new BlurTransformation(mContext))
                .into(ivBlur);
        tvCopyrigth.setText(String.format("by: %s",copyright));
    }
}
