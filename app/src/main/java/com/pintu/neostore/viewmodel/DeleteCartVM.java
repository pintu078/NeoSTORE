package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.model.Cart.Cart_APIMSg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteCartVM extends ViewModel {

    private Context context;
    private MutableLiveData<Cart_APIMSg> delete_list;


    public DeleteCartVM(Context context) {

        this.context = context;
        //   this.loginModel=loginModel;
        //     loginList = new MutableLiveData<>();
    }


    public MutableLiveData<Cart_APIMSg> getDeleteCartObserver() {

        if (delete_list == null) {
            delete_list = new MutableLiveData<>();
            //  loadProductLists();
        }
        return delete_list;
    }

    public void     loadDeleteCart(String header, String id) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        System.out.println();
        Call<Cart_APIMSg> call = apiService.deleteCartPost(header, id);
        System.out.println("--------------------------TABELSvm-------------");
        call.enqueue(new Callback<Cart_APIMSg>() {
            @Override
            public void onResponse(Call<Cart_APIMSg> call, Response<Cart_APIMSg> response) {
                System.out.println("---------------onResponse-----------TABELSvm-------------");
                if (response.isSuccessful()) {
                    response.code();
                    delete_list.postValue(response.body());


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

