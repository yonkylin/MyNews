package yonky.mynews.util;

import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/19.
 */

public class DateUtil {
    public static String getCurrentDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    public static String getTomorrowDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return String.valueOf(Integer.valueOf(df.format(new Date()))+1);
    }

    public static int getCurrentYear(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    public static int getCurrentMonth(){
        Calendar cal =Calendar.getInstance();
        return cal.get(Calendar.MONTH);
    }

    public static int getCurrentDay(){
        Calendar cal =Calendar.getInstance();
        return cal.get(Calendar.DATE);
    }

//     * 切割标准时间
    @Nullable
    public static String subStandardTime(String time){
        int idx = time.indexOf(".");
        if(idx>0){
            return time.substring(0,idx).replace("T","");
        }
        return null;
    }

    public static String formatDateTime(String time,boolean haveYear){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(time ==null){
            return "";
        }
        Date date;
        try{
            date = format.parse(time);
        }catch (ParseException e){
            e.printStackTrace();
            return "";
        }
        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.YEAR,current.get(Calendar.YEAR));
        today.set(Calendar.MONTH,current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH));
        today.set(Calendar.HOUR_OF_DAY,0);
        today.set(Calendar.MINUTE,0);
        today.set(Calendar.SECOND,0);
        Calendar yesterday =Calendar.getInstance();
        yesterday.set(Calendar.YEAR,current.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH)-1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        current.setTime(date);
        if(current.after(today)){
            return "今天"+time.split(" ")[1];
        }else if(current.before(today)&& current.after(yesterday)){
            return "昨天"+time.split(" ")[1];
        }else{
            if(haveYear){
                int index = time.indexOf(" ");
                return time.substring(0,index);
            }else{
                int yearIndex = time.indexOf("-")+1;
                int index = time.indexOf(" ");
                return time.substring(yearIndex,time.length()).substring(0,index);
            }
        }
    }
}
