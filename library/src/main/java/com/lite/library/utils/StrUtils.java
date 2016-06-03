package com.lite.library.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by lxx on 2016/3/28 10:33.
 * String 工具类
 */
public class StrUtils {

    /**
     * ============ 设置TextView的 各种属性 =========================
     */
    public static void setTvStr(TextView tv, String string) {
        if (tv != null && !StrUtils.isEmpty(string)) {
            tv.setText(string);
        }
    }

    public static void setTvStr(TextView tv, int string) {
        tv.setText(string + "");
    }

    public static void setTvColor(Context cxt, TextView tv, int cororId) {
        if (cxt != null && tv != null && cororId != 0) {
            tv.setTextColor(ContextCompat.getColor(cxt, cororId));
        }
    }

    public static void setTvSize(Context cxt, TextView tv, int dimension) {
        if (cxt != null && tv != null && dimension != 0) {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, cxt.getResources().getDimension(dimension));
        }
    }
    /**============ 设置TextView的 各种属性 end =========================**/

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
