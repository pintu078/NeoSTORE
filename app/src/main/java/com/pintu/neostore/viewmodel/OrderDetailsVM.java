package com.pintu.neostore.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pintu.neostore.drawer.order.OrderDetails;
import com.pintu.neostore.model.order.order_details.Data;
import com.pintu.neostore.model.order.order_details.OrderDetailsAPIMsg;
import com.pintu.neostore.network.APIService;
import com.pintu.neostore.network.RetroInstance;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsVM extends ViewModel {

    private Context context;
    private MutableLiveData<Data> order_details;

    public OrderDetailsVM(Context context) {
        this.context = context;
    }

    public MutableLiveData<Data> getOrderDetailObserver() {
        if (order_details == null) {
            order_details = new MutableLiveData<>();
        }
        return order_details;
    }

    public void loadOrderDetails(String token,String id) {

        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<OrderDetailsAPIMsg> call = apiService.orderDetailsPost(token,id);
        Log.d("saurabh", "token "+token+"  "+"id    "+id);
        call.enqueue(new Callback<OrderDetailsAPIMsg>() {
            @Override
            public void onResponse(Call<OrderDetailsAPIMsg> call, Response<OrderDetailsAPIMsg> response) {
                if (response.isSuccessful()) {
                    order_details.postValue(response.body().getData());

                    //  order_list.postValue(response.body());
                   //   Toast.makeText(context, response.body().getUserMsg(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<OrderDetailsAPIMsg> call, Throwable t) {
                Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff---dd---UnSucessful------------------");
                visible();
            }
        });
    }
    public void visible(){
        OrderDetails.progressBar.setVisibility(View.GONE);
    }
}
