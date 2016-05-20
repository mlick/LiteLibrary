package com.mlick.demo;

import android.view.View;

import com.lite.library.base.BaseDaoActivity;

import butterknife.ButterKnife;


/**
 * Created by lxx on 2016/5/18 10:51
 */
public class DemoFragmentActivity extends BaseDaoActivity {

    @Override
    protected void viewInject() {
        ButterKnife.bind(this);
    }

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
