package com.mlick.lite.utils;

/**
 * Created by lxx on 2016/3/28 10:33.
 * String 工具类
 */
public class StrUtils {


    /**
     * 判断String是否为空
     *
     * @param var0
     * @return
     */
    public static boolean isEmpty(String var0) {
        return var0 == null || var0.length() <= 0;
    }

    public static boolean isNotEmpty(String var0) {
        return !isEmpty(var0);
    }

    /**
     * 批量判断String数组对象中是否有空的数据
     */
    public static boolean isEmptys(String... objs) {
        if (objs.length == 0) {
            return false;
        }
        for (String s : objs) {
            if (isEmpty(s)) {
                return true;
            }
        }
        return false;
    }
}
