package com.mlick.lite;

import android.view.View;

import com.mlick.lite.base.BaseDaoActivity;

/**
 * Created by lxx on 2016/5/18 10:51
 */
public class DemoFragmentActivity extends BaseDaoActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_demo_fragment;
    }

    @Override
    public void initViewData() {
        setFragment(new DemoFragment(), R.id.frame);
    }

    @Override
    public void onClick(View v) {

    }
}
