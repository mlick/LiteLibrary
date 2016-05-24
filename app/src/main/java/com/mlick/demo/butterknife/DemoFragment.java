package com.mlick.demo.butterknife;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lite.library.utils.StrUtils;
import com.mlick.base.BaseFragment;
import com.mlick.demo.R;

import butterknife.BindView;

/**
 * Created by lxx on 2016/5/18 10:52
 */
public class DemoFragment extends BaseFragment {
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

    @Override
    public void onClick(View v) {

    }
}
