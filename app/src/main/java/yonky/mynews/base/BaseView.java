package yonky.mynews.base;

/**
 * Created by Administrator on 2017/10/13.
 */

public interface BaseView {
    void showErrorMsg(String msg);
//    void useNightMode(boolean isNight);

    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
