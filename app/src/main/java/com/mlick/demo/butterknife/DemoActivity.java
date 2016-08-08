package com.mlick.demo.butterknife;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lite.library.utils.StrUtils;
import com.mlick.base.BaseActivity;
import com.mlick.demo.R;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by lxx on 2016/5/18 10:15
 */
public class DemoActivity extends BaseActivity {
    @BindView(R.id.hello_tv) TextView helloTv;
    @BindView(R.id.click_btn) Button clickBtn;
    @BindView(R.id.selectBtn_tv) TextView selectBtn_tv;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    public void initViewData() {
        StrUtils.setTvStr(helloTv, "sayHello");
        StrUtils.setTvStr(clickBtn, "show Hello");

//        RippleDrawable.createRipple(selectBtn_tv, ContextCompat.getColor(this, android.R.color.white));
//        RippleDrawable.c(getResources(),R.drawable.riple);
        toolbar.setNavigationIcon(R.drawable.ic_launcher);
        toolbar.setTitle("返回");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity();
            }
        });
    }

    @OnClick({R.id.hello_tv, R.id.click_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hello_tv:
                Toast.makeText(this, "sayHello", Toast.LENGTH_SHORT).show();
                break;
            case R.id.click_btn:
                Toast.makeText(this, "show Hello", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
