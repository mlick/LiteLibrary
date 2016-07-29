package com.mlick.demo.jpushdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mlick.base.BaseActivity;
import com.mlick.demo.MainActivity;
import com.mlick.demo.R;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by lxx on 2016/6/2 16:39
 */
public class NotifyDialogActivity extends BaseActivity {

    public Bundle bundle;

    @BindView(R.id.notify_dialog_content) TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_notifydialog;
    }

    @Override
    public void initViewData() {
        bundle = getIntent().getBundleExtra("bundle");
        String alert = bundle.getString(JPushInterface.EXTRA_ALERT);
        textView.setText(alert);
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
}
