package yonky.mynews.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
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
import yonky.mynews.model.bean.RealmLikeBean;
import yonky.mynews.ui.TechDetailActivity;
import yonky.mynews.ui.zhihu.activity.ZhihuDetailActivity;

/**
 * Created by Administrator on 2017/12/28.
 */

public class LikeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<RealmLikeBean> mList;
    private LayoutInflater inflater;
    private  static final int TYPE_ARTICAL = 0;
    private static final int TYPE_GIRL=1;

    public LikeAdapter(Context mContext, List<RealmLikeBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if(mList.get(position).getType()== Constants.TYPE_GIRL){
            return TYPE_GIRL;
        }else {
            return TYPE_ARTICAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_ARTICAL){
            return new ArticleViewHolder(inflater.inflate(R.layout.item_like_article,parent,false));
        }else{
            return new GirlViewHolder(inflater.inflate(R.layout.item_like_girl,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ArticleViewHolder){
            ((ArticleViewHolder)holder).title.setText(mList.get(position).getTitle());
            switch (mList.get(position).getType()){
                case Constants.TYPE_ZHIHU:
                    if(mList.get(position).getImage()!=null){
                        ImageLoader.load(mContext,mList.get(position).getImage(),((ArticleViewHolder)holder).image);
                    }else {
                        ((ArticleViewHolder)holder).image.setImageResource(R.mipmap.ic_launcher);
                    }
                    ((ArticleViewHolder)holder).from.setText("来自 知乎");
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gotoDailyDetail(Integer.valueOf(mList.get(holder.getAdapterPosition()).getId()));
                        }
                    });
                    break;
                case Constants.TYPE_ANDROID:
                    ((ArticleViewHolder)holder).image.setImageResource(R.mipmap.ic_launcher);
                    ((ArticleViewHolder)holder).from.setText("来自 干货 android");
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gotoTechDetail(mList.get(holder.getAdapterPosition()).getId(),null,mList.get(holder.getAdapterPosition()).getTitle(),
                                    mList.get(holder.getAdapterPosition()).getId(),Constants.TYPE_ANDROID);
                        }
                    });
                    break;
                case Constants.TYPE_IOS:
                    ((ArticleViewHolder)holder).image.setImageResource(R.mipmap.ic_ios);
                    ((ArticleViewHolder)holder).from.setText("来自 干货 ios");
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gotoTechDetail(mList.get(holder.getAdapterPosition()).getUrl(),null,
                                    mList.get(holder.getAdapterPosition()).getTitle(),mList.get(holder.getAdapterPosition()).getId(),Constants.TYPE_IOS);

                        }
                    });
                    break;

            }
        }else if(holder instanceof GirlViewHolder){
            ImageLoader.load(mContext,mList.get(position).getImage(),((GirlViewHolder)holder).image);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoGirlDetail(mList.get(holder.getAdapterPosition()).getImage(),mList.get(holder.getAdapterPosition()).getId());

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class GirlViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_girl_image)
        ImageView image;
        public GirlViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class  ArticleViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_article_image)
        ImageView image;
        @BindView(R.id.tv_article_title)
        TextView title;
        @BindView(R.id.tv_article_from)
        TextView from;
        public ArticleViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    private void gotoDailyDetail(int id){
        Intent intent = new Intent();
        intent.setClass(mContext, ZhihuDetailActivity.class);
        intent.putExtra(Constants.IT_ZHIHU_DETAIL_ID,id);
        intent.putExtra(Constants.IT_ZHIHU_DETAIL_TRANSITION,true);
        mContext.startActivity(intent);
    }
    private void gotoTechDetail(String url,String imgUrl,String title,String id,int type){
        TechDetailActivity.launch(new TechDetailActivity.Builder()
                .setContext(mContext)
                .setUrl(url)
                .setImgUrl(imgUrl)
                .setId(id)
                .setTitle(title)
                .setType(type)
        );
    }
    private void gotoGirlDetail(String url,String id){
//        Intent intent = new Intent();
//        intent.setClass(mContext,GirlDetailAcitivy.class);
//        intent.putExtra(Constants.IT_GANK_GRIL_URL,url);
//        intent.putExtra(Constants.IT_GANK_GRIL_ID,id);
//        mContext.startActivity(intent);
    }

}
