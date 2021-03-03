package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.home.Home;
import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.model.LoginModel;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;
import com.pintu.neostore.register.AppConstant;
import com.pintu.neostore.register.MyData;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginVM extends ViewModel {


//    public MutableLiveData<String> Email = new MutableLiveData<>();
//    public MutableLiveData<String> Pass = new MutableLiveData<>();

  //  public LoginModel loginModel;
    private Context context;
    private MutableLiveData<APIMsg> loginList;

    public LoginVM(Context context){

        this.context = context;
     //   this.loginModel=loginModel;
   //     loginList = new MutableLiveData<>();
    }

    public MutableLiveData<APIMsg> getLoginListObserver() {

        if(loginList == null){
            loginList = new MutableLiveData<>();
         //   makeApiCall();
        }
        return loginList;
    }

    public void  makeApiCall(String E,String P){
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);

        Call<APIMsg> call = apiService.createLogin(E,P);

        call.enqueue(new Callback<APIMsg>() {
            @Override
            public void onResponse(Call<APIMsg> call, Response<APIMsg> response) {
                if(response.isSuccessful()){

                   loginList.postValue(response.body());
                    APIMsg postResponse = response.body();
                    String content = "";
                    content += "Code: " + response.code()+ "\n";
                    content += "First Name: " + postResponse.getData().getEmail() + "\n";
                    content += "First Name: " + postResponse.getData().getEmail() + "\n";
                    content += "Last Name: " + postResponse.getData().getFirstName() + "\n";
                    String F =  postResponse.getData().getFirstName();
                    String L =  postResponse.getData().getLastName();
                    String E =  postResponse.getData().getEmail();
                    String G =  postResponse.getData().getGender();
                    String Ph =  postResponse.getData().getPhoneNo();
//                    if(AppConstant.mydatas.isEmpty()){
//                        AppConstant.mydatas.add(new MyData(F,L,E,G,Ph));
//                    }else {
//                        AppConstant.mydatas.remove(0);
//                        System.out.println(content);
                        AppConstant.mydatas.add(0,new MyData(F,L,E,G,Ph));
                  //  }
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
