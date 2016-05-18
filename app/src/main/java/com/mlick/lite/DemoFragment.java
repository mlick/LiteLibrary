package com.mlick.lite;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mlick.lite.base.BaseDaoFragment;

import butterknife.BindView;

/**
 * Created by lxx on 2016/5/18 10:52
 */
public class DemoFragment extends BaseDaoFragment {
    @BindView(R.id.hello_tv) TextView helloTv;
    @BindView(R.id.click_btn) Button clickBtn;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    public void initViewData() {
        helloTv.setText("sayHello");
        clickBtn.setText("show Hello");
    }

    @Override
    public void onClick(View v) {

    }
}
