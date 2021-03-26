package com.pintu.neostore.network;

import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.model.Cart.Cart_APIMSg;
import com.pintu.neostore.model.Cart.listcart_items.ListCartItem_APIMsg;
import com.pintu.neostore.model.ProductDetailed_Model.ProductDetailed_APIMsg;
import com.pintu.neostore.model.ProductList_APIMsg;
import com.pintu.neostore.model.Rate_Model.Rate_APIMsg;
import com.pintu.neostore.model.fetch.FetchAPIMsg;
import com.pintu.neostore.model.order.OrderAPIMsg;
import com.pintu.neostore.model.order.Order_List.order_list_APIMsg;
import com.pintu.neostore.model.order.order_details.OrderDetailsAPIMsg;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {



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


    @FormUrlEncoded
    @POST("users/update")
    Call<APIMsg> editPost(@Header("access_token") String access_token,
                          @Field("first_name") String first_name,
                          @Field("last_name") String last_name,
                          @Field("email") String email,
                          @Field("dob") String dob,
                          @Field("profile_pic") String profile_pic,
                          @Field("phone_no") String phone_no);

    @GET("users/getUserData")
    Call<FetchAPIMsg> fetchPost(@Header("access_token") String access_token);

    @FormUrlEncoded
    @POST("users/change")
    Call<APIMsg> resetPost(@Header("access_token") String access_token,
                          @Field("old_password") String old_password,
                          @Field("password") String password,
                          @Field("confirm_password") String confirm_password);


    @GET("products/getList")
    Call<ProductList_APIMsg> getProductList(@Query("product_category_id") String product_category_id,
                                            @Query("limit") Integer limit,
                                            @Query("page") Integer page);

    @GET("products/getDetail")
    Call<ProductDetailed_APIMsg> getProductDetail(@Query(" product_id") String  product_id);

    @FormUrlEncoded
    @POST("products/setRating")
    Call<Rate_APIMsg> ratePost(@Field("product_id") String product_id,
                                @Field("rating") String rating);

    @FormUrlEncoded
    @POST("addToCart")
    Call<Cart_APIMSg> buyPost(@Header("access_token") String access_token,
                              @Field("product_id") String product_id,
                              @Field("quantity") String quantity);

    @GET("cart")
    Call<ListCartItem_APIMsg> myCartPost(@Header("access_token") String access_token);

    @FormUrlEncoded
    @POST("deleteCart")
    Call<Cart_APIMSg> deleteCartPost(@Header("access_token") String access_token,
                                     @Field("product_id") String product_id);

    @FormUrlEncoded
    @POST("editCart")
    Call<Cart_APIMSg> editCartPost(@Header("access_token") String access_token,
                              @Field("product_id") String product_id,
                              @Field("quantity") String quantity);

    @FormUrlEncoded
    @POST("order")
    Call<OrderAPIMsg> addressPost(@Header("access_token") String access_token,
                                 @Field("address") String address);

    @GET("orderList")
    Call<order_list_APIMsg> orderListPost(@Header("access_token") String access_token);


    @GET("orderDetail")
    Call<OrderDetailsAPIMsg> orderDetailsPost(@Header("access_token") String access_token,
                                              @Query("order_id") String order_id);


}
