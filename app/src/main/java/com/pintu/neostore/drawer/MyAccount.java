package com.pintu.neostore.drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.pintu.neostore.R;
import com.pintu.neostore.register.Register;

public class MyAccount extends AppCompatActivity {

    EditText FirstName,LastName,Email,Phone,DOB;
    Button EditProfile,ResetProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        FirstName = (EditText)findViewById(R.id.ed_first_name);
        LastName = (EditText)findViewById(R.id.ed_last_name);
        Email = (EditText)findViewById(R.id.ed_email);
        Phone = (EditText)findViewById(R.id.ed_phone);
        DOB = (EditText)findViewById(R.id.ed_dob);
        EditProfile = (Button)findViewById(R.id.btn_edit_profile);
        ResetProfile = (Button)findViewById(R.id.btn_reset_passwrd);

        SharedPreferences sp = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        FirstName.setText(sp.getString("FName",""));
        LastName.setText(sp.getString("LName",""));
        Email.setText(sp.getString("Email",""));
        Phone.setText(sp.getString("Phone",""));
        DOB.setText("00-00-0000");

        ImageButton imgbtn = findViewById(R.id.imgbtn);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAccount.super.onBackPressed();
            }
        });

    }
}