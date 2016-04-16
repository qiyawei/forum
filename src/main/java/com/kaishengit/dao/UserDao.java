package com.kaishengit.dao;

import com.kaishengit.entity.User;
import com.kaishengit.util.DpHelper;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by qiyawei on 2016/4/7.
 */
public class UserDao {

    public User findByUsername(String username){
        String sql = "select * from t_user where username = ?";
        return DpHelper.query(sql,new BeanHandler<User>(User.class),username);
    }

    public User findByEmail(String email){
        String sql = "select * from t_user where email = ?";
        return DpHelper.query(sql,new BeanHandler<User>(User.class),email);
    }

    public void saveUser(User user){
        String sql = "insert into t_user(username,password,email,creattime,img,state) values(?,?,?,?,?,?)";
        DpHelper.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getCreattime(),user.getImg(),user.getState());
    }

    public void update(User user) {
        String sql = "update t_user set password=?,email=?,lastlogintime=?,lastloginip=?,img=?,state=? where id=?";
        DpHelper.update(sql,user.getPassword(),user.getEmail(),user.getLastlogintime(),user.getLastloginip(),user.getImg(),user.getState(),user.getId());
    }

    public User findById(Integer uid) {
        String sql = "select * from t_user where id=?";
        return DpHelper.query(sql,new BeanHandler<User>(User.class),uid);
    }
}
