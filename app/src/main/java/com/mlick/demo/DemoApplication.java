package com.mlick.demo;

import android.app.Application;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by lxx on 2016/5/20 9:32
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //注册crashHandler
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());

        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush

        Log.d("MainActivity ===>>> ", JPushInterface.getRegistrationID(this));


        // 友盟统计 场景类型设置
        MobclickAgent
                .setScenarioType(getApplicationContext(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        //友盟统计 禁止默认的页面统计方式，这样将不会再自动统计Activity
        MobclickAgent.openActivityDurationTrack(false);
    }
}
