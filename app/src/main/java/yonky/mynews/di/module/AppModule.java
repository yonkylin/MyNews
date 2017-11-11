package yonky.mynews.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import yonky.mynews.app.App;
import yonky.mynews.model.DataManager;
import yonky.mynews.model.db.DBHelper;
import yonky.mynews.model.db.RealmHelper;
import yonky.mynews.model.http.HttpHelper;
import yonky.mynews.model.http.RetrofitHelper;
import yonky.mynews.model.prefs.ImplPreferencesHelper;
import yonky.mynews.model.prefs.PreferencesHelper;

/**
 * Created by Administrator on 2017/10/12.
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App application){
        this.application=application;
    }

    @Provides
    @Singleton
    App provideApplicationContext(){
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper){
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper){
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper){
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper){
        return new DataManager(httpHelper,dbHelper,preferencesHelper);
    }

}
