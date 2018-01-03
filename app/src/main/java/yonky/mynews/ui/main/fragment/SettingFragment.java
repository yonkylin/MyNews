package yonky.mynews.ui.main.fragment;

import butterknife.BindView;
import butterknife.OnClick;
import yonky.mynews.R;
import yonky.mynews.app.Constants;
import yonky.mynews.base.BaseFragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import yonky.mynews.base.contract.main.SettingContract.View;
import yonky.mynews.base.contract.main.SettingContract;
import yonky.mynews.component.ACache;
import yonky.mynews.presenter.main.SettingPresenter;

/**
 * Created by Administrator on 2018/1/2.
 */

public class SettingFragment extends BaseFragment<SettingPresenter> implements CompoundButton.OnCheckedChangeListener, SettingContract.View {

    @BindView(R.id.cb_setting_cache)
        AppCompatCheckBox cbSettingCache;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox cbSettingImage;
    @BindView(R.id.tv_setting_clear)
    TextView tvSettingClear;
    @BindView(R.id.ll_setting_clear)
    LinearLayout llSettingClear;

    private File cacheFile;
    private boolean isNull = true;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initEventAndData() {
        cacheFile = new File(Constants.PATH_CACHE);
        tvSettingClear.setText(ACache.getCacheSize(cacheFile));
        cbSettingCache.setChecked(mPresenter.getAutoCacheState());
        cbSettingImage.setChecked(mPresenter.getNoImageState());
        cbSettingCache.setOnCheckedChangeListener(this);
        cbSettingImage.setOnCheckedChangeListener(this);
//        try{
//            PackageManager pm = getActivity().getPackageManager();
//            PackageInfo pi = pm.getPackageInfo(getActivity().getPackageName(),PackageManager.GET_ACTIVITIES);
//            vers
//
//        }catch (PackageManager.NameNotFoundException e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        isNull = savedInstanceState ==null;
        super.onCreate(savedInstanceState);

    }

    @OnClick(R.id.ll_setting_clear)
    void doClear(){
        ACache.deleteDir(cacheFile);
        tvSettingClear.setText(ACache.getCacheSize(cacheFile));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.cb_setting_cache:
                mPresenter.getAutoCacheState();
                break;
            case R.id.cb_setting_image:
                mPresenter.getNoImageState();
                break;
        }
    }
}
