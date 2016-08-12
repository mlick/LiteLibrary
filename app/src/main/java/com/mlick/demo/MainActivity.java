package com.mlick.demo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mlick.demo.animation.MyFamilyEntity;

import cn.jpush.android.api.JPushInterface;


public class MainActivity extends ListActivity {

    private String[][] items = {//显示的命，类名，注解
            /*0*/{"ButterKnifeDemo", "butterknife.DemoActivity", "ButterKnife普通的demo"},
            /*1*/{"ButterKnifeFragmentDemo", "butterknife.DemoFragmentActivity", "带Fragment的ButterKnife的demo"},
            /*2*/{"RxAndroidDemo", "rxandroid.SimpleMainActivity", "RxAndroid的demo"},
            /*3*/{"RxAndroidOriginDemo", "rxandroid.OriginMainActivity", "RxAndroid的官方demo"},
            /*4*/{"RetrofitSimpleActivity", "retrofit.RetrofitSimpleActivity", "Retrofit的官方demo"},
            /*5*/{"AnimationActivity", "animation.AnimationActivity", "动画demo1"},
            /*6*/{"ProgressAnimationActivity", "animation.ProgressAnimationActivity", "进度条动画"},
            /*7*/{"RoundArcActivity", "animation.RoundArcActivity", "半圆动画"},
            /**/{"ListDemoActivity", "recyclerview.ListDemoActivity", "RecyclerView的demo"},
            /**/{"OtherActivity1", "other.jiankangduoduo.PlayBodyFatScalesActivity", "其它demo"}
    /**/};

    public static boolean isForeground = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] showItems = new String[items.length];
        for (int i = 0; i < items.length; i++) {
            showItems[i] = items[i][0];
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, showItems);
        setListAdapter(adapter);

        MyFamilyEntity serBean2 = (MyFamilyEntity) getIntent().getExtras().get("aaa2");
        serBean2.getSerBean2().test();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent();
        try {
            intent.setClassName("com.mlick.demo", "com.mlick.demo." + items[position][1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isForeground = true;
        JPushInterface.resumePush(getApplicationContext());
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JPushInterface.stopPush(getApplicationContext());
    }
}