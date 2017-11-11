package yonky.mynews.base;

/**
 * Created by Administrator on 2017/10/13.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
