package com.kaishengit.test;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.util.EmailHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
;

/**
 * Created by qiyawei on 2016/4/7.
 */
public class Test {
    public static void main(String[] args) {
        //Logger logger = Logger.getLogger(Test.class);
        Logger logger = LoggerFactory.getLogger(Test.class);
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");


    }

}
