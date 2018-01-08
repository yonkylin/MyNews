package yonky.mynews.ui.main.fragment;

import android.widget.TextView;

import butterknife.BindView;
import yonky.mynews.R;
import yonky.mynews.base.SimpleFragment;

/**
 * Created by Administrator on 2018/1/4.
 */

public class AboutFragment extends SimpleFragment {
    @BindView(R.id.tv_about_content)
    TextView tvContent;
    @Override
    protected void initEventAndData() {
        tvContent.setText(R.string.about_content);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }
}
