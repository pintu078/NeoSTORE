package com.pintu.neostore.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.R;
import com.pintu.neostore.register.Register;
import com.pintu.neostore.viewmodel.LoginVM;
import com.pintu.neostore.viewmodel.LoginVMFactory;


public class Login extends AppCompatActivity {

    public static LoginVM loginVM;

    public static final String PREFS_NAME = "MyLoginPrefsFile";
    EditText UserName, Password;
    TextView Forgot;
    public static Button Login;
    FloatingActionButton Fab;
    public static ProgressBar progressBar;

    String UserNames, Passwords;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_main);


        UserName = (EditText) findViewById(R.id.ed_username);
        Password = (EditText) findViewById(R.id.ed_password);
        Login = (Button) findViewById(R.id.btn_login);
        Fab = (FloatingActionButton) findViewById(R.id.fab);
        Forgot = (TextView) findViewById(R.id.tv_forgot_pas_link);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);


        loginVM = new ViewModelProvider(this, new LoginVMFactory(this)).get(LoginVM.class);
        loginVM.getLoginListObserver().observe(this, new Observer<APIMsg>() {
            @Override
            public void onChanged(APIMsg apiMsg) {
                System.out.println("---------1-------");
                if (apiMsg != null) {
                    System.out.println("---------2-------");
                    String FName = apiMsg.getData().getFirstName();
                    String LName = apiMsg.getData().getLastName();
                    String UName = apiMsg.getData().getUsername();
                    String Email = apiMsg.getData().getEmail();
                    String Gender = apiMsg.getData().getGender();
                    String Phone = apiMsg.getData().getPhoneNo();
                    String Token = apiMsg.getData().getAccessToken();
                    String dob = String.valueOf(apiMsg.getData().getDob());
                    String profile = String.valueOf(apiMsg.getData().getProfilePic());

                    SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("FName", FName.toUpperCase());
                    myEdit.putString("LName", LName.toUpperCase());
                    myEdit.putString("UName", UName);
                    myEdit.putString("Email", Email);
                    myEdit.putString("Gender", Gender);
                    myEdit.putString("Phone", Phone);
                    myEdit.putString("Token", Token);
                    myEdit.putString("DOB", dob);
//
//                    if (profile.equals(null)) {
//                        myEdit.putString("Profile", profile);
//                        Log.d("saurabh", "profile" + profile);
//                    } else {
//                        myEdit.putString("Profile", FName.substring(0, 1) + LName.substring(0, 1));
//                        Log.d("saurabh", "null " + FName.substring(0, 1) + LName.substring(0, 1));
//                    }
                    myEdit.putString("Profile", profile);
                    Log.d("saurabh", "profile" + profile);

                    myEdit.commit();
                    System.out.println("---------" + FName + LName + UName + Email);

                    Intent intent = new Intent(com.pintu.neostore.login.Login.this, com.pintu.neostore.home.Home.class);
                    startActivity(intent);
                    finish();
                } else {
                    System.out.println("---------3-------");

                }
            }

        });
        System.out.println("---------4-------");


        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.pintu.neostore.login.Login.this, com.pintu.neostore.forgot.Forgot.class);
                // startActivity(intent);
                startActivityForResult(intent, 2);

            }
        });


        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.pintu.neostore.login.Login.this, com.pintu.neostore.register.Register.class);
                startActivityForResult(intent, 2);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserNames = UserName.getText().toString().trim();
                Passwords = Password.getText().toString().trim();

                if (UserNames.length() == 0 || Passwords.length() == 0 || !UserNames.matches(emailPattern)||(Passwords.length()>0 && Passwords.length()<6)) {


                    if (UserNames.length() == 0) {
                        UserName.requestFocus();
                        UserName.setError("FIELD CANNOT BE EMPTY");
                    } else if (UserNames.length() != 0 && !UserNames.matches(emailPattern)) {
                        UserName.requestFocus();
                        UserName.setError("ENTER VALID USERNAME");
                    }
                    if (Passwords.length() == 0) {
                        Password.requestFocus();
                        Password.setError("FIELD CANNOT BE EMPTY");
                    }
                    if(Passwords.length()>0 && Passwords.length()<6){
                        Password.requestFocus();
                        Password.setError("Password Greater than 6 Digit");
                    }
                } else {

                    loginVM.makeApiCall(UserNames, Passwords);
                    Login.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);

                }
            }
        });
    }
}