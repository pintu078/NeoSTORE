package com.pintu.neostore.drawer.MyAccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.pintu.neostore.R;
import com.pintu.neostore.login.Login;
import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.viewmodel.EditProfileVM;
import com.pintu.neostore.viewmodel.EditVMFactory;
import com.pintu.neostore.viewmodel.ResetVM;
import com.pintu.neostore.viewmodel.ResetVMFactory;

public class ResetPass extends AppCompatActivity {

    EditText current, newP, confirm;
    Button reset;
    ImageButton imgbtn;
    String Currents, News, Confirms,token;
    ResetVM resetVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        current = (EditText) findViewById(R.id.ed_curr_pass);
        newP = (EditText) findViewById(R.id.ed_new_pass);
        confirm = (EditText) findViewById(R.id.ed_con_pass);
        reset = (Button) findViewById(R.id.btn_reset);
        imgbtn = (ImageButton) findViewById(R.id.imgbtn);


        SharedPreferences sp = getSharedPreferences(Login.PREFS_NAME,MODE_PRIVATE);
        token = sp.getString("Token","");
        Log.d("saurabh",token+"  reset");

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPass.super.onBackPressed();
            }
        });

        resetVM = new ViewModelProvider(this, new ResetVMFactory(this)).get(ResetVM.class);
        resetVM.getResetListObserver().observe(this, new Observer<APIMsg>() {
            @Override
            public void onChanged(APIMsg apiMsg) {

                Log.d("saurabh", "OnChanged");
                if (apiMsg != null) {
                    Log.d("saurabh", "Success");
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Currents = current.getText().toString().trim();
                News = newP.getText().toString().trim();
                Confirms = confirm.getText().toString().trim();


                if (Currents.length() == 0 || News.length() == 0 || Confirms.length() == 0 || Currents.length() < 6 || News.length() < 6 || Confirms.length() < 6 || !Confirms.equals(News)) {
                    if (Currents.length() == 0) {
                        current.requestFocus();
                        current.setError("FIELD CANNOT BE EMPTY");
                    } else if (Currents.length() < 6) {
                        current.requestFocus();
                        current.setError("PLEASE ENTER 6 DIGIT PASSSWORD");
                    }
                    if (News.length() == 0) {
                        newP.requestFocus();
                        newP.setError("FIELD CANNOT BE EMPTY");
                    } else if (News.length() < 6) {
                        newP.requestFocus();
                        newP.setError("PLEASE ENTER 6 DIGIT PASSSWORD");
                    }
                    if (Confirms.length() == 0) {
                        confirm.requestFocus();
                        confirm.setError("FIELD CANNOT BE EMPTY");
                    } else if (Confirms.length() < 6) {
                        confirm.requestFocus();
                        confirm.setError("PLEASE ENTER 6 DIGIT PASSSWORD");
                    } else if (!Confirms.equals(News)) {
                        confirm.requestFocus();
                        confirm.setError("PASSWORD MISMATCH");
                    }
                    System.out.println("-------------------------------------Hiii---------------------------------------");
                } else {
                    System.out.println("-------------------------------------Data----------------------------------------");
                    System.out.println(token+"  "+Currents+"  "+News+"  "+"  "+Confirms);
                    resetVM.loadResetLists(token,Currents,News,Confirms);
                }
            }
        });
    }
}