package com.lite.library.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import java.util.List;

/**
 * Created by lxx on 2016/8/3 9:08
 */
public class AppUtils {

    /**
     * 判断当前App处于前台还是后台
     * 需添加<uses-permission android:name="android.permission.GET_TASKS"/>
     * 并且必须是系统应用该方法才有效
     * 注: 效果不是很好
     *
     * @return true 表示在后台运行 || false 表示前台运行
     */
    public static boolean isAppBackground(Context context) {
        if (context != null) {
            ActivityManager am = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            @SuppressWarnings("deprecation") List<ActivityManager.RunningTaskInfo> tasks = am
                    .getRunningTasks(1);
            if (!tasks.isEmpty()) {
                ComponentName topActivity = tasks.get(0).topActivity;
                if (!topActivity.getPackageName().equals(context.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
