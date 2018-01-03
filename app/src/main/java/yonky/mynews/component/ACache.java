package yonky.mynews.component;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/1/3.
 */

public class ACache {
    public static boolean deleteDir(File dir){
        if(dir!=null &&dir.isDirectory()){
            String[] children = dir.list();
            for(String aChildren :children){
                boolean success = deleteDir(new File(dir,aChildren));
                if(!success){
                    return false;
                }
            }
        }
        assert dir !=null;
        return dir.delete();
    }

    public static String getCacheSize(File file){
        return getFormatSize(getFolderSize(file));
    }

    public static long getFolderSize(File file){
        long size = 0;
        try{
            File[] fileList = file.listFiles();
            for(File aFileList:fileList){
                if(aFileList.isDirectory()){
                    size=size+getFolderSize(aFileList);
                }else{
                    size=size+aFileList.length();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return size;
    }

    public static String getFormatSize(double size){
        double kiloByte = size/1024;
        if(kiloByte<1){
            return "0K";
        }
        double megaByte = kiloByte/1024;
        if(megaByte<1){
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString()+"KB";
        }
        double gigaByte = megaByte/1024;
        if(gigaByte<1){
            BigDecimal result2= new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString()+"MB";
        }
        double teraBytes = gigaByte/1024;
        if(teraBytes<1){
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString()+"GB";
        }
        BigDecimal result4 = new BigDecimal(Double.toString(teraBytes));
        return result4.setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString()+"TB";
    }
}
