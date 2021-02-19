package com.pintu.neostore.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.pintu.neostore.R;

import java.util.ArrayList;

public class Display extends AppCompatActivity {
    TextView receiver_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        receiver_msg = (TextView) findViewById(R.id.received_value_idd);

//        MyData myData = AppConstant.mydatas.get(0);
//        receiver_msg.setText("First Name         " +myData.FnameD +"\n"+"Last Name         "+myData.LnameD+"\n"+"Email                  " +myData.EmailD+"\n"+"Password             " +myData.PasswordD+"\n"+"Con Password     " + myData.Con_PasswordD+"\n"+"Gender                 " +myData.GenderD+"\n"+"Phone                  " +myData.PhoneD);
//        Intent intent  = getIntent();
//        String str = intent.getStringExtra("message_key");
//        receiver_msg.setText(str);


    }

}
