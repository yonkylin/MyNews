package yonky.mynews.model.db;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import yonky.mynews.model.bean.ReadStateBean;
import yonky.mynews.model.bean.RealmLikeBean;

/**
 * Created by Administrator on 2017/10/12.
 */

public class RealmHelper implements DBHelper {
    private static final String DB_NAME="myRealm.realm";
    private Realm mRealm;

    @Inject
    public RealmHelper(){
        mRealm=Realm.getInstance(new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .name(DB_NAME)
                    .build());
    }
    /**
     * 增加 阅读记录
     * @param id
     * 使用@PrimaryKey注解后copyToRealm需要替换为copyToRealmOrUpdate
     */
    @Override
    public void insertNewsId(int id) {
        ReadStateBean bean = new ReadStateBean();
        bean.setId(id);
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(bean);
        mRealm.commitTransaction();
    }
    /**
     * 查询 阅读记录
     * @param id
     * @return
     */
    @Override
    public boolean queryNewsId(int id) {
        RealmResults<ReadStateBean> results= mRealm.where(ReadStateBean.class).findAll();
        for(ReadStateBean item:results){
            if(item.getId()==id){
                return true;
            }
        }
        return false;
    }
//增加 收藏记录
    @Override
    public void insertLikeBean(RealmLikeBean bean) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(bean);
        mRealm.commitTransaction();
    }
//删除 收藏记录
    @Override
    public void deleteLikeBean(String id) {
        RealmLikeBean data = mRealm.where(RealmLikeBean.class).equalTo("id",id).findFirst();
        mRealm.beginTransaction();
        if(data !=null){
            data.deleteFromRealm();
        }
        mRealm.commitTransaction();
    }
//查询 收藏记录
    @Override
    public boolean queryLikeId(String id) {
        RealmResults<RealmLikeBean> results = mRealm.where(RealmLikeBean.class).findAll();
        for(RealmLikeBean item:results){
            if(item.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<RealmLikeBean> getLikeList() {
        RealmResults<RealmLikeBean> results = mRealm.where(RealmLikeBean.class).findAllSorted("time");
        return mRealm.copyFromRealm(results);
    }
//修改收藏记录 时间戳以重新排序
    @Override
    public void changeLikeTime(String id, long time, boolean isPlus) {
        RealmLikeBean bean= mRealm.where(RealmLikeBean.class).equalTo("id",id).findFirst();
        mRealm.beginTransaction();
        if(isPlus){
            bean.setTime(++time);
        }else {
            bean.setTime(--time);
        }
        mRealm.commitTransaction();

    }
}
