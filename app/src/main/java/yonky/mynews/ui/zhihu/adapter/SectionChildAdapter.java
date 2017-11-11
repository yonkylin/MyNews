package yonky.mynews.ui.zhihu.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yonky.mynews.R;
import yonky.mynews.component.ImageLoader;
import yonky.mynews.model.bean.SectionChildListBean;
import yonky.mynews.ui.zhihu.activity.SectionActivity;
import yonky.mynews.ui.zhihu.activity.ZhihuDetailActivity;

import static yonky.mynews.app.Constants.IT_ZHIHU_DETAIL_ID;

/**
 * Created by Administrator on 2017/11/2.
 */

public class SectionChildAdapter extends RecyclerView.Adapter<SectionChildAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater inflater;
    private List<SectionChildListBean.StoriesBean> mList;
    public SectionChildAdapter(Context mContext,List<SectionChildListBean.StoriesBean> mList){
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_daily,parent,false));
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, final int position) {
        if(mList.get(position).getImages()!=null && mList.get(position).getImages().size()>0){
            holder.ivImage.setVisibility(View.VISIBLE);
            ImageLoader.load(mContext,mList.get(position).getImages().get(0),holder.ivImage);
        }else{
            holder.ivImage.setVisibility(View.GONE);
        }
        if(mList.get(position).getReadState()){
            holder.tvTitle.setTextColor(ContextCompat.getColor(mContext,R.color.news_read));;
        }else{
            holder.tvTitle.setTextColor(ContextCompat.getColor(mContext,R.color.news_unread));;
        }
        holder.tvTitle.setText(mList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext,ZhihuDetailActivity.class);
                intent.putExtra(IT_ZHIHU_DETAIL_ID,mList.get(position).getId());
                if(v!=null && Build.VERSION.SDK_INT>21){
                    ImageView iv = (ImageView)v.findViewById(R.id.iv_daily_item_image);
                    mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((SectionActivity)mContext,iv,"shareView").toBundle());
                }else{
                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_daily_item_image)
        ImageView ivImage;
        @BindView(R.id.tv_daily_item_title)
        TextView tvTitle;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
