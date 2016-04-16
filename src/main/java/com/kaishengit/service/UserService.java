package com.kaishengit.service;

import com.kaishengit.dao.PasswordDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.Password;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ValidateException;
import com.kaishengit.util.ConfigProp;
import com.kaishengit.util.EmailHelper;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.UUID;

/**
 * Created by qiyawei on 2016/4/7.
 */
public class UserService {

    private UserDao userDao = new UserDao();
    private PasswordDao passwordDao = new PasswordDao();

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
        user.setImg(ConfigProp.get("user.default.img"));
        user.setState(User.NORMAL);
        userDao.saveUser(user);

    }

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email){
        return userDao.findByEmail(email);
    }


    public User login(String username,String password,String ip) {
        User user = userDao.findByUsername(username);
        if(user != null&&user.getPassword().equals(DigestUtils.md5Hex(password + ConfigProp.get("user.salt")))){
            user.setLastlogintime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
            user.setLastloginip(ip);
            userDao.update(user);
            return  user;
        }
        return null;
    }

    public void SendEmail(String username) {
        User user = userDao.findByUsername(username);
        if(user != null){
            String uuid = UUID.randomUUID().toString();
            String url = "http://localhost/forget/newpassword.do?token=" + uuid;
            String title = "论坛-找回密码邮件";
            String htmlmsg = username + ":<br/>请点击超链接"+"<a href=\""+url+"\">"+url+"</a>"+"进行密码重置,该链接30分钟内有效";
            Password password = new Password();
            password.setTime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
            password.setToken(uuid);
            password.setUid(user.getId());
            passwordDao.save(password);
            EmailHelper.sendHtmlEmail(user.getEmail(), title, htmlmsg);
        }

    }

    public void saveForgetPassword(String pwd, String token) {
        Password password = PasswordDao.findByToken(token);
        if(password == null){
            throw new ValidateException("token无效");
        }
        User user = userDao.findById(password.getUid());
        user.setPassword( DigestUtils.md5Hex(pwd+ ConfigProp.get("user.salt")));
        userDao.update(user);
        passwordDao.deleteByToken(token);
    }

    public void validateToken(String token) {
        Password password = PasswordDao.findByToken(token);
        if(password == null){
            throw new ValidateException("链接无效");
        }
        String time = password.getTime();
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dateTime = dateTimeFormat.parseDateTime(time);
        dateTime = dateTime.plusMinutes(30);
        if(!dateTime.isAfterNow()){
            throw new ValidateException("链接失效");
        }



    }

    public void updateUser(User user) {
        userDao.update(user);
    }
    public void updatePasswor(User user,String password){
        password = DigestUtils.md5Hex(password + ConfigProp.get("user.salt"));
        user.setPassword(password);
        userDao.update(user);
    }
}
