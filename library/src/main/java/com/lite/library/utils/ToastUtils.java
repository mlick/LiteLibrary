package com.lite.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lxx on 2016/9/2 13:36
 * Toast 显示的情况
 */
public class ToastUtils {

    private static Toast toast;

    /**
     * 可以 确保只显示一个toast
     */
    public static void showToast(Context context, String content) {
        if (content == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void showLongToast(Context context, String content) {
        if (content == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }


}
