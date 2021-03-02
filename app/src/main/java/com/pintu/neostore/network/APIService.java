package com.pintu.neostore.network;

import com.pintu.neostore.model.APIMsg;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST("login")
    Call<APIMsg> createLogin(@Field("email") String email,
                             @Field("password") String password );

    @FormUrlEncoded
    @POST("forgot")
    Call<APIMsg> createForgot(@Field("email") String email);

    @FormUrlEncoded
    @POST("register")
    Call<APIMsg> createPost(@Field("first_name") String first_name,
                            @Field("last_name") String last_name,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("confirm_password") String confirm_password,
                            @Field("gender") String gender,
                            @Field("phone_no") String phone_no);
}
