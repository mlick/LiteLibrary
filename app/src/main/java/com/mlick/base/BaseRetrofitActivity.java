package com.mlick.base;

import android.os.Bundle;

import com.mlick.demo.retrofit.http.HttpManager;

/**
 * Created by lxx on 2016/7/21 15:21
 */
public abstract class BaseRetrofitActivity extends BaseActivity {


    protected HttpManager httpManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpManager = HttpManager.getInstance();
    }

}
