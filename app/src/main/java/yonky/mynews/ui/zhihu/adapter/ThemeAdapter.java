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
import yonky.mynews.app.App;
import yonky.mynews.component.ImageLoader;
import yonky.mynews.model.bean.ThemeListBean;
import yonky.mynews.util.SystemUtil;

/**
 * Created by Administrator on 2017/11/1.
 */

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<ThemeListBean.OthersBean> mList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public ThemeAdapter(Context mContext,List<ThemeListBean.OthersBean> mList){
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_theme,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewGroup.LayoutParams lp = holder.ivBg.getLayoutParams();
        lp.width = (App.SCREEN_WIDTH- SystemUtil.dp2px(mContext,12))/2;
        lp.height = SystemUtil.dp2px(mContext,120);

        ImageLoader.load(mContext,mList.get(position).getThumbnail(),holder.ivBg);
        holder.tvKind.setText(mList.get(position).getName());
        final int id = mList.get(position).getId();
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(onItemClickListener !=null){
                    onItemClickListener.onItemClick(id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.theme_bg)
        ImageView ivBg;
        @BindView(R.id.theme_kind)
        TextView tvKind;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(int id);
    }
}
