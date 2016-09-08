package com.mlick.demo.animation;

import android.os.Handler;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mlick.base.BaseActivity;
import com.mlick.demo.R;
import com.mlick.demo.animation.view.ChessPanView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by lxx on 2016/8/24 11:19
 */
public class ChessPanActivity extends BaseActivity implements ChessPanView.OnTouchEventListent {

    @BindView(R.id.chessPanView) ChessPanView chessPanView;
    @BindView(R.id.downTv) TextView downTv;
    @BindView(R.id.upTv) TextView upTv;
    @BindView(R.id.postionTv) TextView postionTv;
    @BindView(R.id.log_tv) TextView logTv;
    @BindView(R.id.scrollView) ScrollView scrollView;

//    String downStr = "Down : x =s%    y = s%";
//    String upStr = "Down : x =s%    y = s%";

    @Override
    public int getLayoutId() {
        return R.layout.activity_chesspan;
    }

    @Override
    public void initViewData() {
        chessPanView.onTouchEventListent = this;
////        Uri uri = Uri
////                .parse("http://img1.ph.126.net/YBfF5kkH-4ouHNxVu7Skpg==/6598082416865500891.gif");
//        Uri uri = Uri.parse("res://" + getPackageName() + "/" + R.drawable.icon_of_no_data);
//        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
////        draweeView.setImageURI(uri);
//        DraweeController draweeController = Fresco.newDraweeControllerBuilder().setUri(uri)
//                                                  .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
//                                                  .build();
//        draweeView.setController(draweeController);
    }

    @Override
    public void downListener(String x, String y) {
//        downTv.setText(String.format(downStr, new String[]{x, y}));
        downTv.setText("Down Position: x = " + x + "  y = " + y);
    }

    @Override
    public void upListener(String x, String y) {
//        upTv.setText(String.format(upStr, new String[]{x, y}));
        upTv.setText("Up Position: x = " + x + "  y = " + y);
    }

    @Override
    public void postionListener(String x, String y) {
//        Toast.makeText(this, x + "," + y, Toast.LENGTH_SHORT).show();
        postionTv.setText("Click Position : x = " + x + "  y = " + y);
    }

    @Override
    public void moreStepListener(String steps) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 100);
        logTv.setText(logTv.getText() + "\n" + steps);
//        Toast.makeText(this, steps, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.restart)
    public void onClick(View v) {

        chessPanView.resetChessPan();
        logTv.setText("log日志输出: ");
    }

    @OnLongClick(R.id.restart)
    public boolean onLongClick(View view) {
        chessPanView.resetChessPan();
        logTv.setText("log日志输出: ");
        chessPanView.testAllPan();
        return true;
    }

}