package com.mlick.demo.retrofit.apiB;

import com.mlick.demo.retrofit.bean.InfoContainerBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lxx on 2016/6/16 14:13
 */
public interface Api {
    String baseUrl = "http://ceshi.wenbing.cn/v1/";

    @FormUrlEncoded
    @POST("v1zixuninfo/getarticlelist")
    Observable<ArrayList<InfoContainerBean>> getData(@Field("ColumnID") String columnID, @Field("Page") String page);

    @FormUrlEncoded
    @POST("v1zixuninfo/getarticlelist")
    Observable<ArrayList<InfoContainerBean>> getDataFormMap(@FieldMap HashMap<String, String> map);

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("v1zixuninfo/getarticlelist")
    Observable<String> getDataStr(@Query("ColumnID") String columnID, @Query("Page") String page);

    @FormUrlEncoded
    @POST("v1zixuninfo/getarticlelist")
    Observable<String> getDataStrForm(@Field("ColumnID") String columnID, @Field("Page") String page);

    @POST("v1zixuninfo/getarticlelist")
    Call<String> getDataStr2(@Query("ColumnID") String columnID, @Query("Page") String page);

    @POST("v1zixuninfo/getarticlelist")
    Call<List<InfoContainerBean>> getData2(@Query("ColumnID") String columnID, @Query("Page") String page);


    @POST("v1zixuninfo/getarticlelist")
    Call<List<InfoContainerBean>> getData2(@Body ApiBean bean);


}
