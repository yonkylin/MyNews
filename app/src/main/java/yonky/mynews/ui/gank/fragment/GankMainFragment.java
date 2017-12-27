package yonky.mynews.ui.gank.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.mynews.R;
import yonky.mynews.app.Constants;
import yonky.mynews.base.SimpleFragment;
import yonky.mynews.component.RxBus;
import yonky.mynews.model.event.SearchEvent;
import yonky.mynews.ui.gank.adapter.GankMainAdapter;

/**
 * Created by Administrator on 2017/11/6.
 */

public class GankMainFragment extends SimpleFragment {
    @BindView(R.id.tab_gank_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_gank_main)
    ViewPager mViewPager;

    public static String[] tabTitle= new String[]{"Android","iOS","前端","福利"};
    List<Fragment> fragments = new ArrayList<>();

    GankMainAdapter mAdapter;
    TechFragment androidFragment;
    TechFragment iOSFragment;
    TechFragment webFragment;
    GirlFragment girlFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank_main;
    }

    @Override
    protected void initEventAndData() {
        androidFragment = new TechFragment();
        iOSFragment = new TechFragment();
        webFragment = new TechFragment();
        girlFragment= new GirlFragment();

        Bundle androidBundle = new Bundle();
        androidBundle.putString(Constants.IT_GANK_TYPE,tabTitle[0]);
        androidBundle.putInt(Constants.IT_GANK_TYPE_CODE,Constants.TYPE_ANDROID);
        androidFragment.setArguments(androidBundle);
        Bundle iosBundle = new Bundle();
        iosBundle.putString(Constants.IT_GANK_TYPE,tabTitle[1]);
        iosBundle.putInt(Constants.IT_GANK_TYPE_CODE,Constants.TYPE_IOS);
        iOSFragment.setArguments(iosBundle);
        Bundle webBundle = new Bundle();
        webBundle.putString(Constants.IT_GANK_TYPE,tabTitle[2]);
        webBundle.putInt(Constants.IT_GANK_TYPE_CODE,Constants.TYPE_WEB);
        webFragment.setArguments(webBundle);

        fragments.add(androidFragment);
        fragments.add(iOSFragment);
        fragments.add(webFragment);
        fragments.add(girlFragment);

        mAdapter = new GankMainAdapter(getChildFragmentManager(),fragments,tabTitle);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    public void doSearch(String query){
        switch(mViewPager.getCurrentItem()){
            case 0:
                RxBus.getDefault().post(new SearchEvent(query,Constants.TYPE_ANDROID));
                break;
            case 1:
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_IOS));
                break;
            case 2:
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_WEB));
                break;
            case 3:
                RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_GIRL));
                break;
        }
    }
}
