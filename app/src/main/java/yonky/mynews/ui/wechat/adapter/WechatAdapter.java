package yonky.mynews.ui.wechat.adapter;

import android.content.Context;
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
import yonky.mynews.app.Constants;
import yonky.mynews.component.ImageLoader;
import yonky.mynews.model.bean.WXItemBean;
import yonky.mynews.ui.gank.activity.TechDetailActivity;

/**
 * Created by Administrator on 2017/11/3.
 */

public class WechatAdapter extends RecyclerView.Adapter<WechatAdapter.ViewHolder> {
    private Context mContext;
    private List<WXItemBean.NewslistBean> mList;
    private LayoutInflater inflater;

    public WechatAdapter(Context mContext,List<WXItemBean.NewslistBean> mList){
        this.mContext = mContext;
        this.mList= mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_wechat,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ImageLoader.load(mContext,mList.get(position).getPicUrl(),holder.image);
        holder.tvTitle.setText(mList.get(position).getTitle());
        holder.tvFrom.setText(mList.get(position).getDescription());
        holder.tvTime.setText(mList.get(position).getCtime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TechDetailActivity.launch(new TechDetailActivity.Builder()
                        .setContext(mContext)
                        .setId(mList.get(holder.getAdapterPosition()).getPicUrl())
                        .setImgUrl(mList.get(holder.getAdapterPosition()).getPicUrl())
                        .setTitle(mList.get(holder.getAdapterPosition()).getTitle())
                        .setUrl(mList.get(holder.getAdapterPosition()).getUrl())
                        .setType(Constants.TYPE_WECHAT)
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_wechat_item_imgae)
        ImageView image;
        @BindView(R.id.tv_wechat_item_title)
        TextView tvTitle;
        @BindView(R.id.tv_wechat_item_from)
        TextView tvFrom;
        @BindView(R.id.tv_wechat_item_time)
        TextView tvTime;


        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
