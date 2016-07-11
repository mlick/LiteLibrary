package com.mlick.demo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mlick.demo.animation.AnimationActivity;
import com.mlick.demo.animation.ProgressAnimationActivity;
import com.mlick.demo.animation.RoundArcActivity;
import com.mlick.demo.butterknife.DemoActivity;
import com.mlick.demo.butterknife.DemoFragmentActivity;
import com.mlick.demo.other.jiankangduoduo.PlayBodyFatScalesActivity;
import com.mlick.demo.retrofit.RetrofitSimpleActivity;
import com.mlick.demo.rxandroid.OriginMainActivity;
import com.mlick.demo.rxandroid.SimpleMainActivity;


public class MainActivity extends ListActivity {

    private String[] items = {
            /*0*/"ButterKnifeDemo",
            /*1*/"ButterKnifeFragmentDemo",
            /*2*/"RxAndroidDemo",
            /*3*/"RxAndroidOriginDemo",
            /*4*/"RetrofitSimpleActivity",
            /*5*/"AnimationActivity",
            /*6*/"ProgressAnimationActivity",
            /*7*/"RoundArcActivity",
            /*8*/"OtherActivity"
    /**/};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
//        DigUtils.getCustomProgressDialog(this, "加载中", R.style.ProgressDialogTheme).show();
//        DigUtils.getCustomProgressDialog(this, "加载中").show();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(this, DemoActivity.class);//ButterKnife
                break;
            case 1:
                intent = new Intent(this, DemoFragmentActivity.class);//ButterKnife
                break;
            case 2:
                intent = new Intent(this, SimpleMainActivity.class);//RxAndroid
                break;
            case 3:
                intent = new Intent(this, OriginMainActivity.class);//RxAndroid
                break;
            case 4:
                intent = new Intent(this, RetrofitSimpleActivity.class);//Retrofit
                break;
            case 5:
                intent = new Intent(this, AnimationActivity.class);//Animation
                break;
            case 6:
                intent = new Intent(this, ProgressAnimationActivity.class);//Animation
                break;
            case 7:
                intent = new Intent(this, RoundArcActivity.class);//Animation
                break;
            case 8:
                intent = new Intent(this, PlayBodyFatScalesActivity.class);//Other
                break;

        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
