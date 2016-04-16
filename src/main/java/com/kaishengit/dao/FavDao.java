package com.kaishengit.dao;

import com.kaishengit.entity.Fav;
import com.kaishengit.util.DpHelper;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by qiyawei on 2016/4/12.
 */
public class FavDao {
    public void save(Fav fav) {
        String sql = "insert into t_fav(uid,topicid,favtime) values(?,?,?)";
        DpHelper.update(sql,fav.getUid(),fav.getTopicid(),fav.getFavtime());
    }

    public Fav findByUidAndTopicid(Integer uid, Integer topicid) {
        String sql = "select *from t_fav where uid=? and topicid=? ";
        return DpHelper.query(sql,new BeanHandler<Fav>(Fav.class),uid,topicid);
    }

    public void delByTopicid(Integer id) {

        String sql  = "delete from t_fav where id=?";
        DpHelper.update(sql,id);

    }
}
