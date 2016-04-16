package com.kaishengit.dao;

import com.kaishengit.entity.Password;
import com.kaishengit.util.DpHelper;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by qiyawei on 2016/4/8.
 */
public class PasswordDao {
    public static Password findByToken(String token){
        String sql = "select * from t_password where token=?";
        return DpHelper.query(sql,new BeanHandler<Password>(Password.class),token);
    }

    public void deleteByToken(String token) {
        String sql = "delete from t_password where token=?";
        DpHelper.update(sql,token);
    }
    public void save(Password password) {
        String sql = "insert into t_password(token,time,uid) values(?,?,?)";
        DpHelper.update(sql,password.getToken(),password.getTime(),password.getUid());
    }
}
