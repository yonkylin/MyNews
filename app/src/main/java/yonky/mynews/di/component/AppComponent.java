package yonky.mynews.di.component;

import javax.inject.Singleton;

import dagger.Component;
import yonky.mynews.app.App;
import yonky.mynews.di.module.AppModule;
import yonky.mynews.di.module.HttpModule;
import yonky.mynews.model.DataManager;
import yonky.mynews.model.db.RealmHelper;
import yonky.mynews.model.http.RetrofitHelper;
import yonky.mynews.model.prefs.ImplPreferencesHelper;

/**
 * Created by Administrator on 2017/10/13.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
//    提供app的context
    App getContext();
//    数据中心
    DataManager getDataManager();
//    提供http帮助类
    RetrofitHelper retrofitHelper();
//    提供数据库帮助类
    RealmHelper realmHelper();
    //提供sp帮助类
    ImplPreferencesHelper preferencesHelper();

}
