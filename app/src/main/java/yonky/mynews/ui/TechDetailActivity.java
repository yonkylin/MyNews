package yonky.mynews.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import butterknife.BindView;
import yonky.mynews.R;
import yonky.mynews.app.App;
import yonky.mynews.app.Constants;
import yonky.mynews.base.SimpleActivity;
import yonky.mynews.model.db.RealmHelper;
import yonky.mynews.model.prefs.ImplPreferencesHelper;

/**
 * Created by Administrator on 2017/11/4.
 */

public class TechDetailActivity extends SimpleActivity {
    @BindView(R.id.toolbar)
    Toolbar toolBar;
    @BindView(R.id.wv_tech_content)
    WebView wvTechcontent;
    @BindView(R.id.tv_progress)
    TextView tvProgress;

    RealmHelper mRealmHelper;
    ImplPreferencesHelper mImplPreferencesHelper;
    MenuItem menuItem;

    String title,url,imgUrl,id;
    int type;
    boolean isLiked;

    @Override
    protected int getLayout() {
        return R.layout.activity_tech_detail;
    }

    @Override
    protected void initEventAndData() {
        mRealmHelper = App.getAppComponent().realmHelper();
        mImplPreferencesHelper = App.getAppComponent().preferencesHelper();
        Intent intent = getIntent();
        type =intent.getExtras().getInt(Constants.IT_GANK_DETAIL_TYPE);
        title = intent.getExtras().getString(Constants.IT_GANK_DETAIL_TITLE);
        url = intent.getExtras().getString(Constants.IT_GANK_DETAIL_URL);
        imgUrl = intent.getExtras().getString(Constants.IT_GANK_DETAIL_IMG_URL);
        id =intent.getExtras().getString(Constants.IT_GANK_DETAIL_ID);
        setToolBar(toolBar,title);

        WebSettings settings = wvTechcontent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        wvTechcontent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvTechcontent.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(tvProgress==null)
                    return;
                if(newProgress==100){
                    tvProgress.setVisibility(View.GONE);
                }else{
                    tvProgress.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams lp = tvProgress.getLayoutParams();
                    lp.width =App.SCREEN_WIDTH *newProgress/100;
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
        wvTechcontent.loadUrl(url);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.tech_menu,menu);
//        menuItem = menu.findItem(R.id.action_like);
//        setLikeState(mRealmHelper.queryLikeId(id));
//        return true;
//    }


    @Override
    public void onBackPressedSupport() {
        if(getSupportFragmentManager().getBackStackEntryCount()>1){
            pop();
        }else if(Build.VERSION.SDK_INT>21){
            finishAfterTransition();
        }else {
            finish();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static class Builder{
        private String title;
        private String url;
        private String imgUrl;
        private String id;
        private int type;
        private View shareView;
        private Context mContext;
        private Activity mActivity;

        public Builder(){

        }

        public Builder setContext(Context mContext){
            this.mContext = mContext;
            return this;
        }

        public Builder setTitle(String title){
            this.title = title;
            return this;
        }

        public Builder setUrl(String url){
            this.url = url;
            return this;
        }

        public Builder setImgUrl(String imgUrl){
            this.imgUrl = imgUrl;
            return this;
        }

        public Builder setId(String id){
            this.id = id;
            return this;
        }

        public Builder setType(int type){
            this.type = type;
            return  this;
        }

        public Builder setAnimConfig(Activity mActivity,View shareView){
            this.mActivity = mActivity;
            this.shareView = shareView;
            return this;
        }
    }

    public static void launch(Builder builder){
        Intent intent = new Intent();
        intent.setClass(builder.mContext,TechDetailActivity.class);
        intent.putExtra(Constants.IT_GANK_DETAIL_URL,builder.url);
        intent.putExtra(Constants.IT_GANK_DETAIL_IMG_URL,builder.imgUrl);
        intent.putExtra(Constants.IT_GANK_DETAIL_TITLE,builder.title);
        intent.putExtra(Constants.IT_GANK_DETAIL_ID,builder.id);
        intent.putExtra(Constants.IT_GANK_DETAIL_TYPE,builder.type);
        if(builder.shareView!=null && Build.VERSION.SDK_INT>21){
            ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(builder.mActivity,builder.shareView,"shareView");
            builder.mContext.startActivity(intent,options.toBundle());
        }else{
            builder.mContext.startActivity(intent);
        }
    }
}
