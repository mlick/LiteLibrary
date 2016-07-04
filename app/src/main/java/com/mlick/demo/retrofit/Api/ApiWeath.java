package com.mlick.demo.retrofit.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lxx on 2016/6/16 18:02
 */
public interface ApiWeath {

    public static final String apiWeather = "http://www.weather.com.cn/data/sk/";

    @GET("101010100.html")
    public Call<String> getData();

}
