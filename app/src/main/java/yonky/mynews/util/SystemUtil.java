package yonky.mynews.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import yonky.mynews.app.App;
import yonky.mynews.app.Constants;

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
    /**
     * 检查手机网络(4G/3G/2G)是否连接
     */
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
    /**
     * 检查是否有可用网络
     */
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
//    保存文字到剪贴板
    public static void copyToClipBoard(Context context,String text){
        ClipData clipData = ClipData.newPlainText("url",text);
        ClipboardManager manager = ((ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE));
        manager.setPrimaryClip(clipData);
        ToastUtil.shortShow("已复制到剪贴板");
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

    /*保存图片到本地*/
    public static Uri saveBitmapToFile(Context context, String url, Bitmap bitmap, View container, boolean isShare){
        String fileName =url.substring(url.lastIndexOf("/"),url.lastIndexOf("."))+".png";
        File fileDir = new File(Constants.PATH_SDCARD);
        if(!fileDir.exists()){
            fileDir.mkdir();
        }
        File imageFile = new File(fileDir,fileName);
        Uri uri = Uri.fromFile(imageFile);
        if(isShare && imageFile.exists()){
            return uri;
        }
        try{
            FileOutputStream fos = new FileOutputStream(imageFile);
            boolean isCompress = bitmap.compress(Bitmap.CompressFormat.PNG,90,fos);
            if(isCompress){
                SnackbarUtil.showShort(container,"保存妹纸成功n(*≧▽≦*)n");
            }else{
                SnackbarUtil.showShort(container,"保存妹纸失败ヽ(≧Д≦)ノ");
            }
            fos.flush();
            fos.close();

        }catch (IOException e){
            e.printStackTrace();
            SnackbarUtil.showShort(container,"保存妹纸失败ヽ(≧Д≦)ノ");
        }
        try{
            MediaStore.Images.Media.insertImage(context.getContentResolver(),imageFile.getAbsolutePath(),fileName,null);

            }catch(FileNotFoundException e){
                e.printStackTrace();
            }

        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri));
        return uri;

    }
    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */

    public static String getProcessName(int pid){
        BufferedReader reader =null;
        try{
            reader = new BufferedReader(new FileReader("/proc/"+pid+"/cmdline"));
            String processName= reader.readLine();
            if(!TextUtils.isEmpty(processName)){
                processName = processName.trim();
            }
            return processName;
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }finally {
            try{
                if(reader !=null){
                    reader.close();
                }
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
        return null;
    }


}
