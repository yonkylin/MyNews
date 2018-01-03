package yonky.mynews.model.prefs;

/**
 * Created by Administrator on 2017/11/4.
 */

public interface PreferencesHelper {
    int getCurrentItem();
    void setCurrentItem(int item);
    boolean getAutoCacheState();
    void setAutoCacheState(boolean state);
    boolean getNoImageState();
    void setNoImageState(boolean state);

}
