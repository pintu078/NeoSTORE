package com.pintu.neostore.view.drawer.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pintu.neostore.R;
import com.pintu.neostore.adapter.OrderDetailsAdapter;
import com.pintu.neostore.view.login.Login;
import com.pintu.neostore.model.order.order_details.Data;
import com.pintu.neostore.model.order.order_details.OrderDetail;
import com.pintu.neostore.viewmodel.OrderDetailsVM;
import com.pintu.neostore.viewmodel.OrderDetailsVMFactory;

import java.util.List;

public class OrderDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderDetailsVM orderDetailsVM;
    OrderDetailsAdapter orderDetailsAdapter;
    SharedPreferences sp;
    String token,ids;

    public static ProgressBar progressBar;
    ImageButton imgBtn;
    TextView toolText,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        imgBtn = (ImageButton) findViewById(R.id.imgbtn);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        imgBtn = (ImageButton)findViewById(R.id.back_btn);
        toolText = (TextView)findViewById(R.id.tooltext);
        total = (TextView)findViewById(R.id.total);

        sp = getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
        token = sp.getString("Token", "");

        Intent intent = getIntent();
        ids = intent.getStringExtra(("order id"));

        toolText.setText("ORDER ID : "+ids);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        orderDetailsVM = ViewModelProviders.of(this, new OrderDetailsVMFactory(this)).get(OrderDetailsVM.class);
        orderDetailsVM.getOrderDetailObserver().observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {

                List<OrderDetail> list = data.getOrderDetails();

                orderDetailsAdapter = new OrderDetailsAdapter(OrderDetails.this, list);
                orderDetailsAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(orderDetailsAdapter);

                total.setText("₹ " + data.getCost());
            }
        });


        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDetails.super.onBackPressed();
            }
        });


        orderDetailsVM.loadOrderDetails(token, ids);
    }
}