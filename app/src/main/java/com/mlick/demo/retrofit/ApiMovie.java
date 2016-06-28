package com.mlick.demo.retrofit;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lxx on 2016/6/28 11:26
 */
public interface ApiMovie {

    String url = "http://192.168.1.48:3000";

    @GET("/phone")
    Observable<MovieBean> getHomeList();

    @FormUrlEncoded
    @POST("/phone/user/login")
    Observable<ResultBean> login(@Field("name") String userName, @Field("pwd") String passWord);

    @FormUrlEncoded
    @POST("/phone/user/login")
    Observable<String> loginStr(@Field("name") String userName, @Field("pwd") String passWord);


    @GET("/phone/user/info")
    Observable<String> getUserInfo();
}
