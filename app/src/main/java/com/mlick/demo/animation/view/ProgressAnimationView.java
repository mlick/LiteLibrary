package com.mlick.demo.animation.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lxx on 2016/6/22 16:31
 */
public class ProgressAnimationView extends View {
    private final Paint mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mProgressPaintSel = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF mProgressRectF;
    private final RectF mProgressRectFsel;
    private float sweepAngle = 10.0f;
    ValueAnimator animator;

    public ProgressAnimationView(Context context) {
        this(context, null);
    }

    public ProgressAnimationView(Context context, AttributeSet attrs) {
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
        mProgressPaintSel.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
//参数1：圆的范围大小
//        参数2：起始角度
//        参数3：圆心角角度，360为圆，180为半圆
//        参数4：中心
//        参数5：画笔Paint，可以设置画线or填充，设置颜色，设置线的粗细等等第四个参数
        canvas.drawArc(mProgressRectF, 0.0f, 360.0f, false, mProgressPaint);
        canvas.drawArc(mProgressRectFsel, 90.0f + sweepAngle, 90.0f, false, mProgressPaintSel);
        canvas.restore();
    }

    public void playAnimation() {
        if (animator == null) {
            animator = ValueAnimator.ofFloat(0, 100);
            animator.setDuration(1000);
            animator.setRepeatCount(Integer.MAX_VALUE);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    sweepAngle += 1;
                    sweepAngle %= 360;
                    invalidate();
                }
            });
        } else {
            animator.reverse();
        }
        animator.start();
    }

    public void stopAnimation() {
        if (animator != null) {
            animator.end();
        }
    }

    public void clickAnimation() {
        if (isAnimation()) {
            playAnimation();
        } else {
            stopAnimation();
        }
    }

    public boolean isAnimation() {
        if (animator != null) {
            return animator.isRunning();
        }
        return false;
    }
}
