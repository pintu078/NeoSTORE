package com.pintu.neostore.login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPI {

    @POST("login")
    Call<LoginModel> createLogin(@Body LoginModel loginModel);
}
