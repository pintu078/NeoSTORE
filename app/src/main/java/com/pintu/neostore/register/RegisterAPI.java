package com.pintu.neostore.register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterAPI {


    @POST("register")
    Call<RegisterModel> createPost(@Body RegisterModel registerModel);

//    Call<RegisterModel> createPost(@Field("title") String title,
//                                   @Field("body") String body,
//                                   @Field("userId") long userId););
}
