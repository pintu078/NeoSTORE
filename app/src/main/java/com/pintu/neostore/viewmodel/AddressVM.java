package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.view.drawer.order.Address;
import com.pintu.neostore.model.order.OrderAPIMsg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressVM extends ViewModel {

    private Context context;
    private MutableLiveData<OrderAPIMsg> address_list;

    public AddressVM(Context context) {
        this.context = context;
    }

    public MutableLiveData<OrderAPIMsg> getAddressObserver() {
        if (address_list == null) {
            address_list = new MutableLiveData<>();
        }
        return address_list;
    }

    public void loadAddress(String token, String address) {

        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<OrderAPIMsg> call = apiService.addressPost(token, address);
        call.enqueue(new Callback<OrderAPIMsg>() {
            @Override
            public void onResponse(Call<OrderAPIMsg> call, Response<OrderAPIMsg> response) {
                if (response.isSuccessful()) {
                    address_list.postValue(response.body());
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
            public void onFailure(Call<OrderAPIMsg> call, Throwable t) {
                Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff---dd---UnSucessful------------------");
                visible();
            }
        });
    }
    public void visible(){
        Address.order.setVisibility(View.VISIBLE);
        Address.progressBar.setVisibility(View.GONE);
    }
}

