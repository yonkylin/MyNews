package yonky.mynews.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import yonky.mynews.di.scope.ActivityScope;

/**
 * Created by Administrator on 2017/10/13.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity){
        this.mActivity= activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity(){
        return mActivity;
    }
}
