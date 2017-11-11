package yonky.mynews.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import yonky.mynews.component.RxBus;

/**
 * Created by Administrator on 2017/10/13.
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T mView;
    protected CompositeDisposable mCompositeDisposable;

    protected void unSubscribe(){
        if(mCompositeDisposable !=null){
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription){
        if(mCompositeDisposable==null){
            mCompositeDisposable=new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    protected <U> void addRxBusSubcribe(Class<U> eventType,Consumer<U> act){
        if(mCompositeDisposable==null){
            mCompositeDisposable= new CompositeDisposable();
        }
        mCompositeDisposable.add(RxBus.getDefault().toDefaultFlowable(eventType,act));
    }

    @Override
    public void attachView(T view) {
        this.mView=view;
    }

    @Override
    public void detachView() {
        this.mView= null;
        unSubscribe();
    }
}
