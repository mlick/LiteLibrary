package com.mlick.demo.animation;

import android.view.View;

import com.mlick.base.BaseActivity;
import com.mlick.demo.R;

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
        roundArcView.evaluate(20, 30, 60, "", "", "");
    }

    @Override
    public void onClick(View v) {

    }
}
