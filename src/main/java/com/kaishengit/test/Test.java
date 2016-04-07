package com.kaishengit.test;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;

/**
 * Created by qiyawei on 2016/4/7.
 */
public class Test {
    public static void main(String[] args) {
        User u = new UserDao().findByEmail("1318717d894@qq.com");
        System.out.println(u);
    }
}
