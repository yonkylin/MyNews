package yonky.mynews.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Administrator on 2017/10/13.
 */

public class SnackbarUtil {
    public static void show(View view, String msg){
        Snackbar.make(view,msg,Snackbar.LENGTH_LONG).show();
    }
    public static void showShort(View view,String msg){
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
    }
}
