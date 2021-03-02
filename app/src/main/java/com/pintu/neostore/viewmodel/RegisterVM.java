package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterVM extends ViewModel {
    private Context context;
    private MutableLiveData<APIMsg> registerList;

    public RegisterVM(Context context){
        this.context = context;
    }

    public MutableLiveData<APIMsg> getLoginListObserver() {

        if(registerList == null){
            registerList = new MutableLiveData<>();
        }
        return registerList;
    }

    public void  makeRegisterApiCall(String F,String L,String E,String P,String CP,String G,String Ph){
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);

        Call<APIMsg> call = apiService.createPost(F,L,E,P,CP,G,Ph);

        call.enqueue(new Callback<APIMsg>() {
            @Override
            public void onResponse(Call<APIMsg> call, Response<APIMsg> response) {
                if(response.isSuccessful()){

                    registerList.postValue(response.body());
                    System.out.println("--------------------------------------------SUccess------------------------------------------------------");
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(com.pintu.neostore.login.Login.this, com.pintu.neostore.home.Home.class);
//                    startActivity(intent);

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        System.out.println("-----DM----------------------------------------");
                        Toast.makeText(
                                context,
                                jObjError.getString("user_msg"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {

                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<APIMsg> call, Throwable t) {

                Toast.makeText(context,"Check Internet Connection",Toast.LENGTH_SHORT).show();
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff------UnSucessful------------------");
            }
        });
    }
}
