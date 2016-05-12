package com.mlick.lite.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by lxx on 2016/5/12 13:36
 * 系统工具类
 */
public class SysUtils {

    /**
     * 隐藏键盘
     *
     * @param ctx
     */
    public static void hideSoftKeyWord(Activity ctx) {
        View view = ctx.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 用户未给权限的帮助dialog
     */
    private static void showHelperDialog(final Context context, String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("帮助");
        builder.setCancelable(false);
        builder.setMessage("当前应用缺少必要" + s + "权限。\n \n请点击“设置”—“权限”，打开所需权限 \n \n 最后点击两次后退按钮，即可返回");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((Activity) context).finish();
            }
        });
        builder.show();
    }

    /**
     * 检测是否有存储权限
     *
     * @param context
     * @return
     */
    public static boolean checkWESPermission(Context context) {
        return checkPermission(context, " 存储 ");
    }

    public static boolean checkPermission(Context context, String s) {
        boolean is = selfPermissionGranted(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!is) {
            showHelperDialog(context, s);
        }
        return is;
    }

    /**
     * 检测是否有某个权限
     *
     * @param context    上下文
     * @param permission 需要的权限
     * @return true 表示有 ||  false表示没有
     */
    public static boolean selfPermissionGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED;
    }
}
