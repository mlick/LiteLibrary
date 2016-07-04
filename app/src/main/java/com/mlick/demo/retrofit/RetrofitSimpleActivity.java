package com.mlick.demo.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mlick.demo.R;
import com.mlick.demo.retrofit.apiB.Api;
import com.mlick.demo.retrofit.apiB.ApiMovie;
import com.mlick.demo.retrofit.apiB.ApiWeath;
import com.mlick.demo.retrofit.bean.InfoContainerBean;
import com.mlick.demo.retrofit.bean.MovieBean;
import com.mlick.demo.retrofit.bean.ResultBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lxx on 2016/6/16 14:36
 */
public class RetrofitSimpleActivity extends AppCompatActivity {

    private static OkHttpClient okHttpClient;
    @BindView(R.id.userName) EditText userName;
    @BindView(R.id.userPassWord) EditText userPassWord;

    private ApiMovie apiMovie, apiMovieStr;
    private Retrofit retrofit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_simple);
        ButterKnife.bind(this);
//        testData();

//        initClient(Api.baseUrl, ScalarsConverterFactory.create(), RxJavaCallAdapterFactory
//                .create());
//        testDataStr();
//        initClient(Api.baseUrl, FastJsonConverterFactory.create(), RxJavaCallAdapterFactory
//                .create());
//        testDataFormData();
//        testWeather();
        retrofit = initClient(ApiMovie.url, FastJsonConverterFactory
                .create(), RxJavaCallAdapterFactory.create());
        apiMovie = retrofit.create(ApiMovie.class);
        apiMovieStr = initClient(ApiMovie.url, ScalarsConverterFactory
                .create(), RxJavaCallAdapterFactory.create()).create(ApiMovie.class);
//        testMovie();
//        testRegister();
    }

    private void testMovie() {
        apiMovie.getHomeList().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<MovieBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                System.out.println(">>> onResponse" + e.toString());
            }

            @Override
            public void onNext(MovieBean movieBean) {
                System.out.println(">>> onResponse" + movieBean.toString());
            }
        });

    }


    private Retrofit initClient(String url, Converter.Factory converter, CallAdapter.Factory callAdapter) {
        okHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                                                 .readTimeout(15, TimeUnit.SECONDS).build();
        return new Retrofit.Builder().baseUrl(url).client(okHttpClient)
                                     .addConverterFactory(converter)
                                     .addCallAdapterFactory(callAdapter).build();
    }

    private void testDataStr() {
        Api api = retrofit.create(Api.class);
        api.getDataStrForm("zxColumn4ID20160120162935qhwIrR", "1")
           .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
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
        Api api = retrofit.create(Api.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("ColumnID", "zxColumn4ID20160120162935qhwIrR");
        map.put("Page", "1");
        api.getDataFormMap(map).observeOn(AndroidSchedulers.mainThread())
           .subscribeOn(Schedulers.io()).subscribe(new Subscriber<ArrayList<InfoContainerBean>>() {
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
        Api api = retrofit.create(Api.class);
        api.getData("zxColumn4ID20160120162935qhwIrR", "1")
           .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
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

    private void testLogin(String userName, String passWord) {
        apiMovie.login(userName, passWord).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<ResultBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(">>> onError");
            }

            @Override
            public void onNext(ResultBean resultBean) {
                System.out.println(">>> resultBean" + resultBean.toString());
                Toast.makeText(RetrofitSimpleActivity.this, resultBean
                        .toString(), Toast.LENGTH_SHORT).show();
            }
        });
//        apiMovieStr.loginStr(userName, passWord).observeOn(AndroidSchedulers.mainThread())
//                   .subscribeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println(">>> onError");
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println(">>> onNext" + s);
//            }
//        });
//        apiMovieStr.getUserInfo().observeOn(AndroidSchedulers.mainThread())
//                   .subscribeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println(">>> onError");
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println(">>> onNext" + s);
//            }
//        });
    }

    private void testRegister(String userName, String passWord) {
        apiMovie.register(userName, passWord).enqueue(new Callback<ResultBean>() {
            @Override
            public void onResponse(Call<ResultBean> call, Response<ResultBean> response) {
                System.out.println(">>> onResponse" + call.toString());
                Toast.makeText(RetrofitSimpleActivity.this, response.body()
                                                                    .getMessage(), Toast.LENGTH_SHORT)
                     .show();
            }

            @Override
            public void onFailure(Call<ResultBean> call, Throwable t) {
                System.out.println(">>> onFailure" + t.toString());
            }
        });
    }

    @OnClick({R.id.confirm, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                testLogin(userName.getText().toString(), userPassWord.getText().toString());
                break;
            case R.id.register:
                testRegister(userName.getText().toString(), userPassWord.getText().toString());
                break;
        }
    }
}
