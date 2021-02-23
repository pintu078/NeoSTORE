package com.pintu.neostore.forgot;

import com.pintu.neostore.APIMsg;
import com.pintu.neostore.login.LoginModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ForgotAPI {
    @FormUrlEncoded
    @POST("forgot")
    Call<APIMsg> createForgot(@Field("email") String email);
}
