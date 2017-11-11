package yonky.mynews.ui.gank.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.helper.DataUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yonky.mynews.R;
import yonky.mynews.model.bean.GankListBean;
import yonky.mynews.ui.gank.fragment.GankMainFragment;
import yonky.mynews.ui.wechat.adapter.WechatAdapter;
import yonky.mynews.util.DateUtil;

/**
 * Created by Administrator on 2017/11/6.
 */

public class TechAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<GankListBean.ResultsBean> mList;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    private String tech;

    public TechAdapter(Context mContext,List<GankListBean.ResultsBean> mList,String tech){
        this.mContext = mContext;
        this.mList  = mList;
        inflater = LayoutInflater.from(mContext);
        this.tech = tech;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_tech,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(tech.equals(GankMainFragment.tabTitle[0])){
            ((ViewHolder)holder).ivIcon.setImageResource(R.mipmap.ic_android);
        }else if(tech.equals(GankMainFragment.tabTitle[1])){
            ((ViewHolder)holder).ivIcon.setImageResource(R.mipmap.ic_ios);
        }else if(tech.equals(GankMainFragment.tabTitle[2])){
            ((ViewHolder)holder).ivIcon.setImageResource(R.mipmap.ic_web);
        }
        ((ViewHolder)holder).tvContent.setText(mList.get(position).getDesc());
        ((ViewHolder)holder).tvAuthor.setText(mList.get(position).getWho());
        ((ViewHolder)holder).tvTime.setText(DateUtil.formatDateTime(DateUtil.subStandardTime(mList.get(position).getPublishedAt()),true));
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(onItemClickListener !=null){
                    CardView cv = (CardView)view.findViewById(R.id.cv_tech_content);
                    onItemClickListener.onItemClick(holder.getAdapterPosition(),cv);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_tech_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_tech_title)
        TextView tvContent;
        @BindView(R.id.tv_tech_author)
        TextView tvAuthor;
        @BindView(R.id.tv_tech_time)
        TextView tvTime;
        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position,View view);
    }
}
