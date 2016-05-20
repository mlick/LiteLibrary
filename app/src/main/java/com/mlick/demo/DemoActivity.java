package com.mlick.demo;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lite.library.utils.StrUtils;
import com.mlick.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by lxx on 2016/5/18 10:15
 */
public class DemoActivity extends BaseActivity {
    @BindView(R.id.hello_tv) TextView helloTv;
    @BindView(R.id.click_btn) Button clickBtn;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    public void initViewData() {
        StrUtils.setStr(helloTv, "sayHello");
        StrUtils.setStr(clickBtn, "show Hello");
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
