package com.kaishengit.util;

import java.io.IOException;
import java.util.Properties;

public class ConfigProp {

    private static Properties prop = new Properties();
    static {

        try {
            prop.load(ConfigProp.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取config.propreties文件异常");
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

}
