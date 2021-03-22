package com.pintu.neostore.drawer.mycart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pintu.neostore.R;
import com.pintu.neostore.adapter.MyCartAdapter;
import com.pintu.neostore.drawer.order.Address;
import com.pintu.neostore.login.Login;
import com.pintu.neostore.model.Cart.Cart_APIMSg;
import com.pintu.neostore.model.Cart.listcart_items.ListCartItem_APIMsg;
import com.pintu.neostore.model.Cart.listcart_items.ListCartItem_Data;
import com.pintu.neostore.viewmodel.DeleteCartVM;
import com.pintu.neostore.viewmodel.DeleteCartVMFactory;
import com.pintu.neostore.viewmodel.EditCartVM;
import com.pintu.neostore.viewmodel.EditCartVMFactory;
import com.pintu.neostore.viewmodel.MyCartVM;
import com.pintu.neostore.viewmodel.MyCartVMFactory;

import java.util.List;

public class MyCart extends AppCompatActivity {

    RecyclerView recyclerView;
    MyCartAdapter myCartAdapter;
    SharedPreferences sp;

    public static MyCartVM myCartVM;
    public static DeleteCartVM deleteCartVM;
    public static EditCartVM editCartVM;
    TextView total, totalTxt;
    Button order;
    LinearLayout empty_Layout;
    ImageButton delete, back_btn;
    Boolean flag = true;
    String token;
    List<ListCartItem_Data> list;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        total = (TextView) findViewById(R.id.total);
        totalTxt = (TextView) findViewById(R.id.totaltx);
        empty_Layout = (LinearLayout) findViewById(R.id.empty_layout);
        back_btn = (ImageButton) findViewById(R.id.back_btn);
        order = (Button) findViewById(R.id.btn_order);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCart.super.onBackPressed();
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCart.this, Address.class);
                startActivity(intent);
            }
        });

        myCartVM = new ViewModelProvider(this, new MyCartVMFactory(this)).get(MyCartVM.class);
        myCartVM.getMyCartObserver().observe(this, new Observer<ListCartItem_APIMsg>() {
            @Override
            public void onChanged(ListCartItem_APIMsg listCartItem_apiMsg) {
                list = listCartItem_apiMsg.getData();
                if (listCartItem_apiMsg != null) {
                    if (list != null) {
                        empty_Layout.setVisibility(View.GONE);
                        Log.d("saurah", "MyCart Success  " + listCartItem_apiMsg.getData().get(0).getProduct().getProductImages());

                        myCartAdapter = new MyCartAdapter(MyCart.this, list);
                        recyclerView.setAdapter(myCartAdapter);

                        total.setText("₹ " + listCartItem_apiMsg.getTotal() + "0.0");
                        Log.d("saurah", "MyCart Success  " + listCartItem_apiMsg.getTotal());
                    } else {
                        myCartAdapter = new MyCartAdapter(MyCart.this, list);
                        recyclerView.setAdapter(myCartAdapter);
                        setGone();
                    }
                } else {
                    System.out.println("---------3-------");

                }
            }
        });

        deleteCartVM = new ViewModelProvider(this, new DeleteCartVMFactory(this)).get(DeleteCartVM.class);
        deleteCartVM.getDeleteCartObserver().observe(this, new Observer<Cart_APIMSg>() {

            @Override
            public void onChanged(Cart_APIMSg deleteCart_apiMsg) {
                if (deleteCart_apiMsg != null) {
                    if (deleteCart_apiMsg.getTotalCarts() != 0) {
                        myCartVM.loadMyCart(token);
                        flag = false;
                    } else {
                        myCartVM.loadMyCart(token);
                        setGone();
                    }
                }
            }
        });

        editCartVM = new ViewModelProvider(this, new EditCartVMFactory(this)).get(EditCartVM.class);
        editCartVM.getEditCartObserver().observe(this, new Observer<Cart_APIMSg>() {
            @Override
            public void onChanged(Cart_APIMSg editCart_apimSg) {
                if (editCart_apimSg != null) {

                    myCartVM.loadMyCart(token);
                    flag = false;

                }
            }
        });

        sp = getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
        token = sp.getString("Token", "");
        if (flag == true) {
            myCartVM.loadMyCart(token);
        }
    }

    public void setGone() {
        recyclerView.setVisibility(View.GONE);
        totalTxt.setVisibility(View.GONE);
        total.setVisibility(View.GONE);
        order.setVisibility(View.GONE);
        empty_Layout.setVisibility(View.VISIBLE);
    }

}