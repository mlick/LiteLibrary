package com.mlick.demo.retrofit;

import com.mlick.base.BaseRetrofitActivity;
import com.mlick.demo.retrofit.api.ApiMovie;
import com.mlick.demo.retrofit.bean.MovieBean;

import rx.Subscriber;

/**
 * Created by lxx on 2016/7/21 16:42
 */
public class RetrofitDemoActivity extends BaseRetrofitActivity {


    private ApiMovie apiMovie;


    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initViewData() {
        apiMovie = httpManager.getApi(ApiMovie.class);
        httpManager.getObservable(apiMovie.getHomeList(), new Subscriber<MovieBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MovieBean movieBean) {

            }
        });
    }
}
