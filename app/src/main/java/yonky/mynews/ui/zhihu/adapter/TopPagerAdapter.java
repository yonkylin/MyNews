package yonky.mynews.ui.zhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yonky.mynews.R;
import yonky.mynews.component.ImageLoader;
import yonky.mynews.model.bean.DailyListBean;

/**
 * Created by Administrator on 2017/10/25.
 */

public class TopPagerAdapter extends PagerAdapter {
    private List<DailyListBean.TopStoriesBean> mList = new ArrayList<>();
    private Context mContext;

    public TopPagerAdapter(Context context,List<DailyListBean.TopStoriesBean> mList){
        this.mList = mList;
        this.mContext = context;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.item_top_pager,container,false);
        ImageView ivImage =(ImageView)view.findViewById(R.id.iv_top_image);
        TextView tvTitle= (TextView) view.findViewById(R.id.tv_top_title);
        ImageLoader.load(mContext,mList.get(position).getImage(),ivImage);
        tvTitle.setText(mList.get(position).getTitle());
        final int id = mList.get(position).getId();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
