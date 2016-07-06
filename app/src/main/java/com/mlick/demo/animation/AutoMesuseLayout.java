package com.mlick.demo.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by lxx on 2016/6/17 14:54
 */
public class AutoMesuseLayout extends RelativeLayout {
    private final String TAG = AutoMesuseLayout.class.getName();

    public AutoMesuseLayout(Context context) {
        super(context);
    }

    public AutoMesuseLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoMesuseLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d(TAG, changed + " " + l + " " + t + " " + r + " " + b);
        Log.d(TAG, " height : width >>> " + getHeight() + " " + getWidth());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, widthMeasureSpec + " " + heightMeasureSpec);
    }
}
