package com.pintu.neostore.viewmodel;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.view.drawer.MyAccount.EditProfile;
import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditProfileVM extends ViewModel {

    private Context context;
    private MutableLiveData<APIMsg> editlist;

    public EditProfileVM(Context context) {

        this.context = context;
    }


    public MutableLiveData<APIMsg> getEditListObserver() {

        if (editlist == null) {
            editlist = new MutableLiveData<>();
        }
        return editlist;
    }

    public void loadEditLists(String token, String FName, String LName, String Email, String dob, String Pic, String Phone) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        System.out.println("load     " + token + " " + FName + " " + LName + " " + Email + " " + dob + " " + Pic + " " + Phone);
        Call<APIMsg> call = apiService.editPost(token, FName, LName, Email, dob, Pic, Phone);
        System.out.println("--------------------------TABELSvm-------------");
        call.enqueue(new Callback<APIMsg>() {
            @Override
            public void onResponse(Call<APIMsg> call, Response<APIMsg> response) {
                System.out.println("---------------onResponse-----------TABELSvm-------------");
                if (response.isSuccessful()) {
                    response.code();
                    editlist.postValue(response.body());

                    Toast.makeText(context, response.body().getUserMsg(), Toast.LENGTH_SHORT).show();
                    visible();

                } else {
                    System.out.println(" response  code   " + response.code());
                    System.out.println(" response  code   " + response.message());
                    Log.d("Saurabh", response.errorBody().toString());
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        System.out.println("-----DM----------------------------------------");
                        Toast.makeText(
                                context,
                                jObjError.getString("message"),
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
        EditProfile.Submit.setVisibility(View.VISIBLE);
        EditProfile.progressBar.setVisibility(View.GONE);
    }
}



