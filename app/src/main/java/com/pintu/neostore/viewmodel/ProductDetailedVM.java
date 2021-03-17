package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.model.ProductDetailed_Model.ProductDetailed_APIMsg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailedVM extends ViewModel {

    private Context context;
    private MutableLiveData<ProductDetailed_APIMsg> prod_details_list;


    public ProductDetailedVM(Context context) {

        this.context = context;
        //   this.loginModel=loginModel;
        //     loginList = new MutableLiveData<>();
    }



    public MutableLiveData<ProductDetailed_APIMsg> getProdDetailsObserver() {

        if (prod_details_list == null) {
            prod_details_list = new MutableLiveData<>();
            //  loadProductLists();
        }
        return prod_details_list;
    }

    public void loadProductDetailed(String product_Id) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        System.out.println();
        Call<ProductDetailed_APIMsg> call = apiService.getProductDetail(product_Id);
        System.out.println("--------------------------TABELSvm-------------");
        call.enqueue(new Callback<ProductDetailed_APIMsg>() {
            @Override
            public void onResponse(Call<ProductDetailed_APIMsg> call, Response<ProductDetailed_APIMsg> response) {
                System.out.println("---------------onResponse-----------TABELSvm-------------");
                if (response.isSuccessful()) {
                    response.code();
                    prod_details_list.postValue(response.body());
                    Log.d("saurabh","response "+response.body().getData().getProductImages().get(0).getImage());

                 //   Toast.makeText(context, response.body().getStatus(), Toast.LENGTH_SHORT).show();

                } else {
                    System.out.println(" response  code   " +response.code());
                    System.out.println(" response  code   " +response.message());
                    Log.d("Saurabh", response.errorBody().toString());
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        System.out.println("-----DM----------------------------------------");
                        Toast.makeText(
                                context,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {

                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ProductDetailed_APIMsg> call, Throwable t) {

                Toast.makeText(context,"Check Internet Connection",Toast.LENGTH_SHORT).show();
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff------UnSucessful------------------");
            }
        });
    }
}

