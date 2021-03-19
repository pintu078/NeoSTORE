package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.adapter.MyCartAdapter;
import com.pintu.neostore.model.Cart.Cart_APIMSg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCartVM extends ViewModel {

    private Context context;
    private MutableLiveData<Cart_APIMSg> cart_list;

    public EditCartVM(Context context) {
        this.context = context;
    }

    public MutableLiveData<Cart_APIMSg> getEditCartObserver() {
        if (cart_list == null) {
            cart_list = new MutableLiveData<>();
        }
        return cart_list;
    }

    public void loadEdit(String token, String id, String quantity) {
        Log.d("saurabh ","loadEdit "+id+" "+quantity);

        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<Cart_APIMSg> call = apiService.editCartPost(token, id, quantity);
        call.enqueue(new Callback<Cart_APIMSg>() {
            @Override
            public void onResponse(Call<Cart_APIMSg> call, Response<Cart_APIMSg> response) {
                if (response.isSuccessful()) {
                    cart_list.postValue(response.body());
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
            public void onFailure(Call<Cart_APIMSg> call, Throwable t) {
                Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff---dd---UnSucessful------------------");

            }
        });
    }
}
