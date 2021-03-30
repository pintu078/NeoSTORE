package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;
import com.pintu.neostore.view.register.Register;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterVM extends ViewModel {
    private Context context;
    private MutableLiveData<APIMsg> registerList;

    public RegisterVM(Context context) {
        this.context = context;
    }

    public MutableLiveData<APIMsg> getLoginListObserver() {

        if (registerList == null) {
            registerList = new MutableLiveData<>();
        }
        return registerList;
    }

    public void makeRegisterApiCall(String FName, String LName, String Email, String Passord, String CPassword, String Gender, String Phone) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);

        Call<APIMsg> call = apiService.createPost(FName, LName, Email, Passord, CPassword, Gender, Phone);

        call.enqueue(new Callback<APIMsg>() {
            @Override
            public void onResponse(Call<APIMsg> call, Response<APIMsg> response) {
                if (response.isSuccessful()) {

                    registerList.postValue(response.body());
                    System.out.println("--------------------------------------------SUccess------------------------------------------------------");
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    visible();

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        System.out.println("-----DM----------------------------------------");
                        Toast.makeText(
                                context,
                                jObjError.getString("user_msg"),
                                Toast.LENGTH_SHORT).show();
                        visible();
                    } catch (Exception e) {

                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        visible();
                    }
                }
            }

            @Override
            public void onFailure(Call<APIMsg> call, Throwable t) {

                Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff------UnSucessful------------------");
                visible();
            }
        });
    }
    public void visible(){
        Register.register.setVisibility(View.VISIBLE);
        Register.progressBar.setVisibility(View.GONE);
    }
}
