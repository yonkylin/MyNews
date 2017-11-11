package yonky.mynews.component;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/10/13.
 */

public class InitializeService extends IntentService {
    private static final String ACTION_INIT="initApplication";
    public InitializeService(){
        super("InitializeService");
    }
    public static void start(Context context){
        Intent intent= new Intent(context,InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent!=null){
            final String action = intent.getAction();
            if(ACTION_INIT.equals(action)){
                initApplication();
            }
        }
    }
    private void initApplication(){
//        初始化日志
        Logger.init(getPackageName())
                .hideThreadInfo();
        //初始化tbs x5 webview
     /*   QbSdk.allowThirdPartyAppDownload(true);
        QbSdk.initX5Environment(getApplicationContext(), QbSdk.WebviewInitType.FIRSTUSE_AND_PRELOAD, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
            }

            @Override
            public void onViewInitFinished(boolean b) {
            }
        });*/
    }
}
