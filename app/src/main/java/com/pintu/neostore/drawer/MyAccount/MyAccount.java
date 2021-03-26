package com.pintu.neostore.drawer.MyAccount;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.pintu.neostore.R;
import com.pintu.neostore.login.Login;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static java.lang.System.exit;

public class MyAccount extends AppCompatActivity {

    EditText FirstName, LastName, Email, Phone, DOB;
    Button EditProfile, ResetProfile;
    ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        FirstName = (EditText) findViewById(R.id.ed_first_name);
        LastName = (EditText) findViewById(R.id.ed_last_name);
        Email = (EditText) findViewById(R.id.ed_email);
        Phone = (EditText) findViewById(R.id.ed_phone);
        DOB = (EditText) findViewById(R.id.ed_dob);
        EditProfile = (Button) findViewById(R.id.btn_edit_profile);
        ResetProfile = (Button) findViewById(R.id.btn_reset_passwrd);
        imgProfile = (ImageView) findViewById(R.id.profile_img);


        SharedPreferences sp = getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
        FirstName.setText(sp.getString("FName", ""));
        LastName.setText(sp.getString("LName", ""));
        Email.setText(sp.getString("Email", ""));
        Phone.setText(sp.getString("Phone", ""));
        DOB.setText(sp.getString("DOB", "00-00-0000"));
        String image = sp.getString("Profile", "");

//        Log.d("saurabh ","String image "+image);
//        String imageDataBytes = image.substring(image.lastIndexOf('/')+1);
//        Log.d("saurabh ","String image data "+imageDataBytes);
//        InputStream stream = new ByteArrayInputStream(Base64.decode("data:image/jpg;base64,"+imageDataBytes.getBytes(), Base64.DEFAULT));
//        Bitmap bitmap = BitmapFactory.decodeStream(stream);
//        imgProfile.setImageBitmap(bitmap);
//
        if (!image.equals("")) {
            Picasso.with(getApplicationContext())
                    .load(image)
                    .fit()
                    .into(imgProfile);
        }


        ImageButton imgbtn = findViewById(R.id.imgbtn);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAccount.super.onBackPressed();
            }
        });

        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccount.this, com.pintu.neostore.drawer.MyAccount.EditProfile.class);
                startActivityForResult(intent, 2);

            }
        });

        ResetProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccount.this, com.pintu.neostore.drawer.MyAccount.ResetPass.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            SharedPreferences sp = getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
            FirstName.setText(sp.getString("FName", ""));
            LastName.setText(sp.getString("LName", ""));
            Email.setText(sp.getString("Email", ""));
            Phone.setText(sp.getString("Phone", ""));
            DOB.setText(sp.getString("DOB", "00-00-0000"));
            String image = sp.getString("Profile", "");
            if (!image.equals("")) {
                Picasso.with(getApplicationContext())
                        .load(image)
                        .fit()
                        .into(imgProfile);
            }
        }
    }

}