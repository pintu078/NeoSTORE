package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.pintu.neostore.drawer.tabel.Tables;
import com.pintu.neostore.model.ProductList_APIMsg;
import com.pintu.neostore.model.ProductList_Data;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabelsVM extends ViewModel {

    private Context context;
    private MutableLiveData<List<ProductList_Data>> productlist;
    String Page = "";

    public TabelsVM(Context context){

        this.context = context;
        //   this.loginModel=loginModel;
        //     loginList = new MutableLiveData<>();
    }

    public TabelsVM(){

    }

    public MutableLiveData<List<ProductList_Data>> getTableListObserver() {

        if(productlist == null){
            productlist = new MutableLiveData<List<ProductList_Data>>();
            loadProductLists("1",10,1);

          //  loadProductLists();
        }
        return productlist;
    }

    public void loadProductLists(String id, final Integer li, final Integer page){
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);

        Call<ProductList_APIMsg> call = apiService.getProductList(id,li,page);
        System.out.println("--------------------------TABELSvm-------------");
        call.enqueue(new Callback<ProductList_APIMsg>() {
            @Override
            public void onResponse(Call<ProductList_APIMsg> call, Response<ProductList_APIMsg> response) {
                System.out.println("---------------onResponse-----------TABELSvm-------------");
                if(response.isSuccessful()){


                    Log.w("pintu",new Gson().toJson(response.body()));
                    List<ProductList_Data> list = response.body().getData();

        //            String content = list.get(2).getName();
                    System.out.println("---------content-----------------");
  //                  System.out.println(content);
                    Log.d("saurabh", "is Successful");
                    productlist.setValue(list);
                    System.out.println(productlist.getClass());
                    System.out.println(list.size());
                    System.out.println(response.body());

                    System.out.println(list.size());
                    System.out.println("--------------------------TABELSvm------------------SUccess----------------------------------------");
                  //  Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        System.out.println("-----DM----------------------------------------");
//                        Toast.makeText(
//                                context,
//                                jObjError.getString("user_msg"),
//                                Toast.LENGTH_SHORT).show();
                        Tables.progressBar.setVisibility(View.GONE);
                    } catch (Exception e) {

                 //      Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductList_APIMsg> call, Throwable t) {

//                Toast.makeText(context,"Check Internet Connection",Toast.LENGTH_SHORT).show();
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff------UnSucessful------------------");
            }

        });

    }


}
