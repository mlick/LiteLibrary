package com.mlick.demo.other.animation;

import android.view.View;

import com.mlick.base.BaseActivity;
import com.mlick.demo.R;

/**
 * Created by lxx on 2016/6/22 16:29
 */
public class ProgressAnimationActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_progress_animation;
    }

    @Override
    public void initViewData() {
        ProgressAnimationView animationView = (ProgressAnimationView) findViewById(R.id.animation);
        animationView.playAnimation();
    }

    @Override
    public void onClick(View v) {

    }
}
