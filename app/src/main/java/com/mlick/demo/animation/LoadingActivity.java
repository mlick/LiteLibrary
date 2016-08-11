package com.mlick.demo.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mlick.base.BaseActivity;
import com.mlick.demo.R;

/**
 * Created by lxx on 2016/8/8 13:46
 */
public class LoadingActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initViewData() {
        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_launcher);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        setContentView(imageView, params);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        objectAnimator.setDuration(1500);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                gotoMain();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        objectAnimator.start();
    }

    /**
     * 跳转到首页
     */
    private void gotoMain() {
        Intent intent = new Intent();
//            Class<?> clzz = (Class<?>) Class.forName("MainActivity").newInstance();
//            intent.setClass(this, Class.forName("MainActivity"));
        try {
            intent.setClassName("com.mlick.demo", "com.mlick.demo.MainActivity");
            startActivity(intent);
            finishActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
