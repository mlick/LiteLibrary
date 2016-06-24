package com.mlick.demo.other.animation;

import android.view.View;

import com.mlick.base.BaseActivity;
import com.mlick.demo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lxx on 2016/6/22 16:29
 */
public class ProgressAnimationActivity extends BaseActivity {

    @BindView(R.id.animation) ProgressAnimationView animationView;
    @BindView(R.id.animation2) ProgressAnimationView2 animationView2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_progress_animation;
    }

    @Override
    public void initViewData() {
        animationView.playAnimation();
        animationView2.playAnimation();
    }

    @OnClick({R.id.animation2, R.id.animation})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animation:
                animationView.clickAnimation();
                break;
            case R.id.animation2:
                animationView2.clickAnimation();
                break;
        }
    }
}
