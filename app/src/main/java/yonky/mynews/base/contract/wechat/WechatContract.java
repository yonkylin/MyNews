package yonky.mynews.base.contract.wechat;

import java.util.List;

import yonky.mynews.base.BasePresenter;
import yonky.mynews.base.BaseView;
import yonky.mynews.model.bean.WXItemBean;

/**
 * Created by Administrator on 2017/11/3.
 */

public interface WechatContract {
    interface View extends BaseView{
        void showContent(List<WXItemBean.NewslistBean> mList);
        void showMoreContent(List<WXItemBean.NewslistBean> mList);

    }
    interface Presenter extends BasePresenter<View>{
        void getWechatData();
        void getMoreWechatData();
    }
}
