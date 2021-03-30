package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.view.drawer.MyAccount.ResetPass;
import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetVM extends ViewModel {

    private Context context;
    private MutableLiveData<APIMsg> resetlist;

    public ResetVM(Context context) {

        this.context = context;
    }

    public MutableLiveData<APIMsg> getResetListObserver() {

        if (resetlist == null) {
            resetlist = new MutableLiveData<>();
        }
        return resetlist;
    }

    public void loadResetLists(String tok, String curr, String NewP, String Con) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);

        Call<APIMsg> call = apiService.resetPost(tok, curr, NewP, Con);
        System.out.println("--------------------------TABELSvm-------------");
        call.enqueue(new Callback<APIMsg>() {
            @Override
            public void onResponse(Call<APIMsg> call, Response<APIMsg> response) {
                System.out.println("---------------onResponse-----------TABELSvm-------------");
                if (response.isSuccessful()) {

                    resetlist.postValue(response.body());

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

    public void visible() {
        ResetPass.reset.setVisibility(View.VISIBLE);
        ResetPass.progressBar.setVisibility(View.GONE);
    }
}


