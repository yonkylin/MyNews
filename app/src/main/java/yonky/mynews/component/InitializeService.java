package yonky.mynews.component;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.github.moduth.blockcanary.BlockCanary;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;

import java.util.LinkedHashMap;
import java.util.Map;

import yonky.mynews.BuildConfig;
import yonky.mynews.app.App;
import yonky.mynews.app.Constants;
import yonky.mynews.util.SystemUtil;
import yonky.mynews.widget.AppBlockCanaryContext;

import static yonky.mynews.util.LogUtil.isDebug;

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
        //        初始化错误收集
        initBugly();

//        初始化内存泄漏检测
        LeakCanary.install(App.getInstance());

//        初始化过度绘制检测
        BlockCanary.install(getApplicationContext(),new AppBlockCanaryContext()).start();
        //初始化tbs x5 webview
//        QbSdk.allowThirdPartyAppDownload(true);
        QbSdk.initX5Environment(getApplicationContext(),null);

    }
    private void initBugly(){
        Context context = getApplicationContext();
        String packageName =context.getPackageName();
        String processName = SystemUtil.getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);

        strategy.setCrashHandleCallback(new CrashReport.CrashHandleCallback(){
            public Map<String,String> onCrashHandleStart(int crashType, String errorType, String errorMessage, String errorStack){
                LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
                String x5CrashInfo = com.tencent.smtt.sdk.WebView.getCrashExtraMessage(getApplicationContext());
                map.put("x5crashInfo", x5CrashInfo);
                return map;
            }
            @Override
            public  byte[] onCrashHandleStart2GetExtraDatas(int crashType, String errorType, String errorMessage, String errorStack) {
                try{
                    return "Extra data.".getBytes("UTF-8");
                } catch (Exception e) {
                    return null;
                }
            }
        }
        );
        strategy.setUploadProcess(processName==null || processName.equals(packageName));
        CrashReport.initCrashReport(context, Constants.BUGLY_ID,isDebug,strategy);
    }
}
