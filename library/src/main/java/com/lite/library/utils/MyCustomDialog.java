package com.lite.library.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class MyCustomDialog {
    /**
     * @param context
     * @param title        标题
     * @param content      提示内容
     * @param confirmClick 确认
     * @param cancelClick  取消
     */
    public static void showDialog(Context context, String title, String content, String confirm, DialogInterface.OnClickListener confirmClick, String cancel, DialogInterface.OnClickListener cancelClick) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setTitle(title);
        adb.setMessage(content);
        // adb.setPositiveButton("确定", confirmClick);
        // adb.setNegativeButton("取消", cancelClick);
        if (confirmClick != null) adb.setPositiveButton(confirm, confirmClick);

        if (cancelClick != null) adb.setNegativeButton(cancel, cancelClick);
        adb.show();
    }

    /**
     * 只有确定键的布局
     *
     * @param context
     * @param title        标题
     * @param content      提示内容
     * @param confirmClick 确认
     */
    public static void showDialog2(Context context, String title, String content, String confirm, DialogInterface.OnClickListener confirmClick) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setTitle(title);
        adb.setMessage(content);
        // adb.setPositiveButton("确定", confirmClick);
        adb.setPositiveButton(confirm, confirmClick);
        adb.show();
        // .setIcon(android.R.drawable.ic_dialog_info)
    }

    public static void showEditDialog(Context ctx, String title, EditText editText, String confirm, DialogInterface.OnClickListener confirmClick) {
        AlertDialog.Builder adb = new AlertDialog.Builder(ctx);
        adb.setTitle(title);
        adb.setView(editText);
        adb.setPositiveButton(confirm, confirmClick);
        adb.show();
    }

    public interface EditDialogListener {
        public void parserEdit(String str);
    }

//    public static AlertDialog showCustomEditDialog(Context ctx, final EditDialogListener listener, String title, String title2, String hint) {
//        AlertDialog.Builder adb = new AlertDialog.Builder(ctx);
//        View view = LayoutInflater.from(ctx).inflate(R.layout.alert_edit_dialog, null);
//        StrUtils.setTvStr2((TextView) view.findViewById(R.id.alert_title), title);
//        StrUtils.setTvStr2((TextView) view.findViewById(R.id.alert_title2), title2);
//        final EditText editText = (EditText) view.findViewById(R.id.alert_et);
//        editText.setHint(hint);
//        adb.setView(view);
//        adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (listener != null && editText != null) {
//                    listener.parserEdit(StrUtils.getStr(editText));
//                }
//            }
//        });
//        adb.setNegativeButton("取消", null);
//        return adb.show();
//    }
//
//    public static AlertDialog showCustomEditDialog2(Context ctx, String confirm, DialogInterface.OnClickListener confirmClick, FMsgCallGvSetAdapter adapter, AdapterView.OnItemClickListener itemClickListener, String priceItem, String titileStr) {
//        AlertDialog.Builder adb = new AlertDialog.Builder(ctx);
//        View view = LayoutInflater.from(ctx).inflate(R.layout.alert_edit_dialog2, null);
//        GridView gridView = (GridView) view.findViewById(R.id.alert_gv);
//        gridView.setAdapter(adapter);
//        gridView.setOnItemClickListener(itemClickListener);
//        adapter.editText = (EditText) view.findViewById(R.id.alert_et);
//        StrUtils.setTvStr((TextView) view.findViewById(R.id.price_item_tv), priceItem);
//        StrUtils.setTvStr((TextView) view.findViewById(R.id.alert_title), titileStr);
//        adb.setView(view);
//        adb.setPositiveButton(confirm, confirmClick);
//        adb.setNegativeButton("取消", null);
//        return adb.show();
//    }

    /**
     * 只有确定键的布局
     *
     * @param context
     * @param title        标题
     * @param content      提示内容
     * @param confirmClick 确认
     */
    public static void showDialog3(Context context, String title, String content, String confirm, DialogInterface.OnClickListener confirmClick) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setTitle(title);
        adb.setMessage(content);
        // adb.setPositiveButton("确定", confirmClick);
        adb.setPositiveButton(confirm, confirmClick);
        adb.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        adb.show();
        // .setIcon(android.R.drawable.ic_dialog_info)
    }

    public static void showDialogList(Context context, String titleString, final String items[], DialogInterface.OnClickListener listener) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        // adb.setTitle("列表框");
        adb.setTitle(titleString);
//        adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setItems(items, listener);
        adb.show();
    }

    /**
     * 提示信息 列表对话框
     *
     * @param context
     * @param items
     * @param editText 设置某个布局内容为点击的内容
     */
    public static void showDialogList2(Context context, String titleString, final String items[], final EditText editText) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        // adb.setTitle("列表框");
        adb.setTitle(titleString);
        adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                editText.setText(items[which]);
            }
        });
        adb.show();
    }

    /**
     * 自定义的Dialog框架
     * <p/>
     * 需要在外部生命一个 AlertDialog 例如这样 final AlertDialog dlg = new
     * AlertDialog.Builder(this).create();
     * 布局文件可以参考layout目录下的dialog_call_telephone.xml这个文件
     * <p/>
     * 创造dialog的context
     *
     * @param RLayoutId      自定义的布局
     * @param ROkId          确定按钮的id
     * @param RCancelId      取消按钮的id
     * @param okListener     确定按钮的监听事件
     * @param cancleListener 取消按钮的监听事件
     */
    public static void showExitGameAlert(AlertDialog dlg, int RLayoutId, int ROkId, int RCancelId, View.OnClickListener okListener, View.OnClickListener cancleListener, String content, int RTextId) {
        dlg.show(); // 先进行调用，再进行设置

        Window window = dlg.getWindow();// *** 主要就是在这里实现这种效果的.
        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
        window.setContentView(RLayoutId);

        // 为确认按钮添加事件
        View ok = window.findViewById(ROkId);
        ok.setOnClickListener(okListener);

        // 为取消按钮添加事件
        View cancel = window.findViewById(RCancelId);
        cancel.setOnClickListener(cancleListener);

        TextView text = (TextView) window.findViewById(RTextId);
        text.setText(content);
    }
}
