package com.mlick.demo;

import android.app.Application;

import com.lite.library.utils.CrashHandler;

/**
 * Created by lxx on 2016/5/20 9:32
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //注册crashHandler
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
