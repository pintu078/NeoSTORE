package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.model.ProductDetailed_Model.ProductDetailed_APIMsg;
import com.pintu.neostore.model.Rate_Model.Rate_APIMsg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateVM extends ViewModel {

    private Context context;
    private MutableLiveData<Rate_APIMsg> rate_list;


    public RateVM(Context context) {

        this.context = context;
        //   this.loginModel=loginModel;
        //     loginList = new MutableLiveData<>();
    }



    public MutableLiveData<Rate_APIMsg> getRatingObserver() {

        if (rate_list == null) {
            rate_list = new MutableLiveData<>();
            //  loadProductLists();
        }
        return rate_list;
    }

    public void loadRating(String product_Id,String rating) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        System.out.println();
        Call<Rate_APIMsg> call = apiService.ratePost(product_Id,rating);
        System.out.println("--------------------------TABELSvm-------------");
        call.enqueue(new Callback<Rate_APIMsg>() {
            @Override
            public void onResponse(Call<Rate_APIMsg> call, Response<Rate_APIMsg> response) {
                System.out.println("---------------onResponse-----------TABELSvm-------------");
                if (response.isSuccessful()) {
                    response.code();
                    rate_list.postValue(response.body());


                    Toast.makeText(context, response.body().getUserMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    System.out.println(" response  code   " +response.code());
                    System.out.println(" response  code   " +response.message());
                    Log.d("Saurabh", response.errorBody().toString());
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
            public void onFailure(Call<Rate_APIMsg> call, Throwable t) {

                Toast.makeText(context,"Check Internet Connection",Toast.LENGTH_SHORT).show();
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff------UnSucessful------------------");
            }
        });
    }
}


