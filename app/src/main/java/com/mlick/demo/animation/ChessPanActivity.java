package com.mlick.demo.animation;

import android.widget.TextView;
import android.widget.Toast;

import com.mlick.base.BaseActivity;
import com.mlick.demo.R;
import com.mlick.demo.animation.view.ChessPanView;

import butterknife.BindView;

/**
 * Created by lxx on 2016/8/24 11:19
 */
public class ChessPanActivity extends BaseActivity implements ChessPanView.OnTouchEventListent {

    @BindView(R.id.chessPanView) ChessPanView chessPanView;
    @BindView(R.id.downTv) TextView downTv;
    @BindView(R.id.upTv) TextView upTv;
    @BindView(R.id.postionTv) TextView postionTv;

//    String downStr = "Down : x =s%    y = s%";
//    String upStr = "Down : x =s%    y = s%";

    @Override
    public int getLayoutId() {
        return R.layout.activity_chesspan;
    }

    @Override
    public void initViewData() {
        chessPanView.onTouchEventListent = this;
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
        Toast.makeText(this, steps, Toast.LENGTH_SHORT).show();
    }

}
