package com.pintu.neostore.drawer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.pintu.neostore.R;
import com.pintu.neostore.adapter.ProductListAdapter;
import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.model.ProductList_APIMsg;
import com.pintu.neostore.model.ProductList_Data;
import com.pintu.neostore.register.Register;
import com.pintu.neostore.viewmodel.TabelsVM;

import java.util.List;

public class Tables extends AppCompatActivity {

    RecyclerView recyclerView;
    TabelsVM tabelsVM;
    ProductListAdapter productListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        ImageButton imgbtn = findViewById(R.id.imgbtn);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tables.super.onBackPressed();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        tabelsVM = ViewModelProviders.of(this).get(TabelsVM.class);
        tabelsVM.getTableListObserver().observe(this, new Observer<List<ProductList_Data>>() {

            @Override
            public void onChanged(@Nullable List<ProductList_Data> productList_apiMsgs) {
                System.out.println("-------onchanged");
                System.out.println(productList_apiMsgs.size());
                productListAdapter = new ProductListAdapter(Tables.this,productList_apiMsgs);
                recyclerView.setAdapter(productListAdapter);

            }
        });
        System.out.println("---------Tabels");
       // tabelsVM.loadProductLists();


    }
}