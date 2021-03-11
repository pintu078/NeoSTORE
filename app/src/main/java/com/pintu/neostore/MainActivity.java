package com.pintu.neostore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.pintu.neostore.home.Home;
import com.pintu.neostore.login.Login;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    TextView Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Hooks

        Name =(TextView)findViewById(R.id.name);

        SharedPreferences sp = getSharedPreferences(Login.PREFS_NAME,MODE_PRIVATE);
//        boolean hasLoggedIn = sp.getBoolean("hasLoggedIn", false);
        String Name = sp.getString("FName","");

        if(Name.equals("")) {
            Log.d("saurabh","if");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("saurabh","if");
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    Log.d("saurabh","if");
                    finish();
                }
            }, SPLASH_SCREEN);
        }else {
            Log.d("saurabh","else");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("saurabh","else");
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                    Log.d("saurabh","else");
                    finish();
                }
            }, SPLASH_SCREEN);
        }
    }
}