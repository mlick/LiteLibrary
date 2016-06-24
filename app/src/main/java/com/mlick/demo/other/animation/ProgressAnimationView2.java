package com.mlick.demo.other.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by lxx on 2016/6/22 16:31
 */
public class ProgressAnimationView2 extends View {
    private final Paint mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mProgressPaintSel = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF mProgressRectF;
    private final RectF mProgressRectFsel;
    private float sweepAngle = 0.0f;
    ValueAnimator animator;

    private boolean isRunning = false;

    public ProgressAnimationView2(Context context) {
        this(context, null);
    }

    public ProgressAnimationView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mProgressRectF = new RectF(50, 50, 300, 300);
        mProgressRectFsel = new RectF(50, 50, 300, 300);
        mProgressPaint.setColor(Color.parseColor("#6AAAE8"));
        mProgressPaint.setStrokeWidth(20.0f);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaintSel.setColor(Color.parseColor("#00FCFF"));
        mProgressPaintSel.setStrokeWidth(20.0f);
        mProgressPaintSel.setAntiAlias(true);
        mProgressPaintSel.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
//参数1：圆的范围大小
//        参数2：起始角度 ===> 开始的角度
//        参数3：圆心角角度，360为圆，180为半圆 ==> 长度
//        参数4：中心  ==> 是否显示中心线
//        参数5：画笔Paint，可以设置画线or填充，设置颜色，设置线的粗细等等第四个参数
        canvas.drawArc(mProgressRectF, 0.0f, 360.0f, false, mProgressPaint);
        canvas.drawArc(mProgressRectFsel, -90.0f, sweepAngle, false, mProgressPaintSel);
        canvas.restore();
    }


    public void clickAnimation() {
        if (!isRunning) {
            playAnimation();
        } else {
            stopAnimation();
        }
    }

    public void playAnimation() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stopAnimation();
                sweepAngle = 360;
                invalidate();
                isRunning = false;
            }
        }, 3000);
        animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(3000);
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float vlaue = (float) animation.getAnimatedValue();
                Log.d("animator progress ++> ", sweepAngle + " || " + vlaue);
                sweepAngle = 360 * vlaue;
                invalidate();
            }
        });
        isRunning = true;
        animator.start();
    }

    public void stopAnimation() {
        if (animator != null) {
            animator.cancel();
            isRunning = false;
        }
    }

}
