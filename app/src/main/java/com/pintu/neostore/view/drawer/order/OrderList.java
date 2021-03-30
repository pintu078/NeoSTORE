package com.pintu.neostore.view.drawer.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.pintu.neostore.R;
import com.pintu.neostore.adapter.OrderListAdapter;
import com.pintu.neostore.view.login.Login;
import com.pintu.neostore.model.order.Order_List.Datum;
import com.pintu.neostore.viewmodel.OrderListVM;
import com.pintu.neostore.viewmodel.OrderListVMFactory;


import java.util.List;

public class OrderList extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderListAdapter orderListAdapter;
    OrderListVM orderListVM;
    SharedPreferences sp;
    String token;

    public static ProgressBar progressBar;
    ImageButton imgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        imgBtn = (ImageButton)findViewById(R.id.imgbtn);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        orderListVM = ViewModelProviders.of(this,new OrderListVMFactory(this)).get(OrderListVM.class);
        orderListVM.getOrderListObserver().observe(this, new Observer<List<Datum>>() {
            @Override
            public void onChanged(List<Datum> data) {

                orderListAdapter = new OrderListAdapter(OrderList.this, data);
                orderListAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(orderListAdapter);

            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderList.super.onBackPressed();
            }
        });

        sp = getSharedPreferences(Login.PREFS_NAME,MODE_PRIVATE);
        token = sp.getString("Token","");

        orderListVM.loadOrderList(token);
        progressBar.setVisibility(View.VISIBLE);

    }
}