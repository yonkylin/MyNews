package yonky.mynews.ui.zhihu.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import yonky.mynews.R;
import yonky.mynews.app.Constants;
import yonky.mynews.base.RootFragment;
import yonky.mynews.base.contract.zhihu.DailyContract;
import yonky.mynews.component.RxBus;
import yonky.mynews.model.bean.DailyBeforeListBean;
import yonky.mynews.model.bean.DailyListBean;
import yonky.mynews.presenter.zhihu.DailyPresenter;
import yonky.mynews.ui.zhihu.activity.CalendarActivity;
import yonky.mynews.ui.zhihu.activity.ZhihuDetailActivity;
import yonky.mynews.ui.zhihu.adapter.DailyAdapter;
import yonky.mynews.util.DateUtil;

/**
 * Created by Administrator on 2017/10/19.
 */

public class DailyFragment extends RootFragment<DailyPresenter> implements DailyContract.View{

    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.view_main)
    RecyclerView rvDailyList;

    String currentDate;
    DailyAdapter mAdapter;
    List<DailyListBean.StoriesBean> mList= new ArrayList<>();

    boolean isDataReady = false;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        currentDate = DateUtil.getTomorrowDate();
        mAdapter =  new DailyAdapter(mContext,mList);
        mAdapter.setOnItemClickListener(new DailyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View shareview) {
                mPresenter.insertReadToDB(mList.get(position).getId());
                mAdapter.setReadState(position,true);
                if(mAdapter.getIsBefore()){
                    mAdapter.notifyItemChanged(position+1);
                }else{
                    mAdapter.notifyItemChanged(position+2);
                }
                Intent intent = new Intent();
                intent.setClass(mContext,ZhihuDetailActivity.class);
                intent.putExtra(Constants.IT_ZHIHU_DETAIL_ID,mList.get(position).getId());
                if(Build.VERSION.SDK_INT>21){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity,shareview,"shareView");
                    mContext.startActivity(intent,options.toBundle());
                }else{
                    mContext.startActivity(intent);
                }

            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(currentDate.equals(DateUtil.getTomorrowDate())){
                    mPresenter.getDailyData();
                }else{
                    int year = Integer.valueOf(currentDate.substring(0,4));
                    int month = Integer.valueOf(currentDate.substring(4,6));
                    int day = Integer.valueOf(currentDate.substring(6,8));
                    CalendarDay date  = CalendarDay.from(year,month-1,day);
                    RxBus.getDefault().post(date);
                }
            }
        });
        rvDailyList.setLayoutManager(new LinearLayoutManager(mContext));
        rvDailyList.setAdapter(mAdapter);
        stateLoading();
        mPresenter.getDailyData();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(isDataReady){
            mPresenter.startInterval();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stopInterval();
    }

    @Override
    public void showContent(DailyListBean info) {
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        mList = info.getStories();
        currentDate = String.valueOf(Integer.valueOf(info.getDate())+1);
        mAdapter.addDailyDate(info);
        isDataReady = true;
        mPresenter.startInterval();
    }

    @Override
    public void showMoreContent(String date, DailyBeforeListBean info) {
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        isDataReady = false;
        mPresenter.stopInterval();
        mList = info.getStories();
        currentDate= String.valueOf(Integer.valueOf(info.getDate()));
        mAdapter.addDailyBeforeDate(info);
    }

    @Override
    public void doInterval(int currentCount) {
        mAdapter.changeTopPager(currentCount);
    }
    @OnClick(R.id.fab_calender)
    void startCalender(){
        Intent it = new Intent();
        it.setClass(mContext,CalendarActivity.class);
        startActivity(it);
//        CircularAnimUtil.startACtivity(mActivity,it,fabCalender,R.color.fab_bg);
    }

    @Override
    public void stateError() {
        super.stateError();
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
    }
}
