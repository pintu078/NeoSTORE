package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.model.Cart.addCart_APIMsg;
import com.pintu.neostore.model.Rate_Model.Rate_APIMsg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyVM extends ViewModel {

    private Context context;
    private MutableLiveData<addCart_APIMsg> buy_list;


    public BuyVM(Context context) {

        this.context = context;
        //   this.loginModel=loginModel;
        //     loginList = new MutableLiveData<>();
    }


    public MutableLiveData<addCart_APIMsg> getBuyObserver() {

        if (buy_list == null) {
            buy_list = new MutableLiveData<>();
            //  loadProductLists();
        }
        return buy_list;
    }

    public void loadBuy(String header, String product_Id, String quantity) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        System.out.println();
        Call<addCart_APIMsg> call = apiService.buyPost(header, product_Id, quantity);
        System.out.println("--------------------------TABELSvm-------------");
        call.enqueue(new Callback<addCart_APIMsg>() {
            @Override
            public void onResponse(Call<addCart_APIMsg> call, Response<addCart_APIMsg> response) {
                System.out.println("---------------onResponse-----------TABELSvm-------------");
                if (response.isSuccessful()) {
                    response.code();
                    buy_list.postValue(response.body());


                    Toast.makeText(context, response.body().getUserMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    System.out.println(" response  code   " + response.code());
                    System.out.println(" response  code   " + response.message());
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
            public void onFailure(Call<addCart_APIMsg> call, Throwable t) {

                Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff------UnSucessful------------------");
            }
        });
    }
}
