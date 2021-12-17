package com.archerswet.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequest_Interface {

    @GET("query")
    Call<ResponseBody> selectAll();

}
