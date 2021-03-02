package com.pintu.neostore.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {


    public static  String BASE_URL = "http://staging.php-dev.in:8844/trainingapp/api/users/";

    private static Retrofit retrofit;

    public static Retrofit getRetroClient(){

        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://staging.php-dev.in:8844/trainingapp/api/users/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;
    }
}
