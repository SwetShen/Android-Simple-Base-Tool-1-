package com.archerswet.bookstore.request;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("login")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("account") String account,@Field("password") String password);

    @GET("query")
    Call<ResponseBody> queryAll();



}
