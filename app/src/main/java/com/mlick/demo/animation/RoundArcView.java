package com.mlick.demo.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.lite.library.utils.PxUtils;


/**
 * 综合评分控件,目前写死三个评分项
 *
 * @author lls
 * @since 16/3/7 上午10:24
 */
public class RoundArcView extends View {
    public static final String TAG = "RoundArcView";
    /**
     * 默认字体颜色
     */
    public static final int DEFAULT_TEXT_COLOR = 0xffffffff;
    /**
     * 默认字体大小
     */
    public static final int DEFAULT_TEXT_SIZE = 24;
    /**
     * 文字画笔
     */
    private Paint textPaint;
    /**
     * 圆弧画笔
     */
    private Paint arcPaint;
    /**
     * 圆弧半径
     */
    private float radius;
    /**
     * 总边距
     */
    private float roundMargin = 28;
    private int innerArcWidth = 30;
    /**
     * 初始角度
     */
    private int startAngel = -145;
    /**
     * 画弧度数
     */
    private int swipeAngel = 110;
    /**
     * 外部矩形
     */
    private RectF outerRect = new RectF();
    /**
     * 文字大小
     */
    private int textSize = DEFAULT_TEXT_SIZE;
    /**
     * 字体颜色
     */
    private int textColor = DEFAULT_TEXT_COLOR;
    /**
     * 评分标记
     */
    private boolean evaluated = true;
    /**
     * 基础数据得分
     */
    private int stepScore = 20;
    /**
     * 拍片数据得分
     */
    private int checkScore = 10;
    /**
     * 检验数据得分
     */
    private int bodyFatScore = 30;

    private String stepLevel = "无";
    private String checkLevel = "无";
    private String bodyFatLevel = "无";


    private float[] angels = new float[3];
    /**
     * 动画旋转角
     */
    private float animateAngel = 0;

    public RoundArcView(Context context) {
        super(context);
        init();
    }

    public RoundArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        this.setClickable(true);

        roundMargin = PxUtils.dip2px(getContext(), roundMargin);
        innerArcWidth = PxUtils.dip2px(getContext(), 15);
        textSize = PxUtils.dip2px(getContext(), 13);


        textPaint = new Paint();
        arcPaint = new Paint();

        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);

        arcPaint.setColor(0xffffffff);
        arcPaint.setAlpha(128);
        arcPaint.setAntiAlias(true);
        arcPaint.setStrokeWidth(6);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getMeasuredWidth();
        float height = getMeasuredHeight();
        float centerX = width / 2;
        float centerY = height / 2;
        if (width > height) {
            radius = height / 2 - roundMargin;
        } else {
            radius = width / 2 - roundMargin;
        }

        outerRect.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        arcPaint.setStrokeWidth(4);
        arcPaint.setAlpha(240);

        for (int i = 0; i < 3; i++) {
            canvas.drawArc(outerRect, startAngel + 120 * i - animateAngel, swipeAngel, false, arcPaint);
        }

        arcPaint.setStrokeWidth(innerArcWidth);
        outerRect.inset(innerArcWidth * 1.35f, innerArcWidth * 1.35f);
        arcPaint.setAlpha(128);

        for (int i = 0; i < 3; i++) {
            canvas.drawArc(outerRect, startAngel + 120 * i + 5 + animateAngel, swipeAngel - 10, false, arcPaint);
        }

        if (evaluated) {
            angels[0] = (swipeAngel - 10) * stepScore / 100.0f;
            angels[1] = (swipeAngel - 10) * checkScore / 100.0f;
            angels[2] = (swipeAngel - 10) * bodyFatScore / 100.0f;
            arcPaint.setAlpha(240);
            for (int i = 0; i < 3; i++) {
                if (angels[i] > 0) {
                    canvas.drawArc(outerRect, startAngel + 120 * i + 5 + animateAngel, angels[i], false, arcPaint);
                }
            }
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("计步" + stepLevel, centerX, centerY - radius - textSize, textPaint);
            textPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText("体检" + checkLevel, centerX + radius, (float) (centerY + radius * Math
                    .tan(Math.PI / 6)), textPaint);
            textPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("体脂" + bodyFatLevel, centerX - radius, (float) (centerY + radius * Math
                    .tan(Math.PI / 6)), textPaint);
        }

    }

    /**
     * 判断输入是否合法,并改变数值
     *
     * @param score 分数
     * @return 大于等于0且小于等于100的分数
     */
    private int checkScore(int score) {
        if (score < 0) {
            score = 0;
        } else if (score > 100) {
            score = 100;
        }
        return score;
    }


    public void evaluate(int stepScore, int checkScore, int bodyFatScore, String stepLevel, String checkLevel, String bodyFatLevel) {
        this.stepLevel = stepLevel;
        this.checkLevel = checkLevel;
        this.bodyFatLevel = bodyFatLevel;
        this.stepScore = checkScore(stepScore);
        this.checkScore = checkScore(checkScore);
        this.bodyFatScore = checkScore(bodyFatScore);
        AnimatorSet evaluateAnim = new AnimatorSet();
        evaluateAnim.play(getEvaluateAnimator(stepScore, checkScore, bodyFatScore))
                    .after(getInitAnimation());
        evaluateAnim.start();

    }

    /**
     * 初始旋转动画
     *
     * @return 旋转动画
     */
    private ObjectAnimator getInitAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "animateAngel", 0f, 720f);
        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                evaluated = true;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                evaluated = false;
            }
        });
        return animator;
    }

    /**
     * 开始评价动画
     *
     * @param stepScore    基础数据得分
     * @param checkScore   拍片数据得分
     * @param bodyFatScore 检验数据得分
     */
    private ObjectAnimator getEvaluateAnimator(int stepScore, int checkScore, int bodyFatScore) {
        this.stepScore = 0;
        this.checkScore = 0;
        this.bodyFatScore = 0;
        PropertyValuesHolder pStepScore = PropertyValuesHolder.ofInt("stepScore", 0, stepScore);
        PropertyValuesHolder pCheckScore = PropertyValuesHolder.ofInt("checkScore", 0, checkScore);
        PropertyValuesHolder pBodyFatScore = PropertyValuesHolder
                .ofInt("bodyFatScore", 0, bodyFatScore);
        ObjectAnimator animator = ObjectAnimator
                .ofPropertyValuesHolder(this, pStepScore, pCheckScore, pBodyFatScore);
        animator.setDuration(1000);
        animator.setStartDelay(100);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                invalidate();
            }
        });
        return animator;
    }


    public float getAnimateAngel() {
        return animateAngel;
    }

    public void setAnimateAngel(float animateAngel) {
        this.animateAngel = animateAngel;
    }

    public int getStepScore() {
        return stepScore;
    }

    public void setStepScore(int stepScore) {
        this.stepScore = stepScore;
    }

    public int getCheckScore() {
        return checkScore;
    }

    public void setCheckScore(int checkScore) {
        this.checkScore = checkScore;
    }

    public int getBodyFatScore() {
        return bodyFatScore;
    }

    public void setBodyFatScore(int bodyFatScore) {
        this.bodyFatScore = bodyFatScore;
    }
}
