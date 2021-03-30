package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.view.drawer.order.OrderList;
import com.pintu.neostore.model.order.Order_List.Datum;
import com.pintu.neostore.model.order.Order_List.order_list_APIMsg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListVM extends ViewModel {

    private Context context;
    private MutableLiveData<List<Datum>> order_list;

    public OrderListVM(Context context) {
        this.context = context;
    }

    public MutableLiveData<List<Datum>> getOrderListObserver() {
        if (order_list == null) {
            order_list = new MutableLiveData<>();
        }
        return order_list;
    }

    public void loadOrderList(String token) {

        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<order_list_APIMsg> call = apiService.orderListPost(token);
        Log.d("saurabh", "1");
        call.enqueue(new Callback<order_list_APIMsg>() {
            @Override
            public void onResponse(Call<order_list_APIMsg> call, Response<order_list_APIMsg> response) {
                if (response.isSuccessful()) {
                    List<Datum> list = response.body().getData();
                    order_list.setValue(list);
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
            public void onFailure(Call<order_list_APIMsg> call, Throwable t) {
                Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff---dd---UnSucessful------------------");
                visible();

            }
        });
    }
    public void visible(){
        OrderList.progressBar.setVisibility(View.GONE);
    }
}


