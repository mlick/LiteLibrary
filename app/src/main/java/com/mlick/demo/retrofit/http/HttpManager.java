package com.mlick.demo.retrofit.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lxx on 2016/7/21 14:47
 * <p>
 * http请求统一封装 管理的方法
 */
public class HttpManager {

    public static final String BASE_URL = "192.168.4.113:3000/";//baseUrl
    private static final int DEFAULT_TIMEOUT = 6;//默认时间
    private volatile static HttpManager INSTANCE;
    private Retrofit retrofit;

    //    private ApiMovie apiMovie, apiMovieStr;
    //构造方法私有
    private HttpManager() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder().client(builder.build())
                                         .addConverterFactory(GsonConverterFactory.create())
                                         .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                         .baseUrl(BASE_URL).build();
//        apiMovie = retrofit.create(ApiMovie.class);
//        apiMovieStr = retrofit.create(ApiMovie.class);
    }

    //获取单例
    public static HttpManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 得到Retrofit实例
     */
//    public Retrofit getRetrofit() {
//        return retrofit;
//    }

    /**
     * 得到某个Api的server
     *
     * @param kClass
     * @param <K>
     * @return
     */
    public <K> K getApi(Class<K> kClass) {
        if (kClass != null) {
            K k = retrofit.create(kClass);
            return k;
        }
        return null;
    }

    public <T> void getObservable(Observable<T> observable, Subscriber<T> subscriber) {
        if (observable != null && subscriber != null)
            observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

}
