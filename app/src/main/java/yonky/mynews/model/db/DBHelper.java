package yonky.mynews.model.db;

import java.util.List;

import yonky.mynews.model.bean.RealmLikeBean;

/**
 * Created by Administrator on 2017/10/12.
 */

public interface DBHelper {
    //插入数据
    void insertNewsId(int id);
    //查询记录
    boolean queryNewsId(int id);
    //增加收藏记录
    void insertLikeBean(RealmLikeBean bean);
    //删除收藏记录
    void deleteLikeBean(String id);
    //查询 收藏记录
    boolean queryLikeId(String id);

    List<RealmLikeBean> getLikeList();
    /*
    * 修改 收藏几率 时间戳以重新排序
    * */
    void changeLikeTime(String id , long time,boolean isPlus);
}
