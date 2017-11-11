package yonky.mynews.ui.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yonky.mynews.R;
import yonky.mynews.component.ImageLoader;
import yonky.mynews.model.bean.ThemeChildListBean;

/**
 * Created by Administrator on 2017/11/2.
 */

public class ThemeChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private List<ThemeChildListBean.StoriesBean> mList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;

    public ThemeChildAdapter(Context mContext,List<ThemeChildListBean.StoriesBean> mList){
        inflater = LayoutInflater.from(mContext);
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_daily,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
       if(mList.get(position).getImages()!=null && mList.get(position).getImages().size()>0){
           ImageLoader.load(mContext,mList.get(position).getImages().get(0),((ViewHolder)holder).image);
       }
        ((ViewHolder)holder).title.setText(mList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener !=null){
                    ImageView iv =(ImageView)v.findViewById(R.id.iv_daily_item_image);
                    onItemClickListener.onItemClick(holder.getAdapterPosition(),iv);
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
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    public  interface OnItemClickListener{
         void onItemClick(int position,View view);
    }
    public void setReadState(int positon , boolean readState){
        mList.get(positon).setReadState(readState);
    }
}
