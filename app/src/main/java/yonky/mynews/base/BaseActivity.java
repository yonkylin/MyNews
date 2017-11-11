package yonky.mynews.base;

import android.view.ViewGroup;

import javax.inject.Inject;

import yonky.mynews.app.App;
import yonky.mynews.di.component.ActivityComponent;
import yonky.mynews.di.component.DaggerActivityComponent;
import yonky.mynews.di.module.ActivityModule;
import yonky.mynews.util.SnackbarUtil;

/**
 * Created by Administrator on 2017/10/13.
 */

public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView
{
    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if(mPresenter !=null){
            mPresenter.attachView(this);
        }
    }
    @Override
    protected void onDestroy(){
        if(mPresenter !=null)
            mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup)findViewById(android.R.id.content)).getChildAt(0),msg);
    }

   /* @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }*/
    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    protected abstract void initInject();
}
