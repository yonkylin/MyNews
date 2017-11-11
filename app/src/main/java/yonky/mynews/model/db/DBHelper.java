package yonky.mynews.model.db;

/**
 * Created by Administrator on 2017/10/12.
 */

public interface DBHelper {
    //插入数据
    void insertNewsId(int id);
    //查询记录
    boolean queryNewsId(int id);

//    void insertLikeBean(RealmLikeBean bean);

}
