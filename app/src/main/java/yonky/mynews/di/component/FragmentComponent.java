package yonky.mynews.di.component;

import android.app.Activity;

import dagger.Component;
import yonky.mynews.di.module.FragmentModule;
import yonky.mynews.di.scope.FragmentScope;
import yonky.mynews.ui.gank.fragment.GirlFragment;
import yonky.mynews.ui.gank.fragment.TechFragment;
import yonky.mynews.ui.gold.fragment.GoldPagerFragment;
import yonky.mynews.ui.wechat.fragment.WechatMainFragment;
import yonky.mynews.ui.zhihu.fragment.DailyFragment;
import yonky.mynews.ui.zhihu.fragment.HotFragment;
import yonky.mynews.ui.zhihu.fragment.SectionFragment;
import yonky.mynews.ui.zhihu.fragment.ThemeFragment;

/**
 * Created by Administrator on 2017/10/19.
 */
@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
//    知乎
    void inject(DailyFragment dailyFragment);
    void inject(ThemeFragment themeFragment);
    void inject(SectionFragment sectionFragment);
    void inject(HotFragment hotFragment);
//    微信
    void inject(WechatMainFragment wechatMainFragment);
//    干货
    void inject(TechFragment techFragment);
    void inject(GirlFragment girlFragment);
//    掘金
    void inject(GoldPagerFragment goldPagerFragment);


}
