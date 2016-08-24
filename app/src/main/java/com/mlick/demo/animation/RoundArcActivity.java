package com.mlick.demo.animation;

import android.view.View;

import com.mlick.base.BaseActivity;
import com.mlick.demo.R;
import com.mlick.demo.animation.view.RoundArcView;

import butterknife.BindView;

/**
 * Created by lxx on 2016/7/5 10:00
 */
public class RoundArcActivity extends BaseActivity {
    @BindView(R.id.roundArcView) RoundArcView roundArcView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_round_arc;
    }

    @Override
    public void initViewData() {
        roundArcView.evaluate(30, 40, 20, "", "", "");
    }

    @Override
    public void onClick(View v) {

    }
}