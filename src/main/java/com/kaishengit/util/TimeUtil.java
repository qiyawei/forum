package com.kaishengit.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 存放密码错误次数和验证码显示
 */
public class TimeUtil {

    public static Map<Object,String> map = new HashMap<Object, String>();

    public static void put(Object key,String value) {
        map.put(key, value);
    }

    public static String get(Object key) {
        return map.get(key);

    }

    public static void remove(Object key) {
        map.remove(key);
    }
}
