package yonky.mynews.ui.gold.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
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
import yonky.mynews.model.bean.GankListBean;
import yonky.mynews.model.bean.GoldListBean;
import yonky.mynews.ui.zhihu.adapter.DailyAdapter;
import yonky.mynews.util.DateUtil;

/**
 * Created by Administrator on 2017/11/7.
 */

public class GoldListAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
    private List<GoldListBean> mList;
    private Context mContext;
    private LayoutInflater inflater;
    private String mType;
    private boolean mHotFlag = true;
//    private OnHotCloseListener
    public enum ITEM_TYPE{
    ITEM_TITLE,
    ITEM_HOT,
    ITEM_CONTENT
}
public GoldListAdapter(Context mContext, List<GoldListBean> mList,String typeStr){
    this.mList = mList;
    this.mContext = mContext;
    this.mType = typeStr;
    inflater = LayoutInflater.from(mContext);
}

    @Override
    public int getItemViewType(int position) {
        if(!mHotFlag){
            return ITEM_TYPE.ITEM_CONTENT.ordinal();
        }else{
            if(position==0){
                return ITEM_TYPE.ITEM_TITLE.ordinal();
            }else if(position >0 && position<=3){
                return ITEM_TYPE.ITEM_HOT.ordinal();
            }else {
                return ITEM_TYPE.ITEM_CONTENT.ordinal();
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.ITEM_TITLE.ordinal()){
            return new TitleViewHolder(inflater.inflate(R.layout.item_gold_title,parent,false));
        }else if(viewType ==ITEM_TYPE.ITEM_HOT.ordinal()){
            return new HotViewHolder(inflater.inflate(R.layout.item_gold_hot,parent,false));
        }
        return new DailyAdapter.ContentViewHolder(inflater.inflate(R.layout.item_gold,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GoldListBean bean = mList.get(0);
        if(position >0){
            bean = mList.get(position-1);
        }if(holder instanceof ContentViewHolder){
            if(bean.getScreenshot()!=null &&bean.getScreenshot().getUrl()!=null){
                ImageLoader.load(mContext,bean.getScreenshot().getUrl(),((ContentViewHolder)holder).ivImg);
            }else{
                ((ContentViewHolder)holder).ivImg.setImageResource(R.mipmap.ic_launcher);
            }
            ((ContentViewHolder)holder).tvTitle.setText(bean.getTitle());
            ((ContentViewHolder)holder).tvInfo.setText(getItemInfoStr(
                    bean.getCommentsCount(),
                    bean.getUser().getUsername()));
        }else if(holder instanceof HotViewHolder){

        }else{

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_gold_hot_title)
        TextView tvTitle;
        @BindView(R.id.btn_gold_hot_close)
        AppCompatButton btnClose;
        public TitleViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class HotViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_gold_item_title)
        TextView tvTitle;
        @BindView(R.id.tv_gold_item_like)
        TextView tvLike;
        @BindView(R.id.tv_gold_item_author)
        TextView tvAuthor;
        @BindView(R.id.tv_gold_item_time)
        TextView tvTime;
        @BindView(R.id.iv_gold_item_img)
        ImageView ivImg;

        public HotViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_gold_item_title)
        TextView tvTitle;
        @BindView(R.id.tv_gold_item_info)
        TextView tvInfo;
        @BindView(R.id.iv_gold_item_img)
        ImageView ivImg;

        public ContentViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    private String getItemInfoStr(int cmtNum,String author){
//        StringBuilder sb = new StringBuilder(String.valueOf(likeNum));
        StringBuilder sb = new StringBuilder(String.valueOf(cmtNum));
//        sb.append("人收藏.");
//        sb.append(cmtNum);
        sb.append("条评论.");
        sb.append(author);
//        sb.append(time);
        return sb.toString();
    }

    public void updateData(List<GoldListBean> list){
        mList = list;
    }
}
