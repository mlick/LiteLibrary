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

    private static final String TAG = ChessPanView.class.getName();
    private int panWidth = 0;//棋盘的高和宽
    private int startX, startY;//开始画时的xy坐标轴
    private int panInterval;//棋盘的间隔大小
    private Paint paintLine;
    private Paint paintCircle;
    private Paint painMeasure;//画动画时的画笔
    private Paint painText;//画text的画笔

    private Context mContext;
    private Path blackPath, whitePath;//白色和黑色棋子的path
    private Path selectPath; //选中的path
    private Path mDst;//// 硬件加速的BUG
    private Path mMeasurePath;
    private PathMeasure mPathMeasure;// 路径动画
    private PathEffect mEffect;

    private int allChess = 0;//当前棋盘的棋子个数
    private int firstSteps = 0, secondSteps = 0;//第一和第二个用户所需要多放或者可以吃的步数
    private boolean isFirstPlayer = true;// 是否是第一个用户下棋子 true 表示user1; false 表示user2
    private boolean isEatChess = false; // 是否开始吃棋子操作,true 表示开始吃
    private boolean isAddChess = true;// 是否开始添加棋子
    private boolean isStart = false; //是否开始下下棋,true 表示开始 , false 表示结束
    private boolean isMove = false; //是否开始移动
    private boolean isSeleced = false; //是否表示选中,开始移动
    private boolean isGameOver = false;// 是否GameOVer
    /**
     * 存储所有数据的一个数据集合
     * 表示一个二维数据[x][y]
     * 数值表示意义： 0 表示无棋子, 1 表示黑方(user1), 2表示白方(user2)
     */
    private int[][] panDatas = new int[5][5];
    private int mPostionX = 0, mPostionY = 0, mSPostionX = 0, mSPostionY = 0;
    private float mAnimatorValue, mLength = 0;
    private String drawTextStr = "当前用户：白";

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
        paintLine.setStrokeWidth(2);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setColor(Color.BLACK);

        paintCircle = new Paint();
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(2);
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setColor(Color.BLACK);//默认的是黑色

        painText = new Paint();
        painText.setStrokeWidth(1);
        painText.setColor(Color.RED);
        painText.setTextSize(40);

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
        selectPath.reset();
        if (isStart) {// 判断是否已经开始
//            Log.d("ChessPanView", "moveTo x: " + (startX + panInterval * mPostionX - panInterval / 2) + " y: " + (startY + panInterval * mPostionY - panInterval / 4));
//            Log.d("ChessPanView", "lineTo x: " + (startX + panInterval * mPostionX - panInterval / 2) + " y: " + (startY + panInterval * mPostionY - panInterval / 2));
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
//                    Log.d("ChessPanView", "x : " + (startX + panInterval * i) + " y: " + (startY + panInterval * j));
                    blackPath
                            .addCircle(startX + panInterval * i, startY + panInterval * j, panInterval / 3, Path.Direction.CCW);
                    canvas.drawPath(blackPath, paintCircle);
                }
            }
        }
    }

    // 画棋盘
    private void drawPan(Canvas canvas) {
        canvas.drawText(drawTextStr, startX / 3, startY / 3, painText);
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
        if (isGameOver) {
            return true;
        }
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
        if (!isStart) {//将开始置为true
            isStart = true;
        }
        String drawTextStepStr = "";
        if (isAddChess && panDatas[postionX][postionY] == 0) {//下棋子
            allChess++;
            panDatas[postionX][postionY] = isFirstPlayer ? 1 : 2;
//            panDatas[postionX][postionY] = 1;
        } else if (isEatChess && panDatas[postionX][postionY] != 0) {//吃棋子
            if (isFirstPlayer && panDatas[postionX][postionY] == 2) {
                allChess--;
                panDatas[postionX][postionY] = 0;
            } else if (!isFirstPlayer && panDatas[postionX][postionY] == 1) {
                allChess--;
                panDatas[postionX][postionY] = 0;
            } else {
                Log.d(TAG, "can not eat!");
                return;
            }
        } else if (isMove) {//移动棋子
            // 判断当前用户是否可以选中
            if (panDatas[postionX][postionY] == (isFirstPlayer ? 1 : 2)) {//表示选中棋子
                mSPostionX = mPostionX;
                mSPostionY = mPostionY;
                isSeleced = true;
                invalidate();
                return;
            } else if (isSeleced && panDatas[postionX][postionY] == 0) {
                // 判断是否可以移动
                if (isCanMove(postionX, postionY)) {
                    panDatas[mSPostionX][mSPostionY] = 0;
                    panDatas[postionX][postionY] = (isFirstPlayer ? 1 : 2);
                    isSeleced = false;
                } else {
                    Log.d(TAG, "can not move!");
                    return;
                }
            } else {
                Log.d(TAG, "can not move 2 !");
                return;
            }
        } else {
            Log.d(TAG, "can not add eat move !");
            return;
        }

        if (!isEatChess) {
            int steps = LoadUtil.mapJudgment(panDatas, postionX, postionY);
            if (steps != 0) {
                onTouchEventListent
                        .moreStepListener("用户： " + (isFirstPlayer ? "1" : "2") + " 需要多走步数： " + steps);
            }
            if (isFirstPlayer) {
                firstSteps += steps;
            } else {
                secondSteps += steps;
            }
        }

        if (firstSteps > 0 || secondSteps > 0) {
            if (isMove) {
                isEatChess = true;
                drawTextStepStr = "  可以多吃掉的步数 ： " + (firstSteps + secondSteps);
            } else {
                drawTextStepStr = "  可以多执行的步数 ： " + (firstSteps + secondSteps);
            }
        } else {
            drawTextStepStr = "";
        }

        turnPlay();

        if (allChess == 25) {//放满时的情况
            isEatChess = true;
            isFirstPlayer = false;
            isAddChess = false;
            firstSteps = 0;//自动清零
            secondSteps = 0;// 自动清零
            drawTextStepStr = "  现在黑吃白一个棋子";
        } else if (allChess == 24 && isEatChess && !isAddChess && !isMove) {//刚开始吃时候的阶段
            isFirstPlayer = true;
            drawTextStepStr = "  现在白吃黑一个棋子";
        } else if (allChess == 23 && isEatChess && !isAddChess && !isMove) {//刚开始吃时候的阶段
            isEatChess = false;
            isMove = true;
            isFirstPlayer = false;
            drawTextStepStr = "  现在黑开始移动棋子";
        }
        drawTextStr = "当前用户：" + (isFirstPlayer ? "白" : "黑") + drawTextStepStr;

        mMeasurePath.reset();
        mMeasurePath.moveTo(startX + panInterval * mPostionX, startY + panInterval * mPostionY);
        mMeasurePath
                .lineTo(startX + panInterval * (mPostionX + 1), startY + panInterval * (mPostionY + 1));
        mMeasurePath.close();
        mPathMeasure.setPath(mMeasurePath, false);
        mLength = mPathMeasure.getLength();
//        startAnimation();

        int res = checkIsGameOver();
        if (res == 1) {
            drawTextStr = "白方 WIN !";
        } else if (res == 2) {
            drawTextStr = "黑方 WIN !";
        } else if (res == 3) {
            drawTextStr = "平局 !";
        }
        invalidate();
    }

    private int checkIsGameOver() {
        if (isAddChess) {
            return 4;
        }
        int ws = 0, bs = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (panDatas[i][j] == 1) {
                    ws++;
                } else if (panDatas[i][j] == 2) {
                    bs++;
                }
            }
        }
        if (ws == 0 && bs == 0) {
            return 4;
        }
        if (ws < 4 || bs < 4) {//如果双方中其中一方的小于4个棋子的话，将会决出输赢
            isGameOver = true;
            if (ws == bs) {
                return 3;
            }
            return ws > bs ? 1 : 2;
        }
        return 4;
    }

    /**
     * 判断是否可以移动
     * <p/>
     * mSPostionX 原先选中的x坐标
     * mSPostionY 原先选中的y坐标
     *
     * @param postionX 现在选中的x坐标
     * @param postionY 现在选中的y坐标
     * @return true 表示可以移动, false 表示不能移动
     */
    private boolean isCanMove(int postionX, int postionY) {
        if ((((Math.abs(mSPostionX - postionX) == 1) && mSPostionY == postionY) || (Math
                .abs(mSPostionY - postionY) == 1) && postionX == mSPostionX) && panDatas[postionX][postionY] == 0) {
            return true;
        }
        return false;
    }

    /**
     * 轮换用户
     */
    private void turnPlay() {
        if (isFirstPlayer) {
            if (firstSteps == 0) {
                isFirstPlayer = false;
                if (isMove) {
                    isEatChess = false;
                }
            } else {
                firstSteps--;
            }
        } else if (secondSteps == 0) {
            isFirstPlayer = true;
            if (isMove) {
                isEatChess = false;
            }
        } else {
            secondSteps--;
        }
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

//        void curUser(String userName);
    }

    public OnTouchEventListent onTouchEventListent;


    public void resetChessPan() {
        panDatas = null;
        allChess = 0;
        isStart = false;
        isAddChess = true;
        isEatChess = false;
        isFirstPlayer = true;
        isMove = false;
        panDatas = new int[5][5];
        mSPostionX = 0;
        mSPostionY = 0;
        firstSteps = 0;
        secondSteps = 0;
        isSeleced = false;
        isGameOver = false;
        drawTextStr = "当前用户：白";
        invalidate();
//        testAllPan();
    }


    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * 测试初始化所有的棋盘
     */
    public void testAllPan() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i + j) % 2 == 0) {
                    panDatas[i][j] = 1;
                } else {
                    panDatas[i][j] = 2;
                }
            }
        }
        panDatas[4][4] = 0;
        allChess = 24;
        dealClickEvent(4, 4);
//        isFirstPlayer = false;
//        isEatChess = true;
//        isStart = true;
//        isAddChess = false;
//        allChess = 25;
    }
}
