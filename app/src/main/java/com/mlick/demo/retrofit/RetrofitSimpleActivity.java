package com.mlick.demo.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lxx on 2016/6/16 14:36
 */
public class RetrofitSimpleActivity extends AppCompatActivity {


    private static OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private Api api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        okHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                                                 .readTimeout(15, TimeUnit.SECONDS)
                                                 .build();
        retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl)
                                         .client(okHttpClient)
                                         .addConverterFactory(FastJsonConverterFactory.create())
//                                         .addConverterFactory(ScalarsConverterFactory.create())
                                         .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                         .build();
        api = retrofit.create(Api.class);
//        testData();
//        testDataStr();
        testDataFormData();
//        testWeather();
    }

    private void testDataStr() {
        api.getDataStrForm("zxColumn4ID20160120162935qhwIrR", "1")
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeOn(Schedulers.io())
           .subscribe(new Subscriber<String>() {
               @Override
               public void onCompleted() {
                   System.out.println("==== onCompleted");
               }

               @Override
               public void onError(Throwable e) {
                   System.out.println("==== onError");
               }

               @Override
               public void onNext(String s) {
                   System.out.println("==== onNext  " + s);
               }
           });
    }

    private void testWeather() {
//        okHttpClient.
        ApiWeath apiWeath = retrofit.create(ApiWeath.class);
        apiWeath.getData().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(">>> onResponse");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(">>> onFailure");
            }
        });
    }

    public void testDataFormData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("ColumnID", "zxColumn4ID20160120162935qhwIrR");
        map.put("Page", "1");
        api.getDataFormMap(map)
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeOn(Schedulers.io())
           .subscribe(new Subscriber<ArrayList<InfoContainerBean>>() {
               @Override
               public void onCompleted() {
                   System.out.println(">>> onCompleted");
               }

               @Override
               public void onError(Throwable e) {
                   System.out.println(">>> onError");
                   System.out.println(e.toString());
               }

               @Override
               public void onNext(ArrayList<InfoContainerBean> infoContainerBeen) {
                   System.out.println(">>> onNext");
               }
           });
    }

    public void testData() {
        api.getData("zxColumn4ID20160120162935qhwIrR", "1")
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeOn(Schedulers.io())
           .subscribe(new Subscriber<ArrayList<InfoContainerBean>>() {
               @Override
               public void onCompleted() {
                   System.out.println(">>> onCompleted");
               }

               @Override
               public void onError(Throwable e) {
                   System.out.println(">>> onError");
                   System.out.println(e.toString());
               }

               @Override
               public void onNext(ArrayList<InfoContainerBean> infoContainerBeen) {
                   System.out.println(">>> onNext");
               }
           });
    }
}
