package yonky.mynews.ui.zhihu.adapter;

import android.content.Context;
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
import yonky.mynews.model.bean.HotListBean;

/**
 * Created by Administrator on 2017/11/3.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {
    private List<HotListBean.RecentBean> mList;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemclickListener;

    public HotAdapter(Context mContext,List<HotListBean.RecentBean> mList){
        this.mContext = mContext;
        this.mList= mList;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_daily,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.title.setText(mList.get(position).getTitle());
        if(mList.get(position).getReadState()){
            holder.title.setTextColor(ContextCompat.getColor(mContext,R.color.news_read));
        }else{
            holder.title.setTextColor(ContextCompat.getColor(mContext,R.color.news_unread));
        }
        ImageLoader.load(mContext,mList.get(position).getThumbnail(),holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemclickListener !=null){
                    ImageView iv =(ImageView)v.findViewById(R.id.iv_daily_item_image);
                    onItemclickListener.onItemClick(holder.getAdapterPosition(),iv);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_daily_item_title)
        TextView title;
        @BindView(R.id.iv_daily_item_image)
        ImageView image;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

    public void setReadState(int position,boolean readState) {
        mList.get(position).setReadState(readState);
    }

    public void setOnItemclickListener(OnItemClickListener onItemclickListener){
        this.onItemclickListener = onItemclickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position,View view);
    }
}
