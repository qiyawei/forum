package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ValidateException;
import com.kaishengit.util.ConfigProp;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

/**
 * Created by qiyawei on 2016/4/7.
 */
public class UserService {

    private UserDao userDao = new UserDao();

    public void saveUser(String username,String password,String eamil){
        if(findByEmail(eamil) != null){
            throw new ValidateException("注册失败，账号已被占用");
        }
        if(findByEmail(eamil) != null){
            throw new ValidateException("注册失败，邮箱已被占用");
        }
        password = DigestUtils.md5Hex(password + ConfigProp.get("user.salt"));
        User user = new User();
        user.setEmail(eamil);
        user.setUsername(username);
        user.setCreattime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        user.setPassword(password);
        user.setImg(ConfigProp.get("user.img"));
        user.setState(User.NORMAL);
        userDao.saveUser(user);

    }

    public User findByUsername(String uaername){
        return userDao.findByUsername(uaername);
    }

    public User findByEmail(String email){
        return userDao.findByEmail(email);
    }




}
