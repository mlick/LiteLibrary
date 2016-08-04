package com.mlick.demo.jpushdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mlick.base.BaseActivity;
import com.mlick.demo.MainActivity;
import com.mlick.demo.R;

import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by lxx on 2016/6/2 16:39
 */
public class NotifyDialogActivity extends BaseActivity {

    public Bundle bundle;
    SweetAlertDialog sad;
//    @BindView(R.id.notify_dialog_content) TextView textView;

    @Override
    public int getLayoutId() {
        return 0;//R.layout.activity_notifydialog;
    }

    @Override
    public void initViewData() {

        Log.d("NotifyDialogActivity", "initViewData");
        bundle = getIntent().getBundleExtra("bundle");
        String alert = bundle.getString(JPushInterface.EXTRA_ALERT);
//        textView.setText(alert);

        sad = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?").setContentText(alert)
//                .setCancelText("取消")
                .setConfirmText("重新登陆").showCancelButton(false)
//                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        finishActivity();
//                    }
//                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        JPushInterface.clearAllNotifications(NotifyDialogActivity.this);
                        finishActivity();
                    }
                });
        sad.setCancelable(false);
        sad.setCanceledOnTouchOutside(false);
        sad.show();
    }

    @OnClick({R.id.notify_dialog_cancle, R.id.notify_dialog_sure})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notify_dialog_cancle:
                finish();
                break;
            case R.id.notify_dialog_sure:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        sad = null;
        System.gc();
    }
}
