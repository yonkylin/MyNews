package yonky.mynews.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2017/10/12.
 */

public class Constants {
    public static final int TYPE_ZHIHU=101;
    public static final int TYPE_WECHAT=106;
    public static final int TYPE_GANK = 107;

    public static final int TYPE_ANDROID = 102;

    public static final int TYPE_IOS = 103;

    public static final int TYPE_WEB = 104;
    public static final int TYPE_GIRL = 105;

    public static final int TYPE_LIKE = 111;

    //============key================
    public static final String KEY_API="a744d07b473a985f6f98424d1886cc42";

    public static final String LEANCLOUD_ID = "mhke0kuv33myn4t4ghuid4oq2hjj12li374hvcif202y5bm6";

    public static final String LEANCLOUD_SIGN = "badc5461a25a46291054b902887a68eb,1480438132702";

//============PATH================

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MyNews";

    //================= PREFERENCE ====================
    public static final String SP_AUTO_CACHE = "auto_cache";
    public static final String SP_CURRENT_ITEM = "current_item";
    //================= INTENT ====================
    public static final String IT_ZHIHU_DETAIL_ID = "zhihu_detail_id";
    public static final String IT_ZHIHU_THEME_ID = "zhihu_theme_id";
    public static final String IT_ZHIHU_SECTION_ID="zhihu_section_id";
    public static final String IT_ZHIHU_SECTION_TITLE="zhihu_section_title";
    public static final String IT_ZHIHU_DETAIL_TRANSITION = "zhihu_detail_transition";

    public static final String IT_GANK_DETAIL_TITLE = "gank_detail_title";
    public static final String IT_GANK_DETAIL_URL = "gank_detail_url";
    public static final String IT_GANK_DETAIL_IMG_URL = "gank_detail_img_url";
    public static final String IT_GANK_DETAIL_ID = "gank_detail_id";
    public static final String IT_GANK_DETAIL_TYPE = "gank_detail_type";
    public static final String IT_GANK_GRIL_ID = "gank_girl_id";
    public static final String IT_GANK_GRIL_URL = "gank_girl_url";

    public static final String IT_GANK_TYPE = "gank_type";
    public static final String IT_GANK_TYPE_CODE = "gank_type_code";

    public static final String IT_GOLD_TYPE = "gold_type";
    public static final String IT_GOLD_TYPE_STR = "gold_type_str";



}
