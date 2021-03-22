package com.pintu.neostore.drawer.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.pintu.neostore.R;
import com.pintu.neostore.drawer.mycart.MyCart;
import com.pintu.neostore.login.Login;
import com.pintu.neostore.model.Cart.Cart_APIMSg;
import com.pintu.neostore.model.order.OrderAPIMsg;
import com.pintu.neostore.viewmodel.AddressVM;
import com.pintu.neostore.viewmodel.AddressVMFactory;
import com.pintu.neostore.viewmodel.DeleteCartVM;
import com.pintu.neostore.viewmodel.DeleteCartVMFactory;
import com.pintu.neostore.viewmodel.EditCartVMFactory;

public class Address extends AppCompatActivity {

    EditText address,landmark,city,state,zip,country;
    String Address,landmarks,citys,states,zips,countrys,token;
    Button order;
    ImageButton back_btn;

    AddressVM addressVM;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        address = (EditText)findViewById(R.id.ed_address);
        landmark = (EditText)findViewById(R.id.ed_landmark);
        city =(EditText)findViewById(R.id.ed_city);
        state = (EditText)findViewById(R.id.ed_state);
        zip = (EditText)findViewById(R.id.ed_zip);
        country =(EditText)findViewById(R.id.ed_country);
        order = (Button)findViewById(R.id.btn_place_order);
        back_btn =(ImageButton)findViewById(R.id.imgbtn);

        Address = address.getText().toString().trim();
        landmarks = landmark.getText().toString().trim();
        citys = city.getText().toString().trim();
        states = state.getText().toString().trim();
        zips = zip.getText().toString().trim();
        countrys = country.getText().toString().trim();

        sp = getSharedPreferences(Login.PREFS_NAME,MODE_PRIVATE);
        token = sp.getString("Token","");


        addressVM = new ViewModelProvider(this,new AddressVMFactory(this)).get(AddressVM.class);
        addressVM.getAddressObserver().observe(this, new Observer<OrderAPIMsg>() {

            @Override
            public void onChanged(OrderAPIMsg orderAPIMsg) {

                if(orderAPIMsg!=null){
                    Log.d("saurabh","order place "+"success");
                    MyCart.myCartVM.loadMyCart(token);
                }

            }

        });

        if(address.length()==0 || landmark.length()==0 || city.length()==0 || zip.length()==0 || country.length()==0){

            if(Address.length()==0){
                address.requestFocus();
               address.setError("FIELD CANNOT BE EMPTY");
            }
            if(landmarks.length()==0){
                landmark.requestFocus();
                landmark.setError("FIELD CANNOT BE EMPTY");
            }
            if(citys.length()==0){
                city.requestFocus();
                city.setError("FIELD CANNOT BE EMPTY");
            }
            if(states.length()==0){
                state.requestFocus();
                state.setError("FIELD CANNOT BE EMPTY");
            }
            if(zips.length()==0){
                zip.requestFocus();
                zip.setError("FIELD CANNOT BE EMPTY");
            }
            if(countrys.length()==0){
                country.requestFocus();
                country.setError("FIELD CANNOT BE EMPTY");
            }

        }else{

        }

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("saurabh","address "+address.getText().toString());
                addressVM.loadAddress(token,address.getText().toString());
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Address.super.onBackPressed();
            }
        });
    }
}