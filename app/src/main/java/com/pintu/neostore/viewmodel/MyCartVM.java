package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.view.drawer.mycart.MyCart;
import com.pintu.neostore.model.Cart.listcart_items.ListCartItem_APIMsg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCartVM extends ViewModel {

    private Context context;
    private MutableLiveData<ListCartItem_APIMsg> mycart_list;

    public MyCartVM(Context context) {
        this.context = context;
    }


    public MutableLiveData<ListCartItem_APIMsg> getMyCartObserver() {

        if (mycart_list == null) {
            mycart_list = new MutableLiveData<>();
        }
        return mycart_list;
    }

    public void loadMyCart(String header) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        System.out.println();
        Call<ListCartItem_APIMsg> call = apiService.myCartPost(header);
        System.out.println("--------------------------TABELSvm-------------");
        call.enqueue(new Callback<ListCartItem_APIMsg>() {
            @Override
            public void onResponse(Call<ListCartItem_APIMsg> call, Response<ListCartItem_APIMsg> response) {
                System.out.println("---------------onResponse-----------TABELSvm-------------");
                if (response.isSuccessful()) {
                    response.code();
                    mycart_list.postValue(response.body());
                    visible();
//                    Toast.makeText(context, response.body().getStatus(), Toast.LENGTH_SHORT).show();

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
                        visible();
                    } catch (Exception e) {

                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        visible();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListCartItem_APIMsg> call, Throwable t) {

                Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff------UnSucessful------------------");
                visible();
            }
        });
    }
    public void visible(){
        MyCart.progressBar.setVisibility(View.GONE);
    }
}

