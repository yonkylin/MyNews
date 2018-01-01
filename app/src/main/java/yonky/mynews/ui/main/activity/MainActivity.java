package yonky.mynews.ui.main.activity;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;
import yonky.mynews.R;
import yonky.mynews.app.App;
import yonky.mynews.app.Constants;
import yonky.mynews.base.BaseActivity;
import yonky.mynews.base.contract.main.MainContract;
import yonky.mynews.component.RxBus;
import yonky.mynews.model.event.SearchEvent;
import yonky.mynews.presenter.main.MainPresenter;
import yonky.mynews.ui.gank.fragment.GankMainFragment;
import yonky.mynews.ui.main.fragment.LikeFragment;
import yonky.mynews.ui.wechat.fragment.WechatMainFragment;
import yonky.mynews.ui.zhihu.fragment.ZhihuMainFragment;

/**
 * Created by Administrator on 2017/10/14.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.drawer)
        DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    @BindView(R.id.view_search)
    MaterialSearchView mSearchView;
    ZhihuMainFragment mZhihuFragment;
    WechatMainFragment mWechatFragment;
    GankMainFragment mGankFragment;
    LikeFragment mLikeFragment;

    ActionBarDrawerToggle mDrawerToggle;

    MenuItem mLastMenuItem;
    MenuItem mSearchMenuItem;
    private int hideFragment = Constants.TYPE_ZHIHU;
    private int showFragment= Constants.TYPE_ZHIHU;



    @Override
    protected void initInject() {
        getActivityComponent().inject(this);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHideFragment(getTargetFragment(showFragment),getTargetFragment(hideFragment));
        mNavigationView.getMenu().findItem(R.id.drawer_zhihu).setCheckable(false);
        mToolbar.setTitle(mNavigationView.getMenu().findItem(getCurrentItem(showFragment)).getTitle().toString());
        hideFragment = showFragment;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar,"知乎日报");
        mZhihuFragment = new ZhihuMainFragment();
        mWechatFragment =new WechatMainFragment();
        mGankFragment = new GankMainFragment();
        mLikeFragment = new LikeFragment();

        mDrawerToggle =new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,
                R.string.drawer_open,R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mLastMenuItem = mNavigationView.getMenu().findItem(R.id.drawer_zhihu);
        loadMultipleRootFragment(R.id.fl_main_content,0,mZhihuFragment,mWechatFragment,mGankFragment,mLikeFragment);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.drawer_zhihu:
                        showFragment = Constants.TYPE_ZHIHU;
                        mSearchMenuItem.setVisible(false);
                        break;
                    case R.id.drawer_wechat:
                        showFragment=Constants.TYPE_WECHAT;
                        mSearchMenuItem.setVisible(true);
                        break;
                    case R.id.drawer_gank:
                        showFragment = Constants.TYPE_GANK;
                        mSearchMenuItem.setVisible(true);
                        break;
                    case R.id.drawer_like:
                        showFragment =Constants.TYPE_LIKE;
                        mSearchMenuItem.setVisible(false);
                }
                if(mLastMenuItem !=null){
                    mLastMenuItem.setChecked(false);
                }
                mLastMenuItem = item;
                mPresenter.setCurrentItem(showFragment);
                item.setChecked(true);
                mToolbar.setTitle(item.getTitle());
                mDrawerLayout.closeDrawers();
                showHideFragment(getTargetFragment(showFragment),getTargetFragment(hideFragment));
                hideFragment = showFragment;
                return true;
            }
        });
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(showFragment == Constants.TYPE_GANK){
                    mGankFragment.doSearch(query);
                }else if(showFragment==Constants.TYPE_WECHAT){
                    RxBus.getDefault().post(new SearchEvent(query,Constants.TYPE_WECHAT));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        mSearchView.setMenuItem(item);
        mSearchMenuItem = item;
        return true;
    }

    /*

        @Override
        public void showUpdateDialog(String versionContent) {

        }
    */
    @Override
    public void startDownloadservice() {
//        startService(new Intent(mContext,UpdateService.class));
    }

    public void checkPermissions(){
        mPresenter.checkPermissions(new RxPermissions(this));
    }

    @Override
    public void onBackPressedSupport() {
        showExitDialog();
    }

    private void showExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogStyle);

        builder.setTitle("提示");
        builder.setMessage("确定退出软件吗？");
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                App.getInstance().exitApp();
            }
        });
       AlertDialog dialog= builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.WHITE);
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.WHITE);
        dialog.getWindow().setDimAmount(0.2f);
        dialog.getWindow().setWindowAnimations(R.style.dialogWindowAnim);
//        builder.show();
    }


    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_ZHIHU:
                return mZhihuFragment;
            case Constants.TYPE_GANK:
                return mGankFragment;
            case Constants.TYPE_WECHAT:
                return mWechatFragment;
//            case Constants.TYPE_GOLD:
//                return mGoldFragment;
//            case Constants.TYPE_VTEX:
//                return mVtexFragment;
            case Constants.TYPE_LIKE:
                return mLikeFragment;
//            case Constants.TYPE_SETTING:
//                return mSettingFragment;
//            case Constants.TYPE_ABOUT:
//                return mAboutFragment;
        }
        return mZhihuFragment;
    }

    private int getCurrentItem(int item) {
        switch (item) {
            case Constants.TYPE_ZHIHU:
                return R.id.drawer_zhihu;
            case Constants.TYPE_GANK:
                return R.id.drawer_gank;
            case Constants.TYPE_WECHAT:
                return R.id.drawer_wechat;
//            case Constants.TYPE_GOLD:
//                return R.id.drawer_gold;
//            case Constants.TYPE_VTEX:
//                return R.id.drawer_vtex;
            case Constants.TYPE_LIKE:
                return R.id.drawer_like;
//            case Constants.TYPE_SETTING:
//                return R.id.drawer_setting;
//            case Constants.TYPE_ABOUT:
//                return R.id.drawer_about;
        }
        return R.id.drawer_zhihu;
    }


}
