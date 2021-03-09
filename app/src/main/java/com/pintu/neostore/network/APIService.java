package com.pintu.neostore.network;

import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.model.ProductList_APIMsg;
import com.pintu.neostore.model.ProductList_Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @GET("products/getList")
    Call<ProductList_APIMsg> getProductList(@Query("product_category_id") String product_category_id,
                                            @Query("limit") String limit,
                                            @Query("page") String page);


    @FormUrlEncoded
    @POST("users/login")
    Call<APIMsg> createLogin(@Field("email") String email,
                             @Field("password") String password );

    @FormUrlEncoded
    @POST("users/forgot")
    Call<APIMsg> createForgot(@Field("email") String email);

    @FormUrlEncoded
    @POST("users/register")
    Call<APIMsg> createPost(@Field("first_name") String first_name,
                            @Field("last_name") String last_name,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("confirm_password") String confirm_password,
                            @Field("gender") String gender,
                            @Field("phone_no") String phone_no);



}
