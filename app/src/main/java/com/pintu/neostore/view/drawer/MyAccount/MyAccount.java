package com.pintu.neostore.view.drawer.MyAccount;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pintu.neostore.R;
import com.pintu.neostore.view.login.Login;
import com.squareup.picasso.Picasso;

public class MyAccount extends AppCompatActivity {

    EditText FirstName, LastName, Email, Phone, DOB;
    Button EditProfile, ResetProfile;
    TextView tv_Img;
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
        tv_Img = (TextView)findViewById(R.id.tv_img);


        SharedPreferences sp = getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
        FirstName.setText(sp.getString("FName", ""));
        LastName.setText(sp.getString("LName", ""));
        Email.setText(sp.getString("Email", ""));
        Phone.setText(sp.getString("Phone", ""));
        DOB.setText(sp.getString("DOB", "00-00-0000"));
        String image = sp.getString("Profile", "");
        Log.d("saurabh","image Myaccount 1 "+image);

//        Log.d("saurabh ","String image "+image);
//        String imageDataBytes = image.substring(image.lastIndexOf('/')+1);
//        Log.d("saurabh ","String image data "+imageDataBytes);
//        InputStream stream = new ByteArrayInputStream(Base64.decode("data:image/jpg;base64,"+imageDataBytes.getBytes(), Base64.DEFAULT));
//        Bitmap bitmap = BitmapFactory.decodeStream(stream);
//        imgProfile.setImageBitmap(bitmap);
//

        if (!image.equals("null")) {
            Picasso.with(getApplicationContext())
                    .load(image)
                    .fit()
                    .into(imgProfile);
            tv_Img.setVisibility(View.INVISIBLE);
        }else{
            tv_Img.setVisibility(View.VISIBLE);
            String fini=(sp.getString("FName","")).toUpperCase();
            String lini=(sp.getString("LName","")).toUpperCase();
            String initials=fini.substring(0,1)+lini.substring(0,1);

            tv_Img.setText(initials);
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
                Intent intent = new Intent(MyAccount.this, com.pintu.neostore.view.drawer.MyAccount.EditProfile.class);
                startActivityForResult(intent, 2);

            }
        });

        ResetProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccount.this, com.pintu.neostore.view.drawer.MyAccount.ResetPass.class);
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
            Log.d("saurabh","image Myaccount return "+image);

            if (!image.equals("null")) {
                Picasso.with(getApplicationContext())
                        .load(image)
                        .fit()
                        .into(imgProfile);
                tv_Img.setVisibility(View.INVISIBLE);
            }else{
                tv_Img.setVisibility(View.VISIBLE);
                String fini=(sp.getString("FName","")).toUpperCase();
                String lini=(sp.getString("LName","")).toUpperCase();
                String initials=fini.substring(0,1)+lini.substring(0,1);

                tv_Img.setText(initials);
            }
        }
    }

}