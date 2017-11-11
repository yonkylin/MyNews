package yonky.mynews.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ReadStateBean extends RealmObject {
    @PrimaryKey
    private int id;
    public ReadStateBean(){}
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
}
