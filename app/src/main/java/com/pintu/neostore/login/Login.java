package com.pintu.neostore.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pintu.neostore.forgot.Forgot;
import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.R;
import com.pintu.neostore.model.LoginModel;
import com.pintu.neostore.register.Register;
import com.pintu.neostore.viewmodel.LoginVM;
import com.pintu.neostore.viewmodel.LoginVMFactory;


public class Login extends AppCompatActivity {

    private LoginVM loginVM;
  //  LoginMainBinding binding;

    public static final String PREFS_NAME = "MyLoginPrefsFile";
    EditText UserName,Password;
    TextView Forgot;
    Button Login;
    FloatingActionButton Fab;

    String UserNames,Passwords;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_main);
// getSupportActionBar().hide();


        UserName = (EditText)findViewById(R.id.ed_username);
        Password = (EditText)findViewById(R.id.ed_password);
        Login =(Button)findViewById(R.id.btn_login);
        Fab = (FloatingActionButton)findViewById(R.id.fab);
        Forgot = (TextView)findViewById(R.id.tv_forgot_pas_link);


        
        loginVM = new ViewModelProvider(this,new LoginVMFactory(this)).get(LoginVM.class);
//        binding = DataBindingUtil.setContentView(com.pintu.neostore.login.Login.this,R.layout.login_main);
//        binding.setLoginViewModel(loginVM);
        loginVM.getLoginListObserver().observe(this, new Observer<APIMsg>() {
            @Override
            public void onChanged(APIMsg apiMsg) {
                System.out.println("---------1-------");
                if(apiMsg != null){
                    System.out.println("---------2-------");
                    String F = apiMsg.getData().getFirstName();
                    String L = apiMsg.getData().getLastName();
                    String U = apiMsg.getData().getUsername();
                    String E = apiMsg.getData().getEmail();
                    String G = apiMsg.getData().getGender();
                    String P = apiMsg.getData().getPhoneNo();

                    SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("FName", F.toUpperCase());
                    myEdit.putString("LName", L.toUpperCase());
                    myEdit.putString("UName", U);
                    myEdit.putString("Email", E);
                    myEdit.putString("Gender", G);
                    myEdit.putString("Phone", P);
                    myEdit.putBoolean("hasLoggedIn",true);
                    myEdit.commit();

                    Intent intent = new Intent(com.pintu.neostore.login.Login.this, com.pintu.neostore.home.Home.class);
                    startActivity(intent);
                }else{
                    System.out.println("---------3-------");

                }
            }

        });
        System.out.println("---------4-------");


        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.pintu.neostore.login.Login.this, com.pintu.neostore.forgot.Forgot.class);
                startActivity(intent);
            }
        });


        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserNames = UserName.getText().toString().trim();
                Passwords = Password.getText().toString().trim();

                if(UserNames.length()==0 || Passwords.length()==0 || !UserNames.matches(emailPattern)){


                    if(UserNames.length()==0){
                        UserName.requestFocus();
                        UserName.setError("FIELD CANNOT BE EMPTY");
                    }else if(UserNames.length()!=0 && !UserNames.matches(emailPattern)){
                        UserName.requestFocus();
                        UserName.setError("ENTER VALID USERNAME");
                    }
                    if(Passwords.length()==0){
                        Password.requestFocus();
                        Password.setError("FIELD CANNOT BE EMPTY");
                    }
                }else{

                    loginVM.makeApiCall(UserNames,Passwords);

//                    Intent intent = new Intent(com.pintu.neostore.login.Login.this, com.pintu.neostore.home.Home.class);
//                    startActivity(intent);

                }
            }
        });
    }
}