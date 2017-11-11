package yonky.mynews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import yonky.mynews.app.App;
import yonky.mynews.di.component.DaggerFragmentComponent;
import yonky.mynews.di.component.FragmentComponent;
import yonky.mynews.di.module.FragmentModule;
import yonky.mynews.util.SnackbarUtil;

/**
 * Created by Administrator on 2017/10/19.
 */

public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {
    @Inject
    protected T mPresenter;
    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();

    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if(mPresenter !=null) mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup)getActivity().findViewById(android.R.id.content)).getChildAt(0),msg);
    }

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

    protected abstract  void initInject();
}
