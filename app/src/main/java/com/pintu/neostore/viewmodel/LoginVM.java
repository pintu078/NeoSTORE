package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.view.login.Login;
import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginVM extends ViewModel {

    private Context context;
    private MutableLiveData<APIMsg> loginList;

    public LoginVM(Context context) {

        this.context = context;
    }

    public MutableLiveData<APIMsg> getLoginListObserver() {

        if (loginList == null) {
            loginList = new MutableLiveData<>();
        }
        return loginList;
    }

    public void makeApiCall(String E, String P) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);

        Call<APIMsg> call = apiService.createLogin(E, P);

        call.enqueue(new Callback<APIMsg>() {
            @Override
            public void onResponse(Call<APIMsg> call, Response<APIMsg> response) {
                if (response.isSuccessful()) {

                    loginList.postValue(response.body());
                    APIMsg postResponse = response.body();
                    String content = "";
                    content += "Code: " + response.code() + "\n";
                    content += "First Name: " + postResponse.getData().getEmail() + "\n";
                    content += "First Name: " + postResponse.getData().getEmail() + "\n";
                    content += "Last Name: " + postResponse.getData().getFirstName() + "\n";

                    System.out.println(content);

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
        Login.Login.setVisibility(View.VISIBLE);
        Login.progressBar.setVisibility(View.GONE);
    }

}
