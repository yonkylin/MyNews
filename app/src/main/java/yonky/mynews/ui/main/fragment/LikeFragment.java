package yonky.mynews.ui.main.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.mynews.R;
import yonky.mynews.base.BaseFragment;
import yonky.mynews.base.contract.main.LikeContract;
import yonky.mynews.model.bean.RealmLikeBean;
import yonky.mynews.presenter.main.LikePresenter;
import yonky.mynews.ui.main.adapter.LikeAdapter;

/**
 * Created by Administrator on 2017/12/28.
 */

public class LikeFragment extends BaseFragment<LikePresenter> implements LikeContract.View {
    @BindView(R.id.rv_like_list)
    RecyclerView rvLikeList;

    LikeAdapter mAdapter;
    List<RealmLikeBean> mList;
//    DefaultItemTouchHelpCallback mCallback;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_like;
    }


    @Override
    protected void initEventAndData() {
        mList = new ArrayList<>();
        mAdapter = new LikeAdapter(mContext,mList);
        rvLikeList.setLayoutManager(new LinearLayoutManager(mContext));
//        mCallback = new DefaultItemTouchHelpCallback()
        rvLikeList.setAdapter(mAdapter);
        mPresenter.getLikeData();
    }
    @Override
    public void showContent(List<RealmLikeBean> list) {
        mList.clear();
        mList.addAll(list);
//        Log.e("yonkylin","type:"+mList.get(0).getType());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isInited){
            mPresenter.getLikeData();
        }
    }
}
