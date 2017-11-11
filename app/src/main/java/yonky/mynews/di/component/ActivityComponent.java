package yonky.mynews.di.component;

import android.app.Activity;

import dagger.Component;

import yonky.mynews.di.module.ActivityModule;
import yonky.mynews.di.scope.ActivityScope;
import yonky.mynews.ui.main.activity.MainActivity;
import yonky.mynews.ui.main.activity.WelcomeActivity;
import yonky.mynews.ui.zhihu.activity.SectionActivity;
import yonky.mynews.ui.zhihu.activity.ThemeActivity;
import yonky.mynews.ui.zhihu.activity.ZhihuDetailActivity;

/**
 * Created by Administrator on 2017/10/13.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules=ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
    void inject(WelcomeActivity welcomeActivity);
    void inject(MainActivity mainActivity);
    void inject(ZhihuDetailActivity zhihuDetailActivity);
    void inject(ThemeActivity themeActivity);
    void inject(SectionActivity sectionActivity);
}
