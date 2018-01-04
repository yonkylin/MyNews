package yonky.mynews.ui.zhihu.activity;

import android.content.Intent;
import android.os.Build;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.OnClick;
import yonky.mynews.R;
import yonky.mynews.app.Constants;
import yonky.mynews.base.RootActivity;
import yonky.mynews.base.contract.zhihu.ZhihuDetailContract;
import yonky.mynews.component.ImageLoader;
import yonky.mynews.model.bean.ZhihuDetailBean;
import yonky.mynews.presenter.zhihu.ZhihuDetailPresenter;
import yonky.mynews.util.HtmlUtil;
import yonky.mynews.util.SystemUtil;

/**
 * Created by Administrator on 2017/10/30.
 */

public class ZhihuDetailActivity extends RootActivity<ZhihuDetailPresenter> implements ZhihuDetailContract.View {
    @BindView(R.id.detail_bar_image)
    ImageView detailBarImage;
    @BindView(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @BindView(R.id.view_toolbar)
    Toolbar viewToolbar;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.view_main)
    WebView wvDetailContent;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.fab_like)
    FloatingActionButton fabLike;

    int id = 0;
    int allNum = 0;
    int shortNum = 0;
    String imgUrl;
    boolean isImageShow= false;
    boolean isTransitionEnd = false;
    boolean isNotTransition = false;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setToolBar(viewToolbar,"");
        Intent intent = getIntent();
        id = intent.getExtras().getInt(Constants.IT_ZHIHU_DETAIL_ID);
        isNotTransition = intent.getBooleanExtra("isNotTransition",false);
        mPresenter.queryLikeData(id);
        mPresenter.getDetailData(id);
        stateLoading();
        WebSettings settings = wvDetailContent.getSettings();
        if(mPresenter.getNoImageState()){
            settings.setBlockNetworkImage(true);
        }
        if (mPresenter.getAutoCacheState()) {
            settings.setAppCacheEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setDatabaseEnabled(true);
            if(SystemUtil.isNetworkConnected()){
                settings.setCacheMode(WebSettings.LOAD_DEFAULT);
            }else{
                settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
            }
        }

        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        wvDetailContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        if(Build.VERSION.SDK_INT>21) {
            (getWindow().getSharedElementEnterTransition()).addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                }

                @Override
                public void onTransitionEnd(Transition transition) {
               /**
                 *测试发现部分手机(如红米note2) 上加载图片会变形, 没有达到centerCrop效果
                            * 查阅资料发现Glide配合SharedElementTransition是有坑的, 需要在Transition动画结束后再加载图片
                            * https://github.com/TWiStErRob/glide-support/blob/master/src/glide3/java/com/bumptech/glide/supportapp/github/_847_shared_transition/DetailFragment.java
                */
                    isTransitionEnd = true;
                    if (imgUrl != null) {
                        isImageShow = true;
                        ImageLoader.load(mContext, imgUrl, detailBarImage);
                    }
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                }

                @Override
                public void onTransitionPause(Transition transition) {
                }

                @Override
                public void onTransitionResume(Transition transition) {
                }
            });
        }
    }

    @Override
    public void showContent(ZhihuDetailBean zhihuDetailBean) {
       stateMain();
        imgUrl = zhihuDetailBean.getImage();
//        shareUrl = zhihuDetailBean.getShare_url();
        if(isNotTransition){
            ImageLoader.load(mContext,zhihuDetailBean.getImage(),detailBarImage);
        }else{
            if(!isImageShow && isTransitionEnd){
                ImageLoader.load(mContext,zhihuDetailBean.getImage(),detailBarImage);
            }
        }
        clpToolbar.setTitle(zhihuDetailBean.getTitle());
        detailBarCopyright.setText(zhihuDetailBean.getImage_source());
        String htmlData = HtmlUtil.createHtmlData(zhihuDetailBean.getBody(),zhihuDetailBean.getCss(),zhihuDetailBean.getImages());
        wvDetailContent.loadData(htmlData,HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);

    }
    @OnClick(R.id.fab_like)
    void likeArticle(){
        if(fabLike.isSelected()){
            fabLike.setSelected(false);
            mPresenter.deleteLikeData();

        }else{
            fabLike.setSelected(true);
            mPresenter.insertLikeData();
        }
    }
    @Override
    public void setLikeButtonState(boolean isLiked) {
        fabLike.setSelected(isLiked);
    }

    @Override
    public void onBackPressedSupport() {
        if(getSupportFragmentManager().getBackStackEntryCount()>1){
            pop();
        }else{
           if(Build.VERSION.SDK_INT>21) {
               finishAfterTransition() ;
           }else{
               finish();
           }
        }
    }
}
