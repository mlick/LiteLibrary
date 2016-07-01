package com.mlick.demo.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lxx on 2016/6/28 11:26
 */
public interface ApiMovie {

    String url = "http://192.168.4.102:3000/";

    @GET("phone")
    Observable<MovieBean> getHomeList();

    @FormUrlEncoded
    @POST("phone/user/login")
    Observable<ResultBean> login(@Field("name") String userName, @Field("pwd") String passWord);

    @FormUrlEncoded
    @POST("phone/user/login")
    Observable<String> loginStr(@Field("name") String userName, @Field("pwd") String passWord);

    @GET("phone/user/info")
    Observable<String> getUserInfo();

    @FormUrlEncoded
    @POST("phone/user/register")
    Call<UserLoginInfo> registerBody(@Body UserLoginInfo user);

    @FormUrlEncoded
    @POST("phone/user/register")
    Call<ResultBean> register(@Field("name") String userName, @Field("pwd") String passWordr);
}
