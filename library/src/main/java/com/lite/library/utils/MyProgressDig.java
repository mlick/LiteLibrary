package com.lite.library.utils;

import android.app.ProgressDialog;
import android.content.Context;


/**
 * 自定义的进度条
 *
 * @author lxx
 * @date 2014-9-4
 * @time 下午5:13:30
 */
public class MyProgressDig {


    private static ProgressDialog loadingDialog;


    /**
     * 自定义的ProgressDialog对话框进行的处理
     */
    public static ProgressDialog getCustomProgressDialog(Context context, String content, boolean canceledOnTouchOutside, int dialogTheam) {
        ProgressDialog progressDialog = dialogTheam == 0 ? new ProgressDialog(context) : new ProgressDialog(context, dialogTheam);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(content);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        return progressDialog;
    }

    /**
     * 默认情况下是不能消失的
     *
     * @param context
     * @param content
     * @return
     */
    public static ProgressDialog getCustomProgressDialog(Context context, String content) {
        return getCustomProgressDialog(context, content, false, 0);
    }

    public static ProgressDialog getCustomProgressDialog(Context context, String content, int dialogTheam) {
        return getCustomProgressDialog(context, content, false, dialogTheam);
    }


    public static void show(Context ctx) {
        show(ctx, "加载中...");
    }

    public static void show(Context ctx, String s) {
        initDialog(ctx, s);
        loadingDialog.show();
    }

    public static void dismiss() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    private static synchronized void initDialog(Context ctx, String s) {
        if (loadingDialog == null && ctx != null) {
            loadingDialog = new ProgressDialog(ctx);
            loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loadingDialog.setMessage(s);
            loadingDialog.setCanceledOnTouchOutside(false);
        } else if (loadingDialog != null && ctx != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
