package com.archerswet.uploadimg.request;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RequestInterface {

    @POST("login")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("account") String account,@Field("password") String password);

    @GET("query")
    Call<ResponseBody> queryAll();


    @POST("test")
    @Multipart
    Call<ResponseBody> uploadFile(@Part("title") String title, @Part MultipartBody.Part file);

}
