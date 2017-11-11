package yonky.mynews.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import yonky.mynews.app.App;

/**
 * Created by Administrator on 2017/10/13.
 */

public class SystemUtil {
    public static boolean isWifiConnected(){
        ConnectivityManager connectivityManager=(ConnectivityManager) App.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if(networkInfo !=null){
            if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI)
                return true;
        }
        return false;
    }
    public static boolean isMobileNetworkConnected(){
        ConnectivityManager connectivityManager=(ConnectivityManager) App.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if(networkInfo !=null){
            if(networkInfo.getType()==ConnectivityManager.TYPE_MOBILE)
                return true;
        }
        return false;
    }
    public static boolean isNetworkConnected(){
        ConnectivityManager connectivityManager=(ConnectivityManager) App.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if(networkInfo !=null){
                return true;
        }
        return false;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context , float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale +0.5f);
    }

    public static int dp2px(float dpValue){
        final float scale = App.getInstance().getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale +0.5f);
    }

}
