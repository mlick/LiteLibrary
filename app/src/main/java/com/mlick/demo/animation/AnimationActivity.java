package com.mlick.demo.animation;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mlick.base.BaseActivity;
import com.mlick.demo.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lxx on 2016/6/17 12:59
 */
public class AnimationActivity extends BaseActivity {


    @BindView(R.id.iv) View iv;
    //    @BindView(R.id.start)
    Button button;
    ObjectAnimator animator;

    private boolean isFirstLoading = true, isShow = false;

    int toTopHeight;

    int windowHeight;

    @Override
    public int getLayoutId() {
        return R.layout.activity_animation;
    }


    @Override
    public void initViewData() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        windowHeight = metrics.heightPixels;
//        ViewTreeObserver vto = iv.getViewTreeObserver();
//        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            public boolean onPreDraw() {
//                iv.getViewTreeObserver().removeOnPreDrawListener(this);
//                int height = iv.getMeasuredHeight();
//                int width = iv.getMeasuredWidth();
//                button.setText("方法三: height:" + height + ",width:" + width + "..\n");
//                return true;
//            }
//        });
//        iv = (ImageView) findViewById(R.id.iv);
//        button = (Button) findViewById(R.id.start);
//        ViewTreeObserver vto2 = iv.getViewTreeObserver();
//        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                iv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                button.setText("\n\n" + iv.getHeight() + "," + iv.getWidth());
//            }
//        });
//        iv.post(new Runnable() {
//            @Override
//            public void run() {
//                button.setText("\n\n" + iv.getHeight() + "," + iv.getWidth());
//            }
//        });
//        ObjectAnimator.ofFloat(iv, "y", 0, 300, 0).setDuration(3000).start();
    }

    @OnClick(R.id.start)
    public void onClick(View v) {
        startAnimation();
    }

    private void startAnimation() {
        toTopHeight = windowHeight;//getResources().getDimensionPixelOffset(R.dimen.height2);
        Log.d("AnimationActivity", "toTopHeight ==> " + toTopHeight + "");
        if (isFirstLoading) {
            isFirstLoading = false;
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(iv.getLayoutParams());
            marginLayoutParams.height = toTopHeight;
            marginLayoutParams.setMargins(0, -toTopHeight, 0, 0);
            marginLayoutParams.height = toTopHeight;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(marginLayoutParams);
            iv.setLayoutParams(layoutParams);
//            iv.layout(0, -iv.getHeight(), 0, 0);
//            iv.invalidate();
//            ImageView imageView = new ImageView(this);
//            imageView.getLayoutParams()
//            animator = ObjectAnimator.ofFloat(iv, "translationY", toTopHeight - iv.getHeight(), toTopHeight);
            Log.d("AnimationActivity", "iv.getHeight() ==> " + iv.getHeight() + "");
        }
        showOrHideIv(toTopHeight);
    }

    private void showOrHideIv(int toHeight) {
        if (isShow) {
            animator = ObjectAnimator.ofFloat(iv, "translationY", toHeight, 0);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    iv.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    isShow = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        } else {
            animator = ObjectAnimator.ofFloat(iv, "translationY", 0, toHeight);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    iv.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    isShow = true;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        animator.setDuration(1000);//.setRepeatCount(Integer.MAX_VALUE);
        animator.start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //Here you can get the size!
//        button.setText(iv.getHeight() + "," + iv.getWidth() + "," + iv.getMeasuredHeight() + "," + iv
//                .getMeasuredWidth());
    }
}
