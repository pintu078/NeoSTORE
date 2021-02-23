package com.pintu.neostore.login;

import com.pintu.neostore.APIMsg;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginAPI {


    @FormUrlEncoded
    @POST("login")
    Call<APIMsg> createLogin(@Field("email") String email,
                             @Field("password") String password );


}
