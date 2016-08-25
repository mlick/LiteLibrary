package com.mlick.demo.animation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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

    private Context mContext;
    private Path blackPath, whitePath;//白色和黑色棋子的path

    public ChessPanView(Context context) {
        super(context, null);
    }

    public ChessPanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }


    private void initView() {
        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setStrokeWidth(1);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setColor(Color.BLACK);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (int i = 0; i < 5; i++) {
            Log.d("ChessPanView", "垂直线+" + i + " ：起始点 (" + (startX + panInterval * i) + "," + startY + ")" + "终点(" + (startX + panInterval * i) + "," + panWidth + ")");
            canvas.drawLine(startX + panInterval * i, startY, startX + panInterval * i, panWidth, paintLine);// 画垂直线
            Log.d("ChessPanView", "水平线+" + i + " ：起始点 (" + startX + "," + (startY + panInterval * i) + ")" + "终点(" + panWidth + "," + (startY + panInterval * i) + ")");
            canvas.drawLine(startX, startY + panInterval * i, panWidth, startY + panInterval * i, paintLine);// 画水平线
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
                    int postionX = getPanPostion(downX);
                    int postionY = getPanPostion(downY);
                    if (Math.abs(downX - x) < panInterval / 2 &&
                            Math.abs(downY - y) < panInterval / 2 &&
                            postionX == getPanPostion(x) &&
                            postionY == getPanPostion(y) &&
                            postionX > 0 && postionX < 6 &&
                            postionY < 6 && postionY > 0) {//说明点击的事件是在某个角上
                        onTouchEventListent.postionListener(postionX + "", postionY + "");
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
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
    }

    public OnTouchEventListent onTouchEventListent;
}
