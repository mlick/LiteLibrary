package com.mlick.demo.animation;

import android.util.Log;
import android.view.View;

import com.mlick.base.BaseActivity;
import com.mlick.demo.R;
import com.mlick.demo.animation.view.ProgressAnimationView;
import com.mlick.demo.animation.view.ProgressAnimationView2;
import com.mlick.demo.animation.view.RadarView2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lxx on 2016/6/22 16:29
 */
public class ProgressAnimationActivity extends BaseActivity {

    @BindView(R.id.animation) ProgressAnimationView animationView;
    @BindView(R.id.animation2) ProgressAnimationView2 animationView2;
    @BindView(R.id.radarView2) RadarView2 radarView2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_progress_animation;
    }

    @Override
    public void initViewData() {
        animationView.playAnimation();
        animationView2.playAnimation();
//        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int progress = (int) animation.getAnimatedValue();
//
//            }
//        });
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.setDuration(4000);
//        animator.start();
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

    @Override
    protected void onDestroy() {
        if (animationView != null) {
            Log.d("ProgressAniActivity", "OnDestroy animationView");
            animationView.stopAnimation();
        }

        if (animationView2 != null) {
            Log.d("ProgressAniActivity", "OnDestroy animationView2");
            animationView2.stopAnimation();
        }

        if (radarView2 != null) {
            radarView2.stopScan();
        }

        radarView2 = null;
        animationView = null;
        animationView2 = null;
        Log.d("ProgressAniActivity", "OnDestroy");
        super.onDestroy();
    }
}