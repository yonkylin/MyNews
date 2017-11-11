package yonky.mynews.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import yonky.mynews.di.scope.FragmentScope;

/**
 * Created by Administrator on 2017/10/19.
 */
@Module
public class FragmentModule {
    private Fragment fragment;
    public FragmentModule(Fragment fragment){
        this.fragment = fragment;
    }
    @Provides
    @FragmentScope
    public Activity provideActivity(){
        return fragment.getActivity();
    }

}
