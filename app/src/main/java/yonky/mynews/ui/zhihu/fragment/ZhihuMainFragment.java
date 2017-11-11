package yonky.mynews.ui.zhihu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.mynews.R;
import yonky.mynews.base.SimpleFragment;
import yonky.mynews.ui.zhihu.adapter.ZhihuMainAdapter;

/**
 * Created by Administrator on 2017/10/14.
 */

public class ZhihuMainFragment extends SimpleFragment{
    @BindView(R.id.tab_zhihu_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_zhihu_main)
    ViewPager mViewPager;
    List<Fragment> fragments = new ArrayList<Fragment>();
    ZhihuMainAdapter mAdapter;
    String[] titles = new String[]{"日报","主题","专栏","热门"};
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_main;
    }

    @Override
    protected void initEventAndData() {
        fragments.add(new DailyFragment());
        fragments.add(new ThemeFragment());
        fragments.add(new SectionFragment());
        fragments.add(new HotFragment());
        mAdapter = new ZhihuMainAdapter(getChildFragmentManager(),fragments,titles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }
}
