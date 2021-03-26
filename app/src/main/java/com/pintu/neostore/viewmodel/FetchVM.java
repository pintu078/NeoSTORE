package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.model.fetch.Data;
import com.pintu.neostore.model.fetch.FetchAPIMsg;
import com.pintu.neostore.model.order.Order_List.Datum;
import com.pintu.neostore.model.order.Order_List.order_list_APIMsg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchVM extends ViewModel {

    private Context context;
    private MutableLiveData<Data> fetch_list;

    public FetchVM(Context context) {
        this.context = context;
    }

    public MutableLiveData<Data> getFetchListObserver() {
        if (fetch_list == null) {
            fetch_list = new MutableLiveData<>();
        }
        return fetch_list;
    }

    public void loadFetchList(String token) {

        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<FetchAPIMsg> call = apiService.fetchPost(token);
        Log.d("saurabh", "1");
        call.enqueue(new Callback<FetchAPIMsg>() {
            @Override
            public void onResponse(Call<FetchAPIMsg> call, Response<FetchAPIMsg> response) {
                if (response.isSuccessful()) {

                    fetch_list.postValue(response.body().getData());
                    //  Log.d("saurabh", list.get(0).getCost().toString());
                    //  order_list.postValue(response.body());
                    //  Toast.makeText(context, response.body().getUserMsg(), Toast.LENGTH_SHORT).show();

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
            public void onFailure(Call<FetchAPIMsg> call, Throwable t) {
                Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff---dd---UnSucessful------------------");

            }
        });
    }
}


