package com.pintu.neostore.drawer.mycart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pintu.neostore.R;
import com.pintu.neostore.adapter.MyCartAdapter;
import com.pintu.neostore.adapter.ProductDetailedAdapter;
import com.pintu.neostore.drawer.tabel.ProductDetailed;
import com.pintu.neostore.drawer.tabel.Tables;
import com.pintu.neostore.login.Login;
import com.pintu.neostore.model.Cart.listcart_items.ListCartItem_APIMsg;
import com.pintu.neostore.model.Cart.listcart_items.ListCartItem_Data;
import com.pintu.neostore.model.Cart.listcart_items.ListCartItem_Image;
import com.pintu.neostore.model.ProductDetailed_Model.ProductDetailed_APIMsg;
import com.pintu.neostore.model.ProductDetailed_Model.ProductImage;
import com.pintu.neostore.viewmodel.MyCartVM;
import com.pintu.neostore.viewmodel.MyCartVMFactory;
import com.pintu.neostore.viewmodel.ProductDetailedVM;
import com.pintu.neostore.viewmodel.ProductDetailedVMFactory;

import java.util.List;

public class MyCart extends AppCompatActivity {

    RecyclerView recyclerView;
    MyCartAdapter myCartAdapter;
    SharedPreferences sp;

    MyCartVM myCartVM;
    TextView total;
    Button order;
    ImageButton delete,back_btn;

    String token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        total = (TextView)findViewById(R.id.total);
        back_btn = (ImageButton)findViewById(R.id.back_btn);
        order = (Button)findViewById(R.id.btn_order);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);




        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCart.super.onBackPressed();
            }
        });

        myCartVM = new ViewModelProvider(this, new MyCartVMFactory(this)).get(MyCartVM.class);
        myCartVM.getMyCartObserver().observe(this, new Observer<ListCartItem_APIMsg>() {
            @Override
            public void onChanged(ListCartItem_APIMsg listCartItem_apiMsg) {

                if (listCartItem_apiMsg != null) {
                    Log.d("saurah", "MyCart Success  " + listCartItem_apiMsg.getData().get(0).getProduct().getProductImages());

                    List<ListCartItem_Data> list = listCartItem_apiMsg.getData();

                    myCartAdapter = new MyCartAdapter(MyCart.this,list);
                    myCartAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(myCartAdapter);

                    total.setText("₹ "+listCartItem_apiMsg.getTotal()+"0.0");
                    Log.d("saurah", "MyCart Success  " + listCartItem_apiMsg.getTotal());
                } else {
                    System.out.println("---------3-------");

                }
            }
        });
        sp = getSharedPreferences(Login.PREFS_NAME,MODE_PRIVATE);
        token = sp.getString("Token","");
        myCartVM.loadMyCart(token);

    }
}