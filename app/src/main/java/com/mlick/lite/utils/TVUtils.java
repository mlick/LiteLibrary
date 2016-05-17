package com.mlick.lite.utils;

import android.widget.TextView;

/**
 * Created by lxx on 2016/5/17 14:21
 * TextView的工具类
 */
public class TvUtils {

    /**
     * 设置TextView的setText
     */
    public static void setStr(TextView tv, String string) {
        if (tv != null && !StrUtils.isEmpty(string)) {
            tv.setText(string);
        }
    }

    public static void setStr(TextView tv, int string) {
        tv.setText(string + "");
    }
}
