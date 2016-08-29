package com.mlick.demo.animation.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lxx on 2016/8/24 10:10
 */
public class ChessPanView extends View {

    private int panWidth = 0;//棋盘的高和宽
    private int startX, startY;//开始画时的xy坐标轴
    private int panInterval;//棋盘的间隔大小
    private Paint paintLine;
    private Paint paintCircle;
    private Paint painMeasure;//画动画时的画笔

    private Context mContext;
    private Path blackPath, whitePath;//白色和黑色棋子的path
    private Path selectPath; //选中的path
    private Path mDst;//// 硬件加速的BUG
    private Path mMeasurePath;
    private PathMeasure mPathMeasure;// 路径动画
    private PathEffect mEffect;

    private int allChess = 0;//当前棋盘的棋子个数
    private boolean isFirstPlayer = true;// 是否是第一个用户下棋子
    private boolean isEatChess = false; // 是否开始吃棋子操作,true 表示开始吃
    private boolean isStart = false; //是否开始下下棋,true 表示开始 , false 表示结束

    /**
     * 存储所有数据的一个数据集合
     * 表示一个二维数据[x][y]
     * 数值表示意义： 0 表示无棋子, 1 表示黑方, 2表示白方
     */
    private int[][] panDatas = new int[5][5];
    private int mPostionX = 0, mPostionY = 0;
    private float mAnimatorValue, mLength = 0;

    public ChessPanView(Context context) {
        super(context, null);
    }

    public ChessPanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
//        startAnimation();
    }

    private void startAnimation() {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                mEffect = new DashPathEffect(new float[]{mLength, mLength}, mAnimatorValue * mLength);
                painMeasure.setPathEffect(mEffect);
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(1);
        valueAnimator.start();
    }


    private void initView() {
        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setStrokeWidth(1);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setColor(Color.BLACK);

        paintCircle = new Paint();
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(1);
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setColor(Color.BLACK);//默认的是黑色

        blackPath = new Path();
        selectPath = new Path();

        mDst = new Path();
        painMeasure = new Paint();
        painMeasure.setStrokeWidth(2);
        painMeasure.setStyle(Paint.Style.STROKE);
        painMeasure.setColor(Color.RED);
        mMeasurePath = new Path();
        mPathMeasure = new PathMeasure();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mDst.reset();
//        // 硬件加速的BUG
//        mDst.lineTo(0, 0);

        canvas.save();
        drawPan(canvas);
        drawChess(canvas);
        drawSelect(canvas);
        canvas.restore();
    }


    /**
     * 绘画选中的棋子
     */
    private void drawSelect(Canvas canvas) {

        if (isStart) {// 判断是否已经开始
            Log.d("ChessPanView", "moveTo x: " + (startX + panInterval * mPostionX - panInterval / 2) + " y: " + (startY + panInterval * mPostionY - panInterval / 4));
            Log.d("ChessPanView", "lineTo x: " + (startX + panInterval * mPostionX - panInterval / 2) + " y: " + (startY + panInterval * mPostionY - panInterval / 2));
            selectPath.reset();
            //画左上 |——
            selectPath
                    .moveTo(startX + panInterval * mPostionX - panInterval / 2, startY + panInterval * mPostionY - panInterval / 4);
            selectPath
                    .lineTo(startX + panInterval * mPostionX - panInterval / 2, startY + panInterval * mPostionY - panInterval / 2);
            selectPath
                    .lineTo(startX + panInterval * mPostionX - panInterval / 4, startY + panInterval * mPostionY - panInterval / 2);

            //画右上 |——
            selectPath
                    .moveTo(startX + panInterval * mPostionX + panInterval / 4, startY + panInterval * mPostionY - panInterval / 2);
            selectPath
                    .lineTo(startX + panInterval * mPostionX + panInterval / 2, startY + panInterval * mPostionY - panInterval / 2);
            selectPath
                    .lineTo(startX + panInterval * mPostionX + panInterval / 2, startY + panInterval * mPostionY - panInterval / 4);

            //画左下 |——
            selectPath
                    .moveTo(startX + panInterval * mPostionX - panInterval / 2, startY + panInterval * mPostionY + panInterval / 4);
            selectPath
                    .lineTo(startX + panInterval * mPostionX - panInterval / 2, startY + panInterval * mPostionY + panInterval / 2);
            selectPath
                    .lineTo(startX + panInterval * mPostionX - panInterval / 4, startY + panInterval * mPostionY + panInterval / 2);

            //画右下 |——
            selectPath
                    .moveTo(startX + panInterval * mPostionX + panInterval / 2, startY + panInterval * mPostionY + panInterval / 4);
            selectPath
                    .lineTo(startX + panInterval * mPostionX + panInterval / 2, startY + panInterval * mPostionY + panInterval / 2);
            selectPath
                    .lineTo(startX + panInterval * mPostionX + panInterval / 4, startY + panInterval * mPostionY + panInterval / 2);
//            selectPath.close();
            canvas.drawPath(selectPath, paintLine);
        }

//        if (isEatChess && allChess == 25) {// 当下到满全棋子时，取消显示
//            selectPath.reset();// 暂时不好使
//        }
//        canvas.drawPath(mMeasurePath, painMeasure);
    }
    /**
     * 画棋子
     *
     * @param canvas
     */
    private void drawChess(Canvas canvas) {
//        PathMeasure
        if (panDatas == null) {
            return;
        }
        for (int i = 0; i < panDatas.length; i++) {
            for (int j = 0; j < 5; j++) {
                if (panDatas[i][j] != 0) {
                    if (panDatas[i][j] == 1) {
                        paintCircle.setColor(Color.WHITE);
                    } else {
                        paintCircle.setColor(Color.BLACK);
                    }
                    blackPath.reset();
                    Log.d("ChessPanView", "x : " + (startX + panInterval * i) + " y: " + (startY + panInterval * j));
                    blackPath
                            .addCircle(startX + panInterval * i, startY + panInterval * j, panInterval / 3, Path.Direction.CCW);
                    canvas.drawPath(blackPath, paintCircle);
                }
            }
        }
    }

    // 画棋盘
    private void drawPan(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            canvas.drawLine(startX + panInterval * i, startY, startX + panInterval * i, panWidth, paintLine);// 画垂直线
            canvas.drawLine(startX, startY + panInterval * i, panWidth, startY + panInterval * i, paintLine);// 画水平线
            //Log.d("ChessPanView", "垂直线+" + i + " ：起始点 (" + (startX + panInterval * i) + "," + startY + ")" + "终点(" + (startX + panInterval * i) + "," + panWidth + ")");
            // Log.d("ChessPanView", "水平线+" + i + " ：起始点 (" + startX + "," + (startY + panInterval * i) + ")" + "终点(" + panWidth + "," + (startY + panInterval * i) + ")");
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        panWidth = getMeasuredWidth() / 6 * 5;
        panInterval = panWidth / 5;
        startX = panWidth / 5;
        startY = panWidth / 5;
        Log.d("ChessPanView", "Width : " + panWidth + " Interval : " + panInterval + " startX : " + startX + " startY : " + startY);
    }

    float downX = 0, downY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                if (onTouchEventListent != null) {
//                    onTouchEventListent.downListener(event.getRawX() + "", event.getRawY() + "");
                    onTouchEventListent.downListener(downX + "", downY + "");
                }
                break;
            case MotionEvent.ACTION_UP:
                float x = event.getX();
                float y = event.getY();
                if (onTouchEventListent != null) {
//                    onTouchEventListent.upListener(event.getRawX() + "", event.getRawY() + "");
                    onTouchEventListent.upListener(event.getX() + "", event.getY() + "");
                    mPostionX = getPanPostion(downX);
                    mPostionY = getPanPostion(downY);
                    if (Math.abs(downX - x) < panInterval / 2 &&
                            Math.abs(downY - y) < panInterval / 2 &&
                            mPostionX == getPanPostion(x) &&
                            mPostionY == getPanPostion(y) &&
                            mPostionX > 0 && mPostionX < 6 &&
                            mPostionY < 6 && mPostionY > 0) {//说明点击的事件是在某个角上
                        onTouchEventListent.postionListener(mPostionX + "", mPostionY + "");
                        mPostionX = mPostionX - 1;// 转成0从开始的数据形式
                        mPostionY = mPostionY - 1;// 转成0从开始的数据形式
                        dealClickEvent(mPostionX, mPostionY);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
    }

    /**
     * 处理点击事件
     */
    private void dealClickEvent(int postionX, int postionY) {
//        try {
//            int steps = LoadUtil.mapJudgment(panDatas, postionX, postionY);
//            if (steps != 0) {
//                onTouchEventListent.moreStepListener(steps + "");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if (!isEatChess && panDatas[postionX][postionY] == 0) {//下棋子
            allChess++;
            panDatas[postionX][postionY] = isFirstPlayer ? 1 : 2;
        } else if (isEatChess && panDatas[postionX][postionY] != 0) {//吃棋子
            allChess--;
            panDatas[postionX][postionY] = 0;
        } else {
            return;
        }

        if (allChess == 25) {
            isEatChess = true;
        } else if (allChess == 0) {
            isEatChess = false;
        }
        if (isFirstPlayer) {
            isFirstPlayer = false;
        } else {
            isFirstPlayer = true;
        }
        if (!isStart) {//将开始置为true
            isStart = true;
        }

        mMeasurePath.reset();
//        mMeasurePath.moveTo(100, 100);
//        mMeasurePath.lineTo(100, 500);
//        mMeasurePath.lineTo(400, 300);
        mMeasurePath.moveTo(startX + panInterval * mPostionX, startY + panInterval * mPostionY);
        mMeasurePath
                .lineTo(startX + panInterval * (mPostionX + 1), startY + panInterval * (mPostionY + 1));
        mMeasurePath.close();
        mPathMeasure.setPath(mMeasurePath, false);
        mLength = mPathMeasure.getLength();

//        startAnimation();
        invalidate();
    }

    public int getPanPostion(float postion) {
        return Math.round(postion / panInterval);
    }
    //    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//
//    }

    public interface OnTouchEventListent {
        void downListener(String x, String y);

        void upListener(String x, String y);

        void postionListener(String x, String y);

        void moreStepListener(String steps);
    }

    public OnTouchEventListent onTouchEventListent;
}
