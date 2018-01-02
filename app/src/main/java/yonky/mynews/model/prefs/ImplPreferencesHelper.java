package yonky.mynews.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import yonky.mynews.app.App;
import yonky.mynews.app.Constants;

/**
 * Created by Administrator on 2017/11/4.
 */

public class ImplPreferencesHelper implements PreferencesHelper {
    private static final boolean DEFAULT_AUTO_SAVE = true;
    private static final int DEFAULT_CURRENT_ITEM= Constants.TYPE_ZHIHU;

    private static final String SHAREDPREFERENCES_NAME="my_sp";
    private final SharedPreferences mSprefs;

    @Inject
    public ImplPreferencesHelper(){
        mSprefs= App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public int getCurrentItem() {
        return mSprefs.getInt(Constants.SP_CURRENT_ITEM,DEFAULT_CURRENT_ITEM);
    }

    @Override
    public void setCurrentItem(int item) {
        mSprefs.edit().putInt(Constants.SP_CURRENT_ITEM,item).apply();
    }

    @Override
    public boolean getAutoCacheState() {
        return mSprefs.getBoolean(Constants.SP_AUTO_CACHE,DEFAULT_AUTO_SAVE);
    }

    @Override
    public void setAutoCacheState(boolean state) {
        mSprefs.edit().putBoolean(Constants.SP_AUTO_CACHE,state).apply();
    }
}
