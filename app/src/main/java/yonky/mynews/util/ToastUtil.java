package yonky.mynews.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import yonky.mynews.R;
import yonky.mynews.app.App;

/**
 * Created by Administrator on 2017/12/29.
 */

public class ToastUtil {
    static ToastUtil tu;

    Context context;
    Toast toast;
    String msg;
    public ToastUtil(Context context){
        this.context = context;
    }

    public static void shortShow(String msg){
        if(tu ==null){
            tu= new ToastUtil(App.getInstance());
        }
        tu.setText(msg);
        tu.createShort().show();
    }

    public void setText(String text){
        msg =text;
    }
    public Toast createShort(){
        View contentView =View.inflate(context, R.layout.dialog_toast,null);
        TextView tvMsg = (TextView)contentView.findViewById(R.id.tv_toast_msg);
        toast = new Toast(context);
        toast.setView(contentView);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        tvMsg.setText(msg);
        return toast;
    }
}
