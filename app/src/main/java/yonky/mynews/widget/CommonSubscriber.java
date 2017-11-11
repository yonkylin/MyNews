package yonky.mynews.widget;

import android.text.TextUtils;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;
import yonky.mynews.base.BaseView;

/**
 * Created by Administrator on 2017/10/19.
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowErrorState= true;

    protected CommonSubscriber(BaseView view){
        this.mView = view;
    }
    protected CommonSubscriber(BaseView view,String mErrorMsg){
        this.mView = view;
        this.mErrorMsg = mErrorMsg;
    }
    protected CommonSubscriber(BaseView view, boolean isShowErrorState){
        this.mView = view;
        this.isShowErrorState = isShowErrorState;
    }
    protected CommonSubscriber(BaseView view , String errorMsg,boolean isShowErrorState){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if(mView==null){
            return;
        }
        if(mErrorMsg !=null &&!TextUtils.isEmpty(mErrorMsg)){
            mView.showErrorMsg(mErrorMsg);
//        }else if(e instanceof ApiException){
//            mView.showErroerMsg(e.toString());
        }else if(e instanceof HttpException){
            mView.showErrorMsg("数据加载失败");
        }else{
            mView.showErrorMsg("未知错误");
//            LogUtil.d(e.toString());
        }
        if(isShowErrorState) {
            mView.stateError();
        }
    }
}
