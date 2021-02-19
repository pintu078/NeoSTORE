package com.pintu.neostore.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pintu.neostore.R;
import com.pintu.neostore.register.Register;
import com.pintu.neostore.register.RegisterAPI;
import com.pintu.neostore.register.RegisterModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private LoginAPI loginAPI;

    EditText UserName,Password;
    Button Login;
    FloatingActionButton Fab;

    String UserNames,Passwords;
    String namePattern = "[a-zA-Z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        getSupportActionBar().hide();

        UserName = (EditText)findViewById(R.id.ed_username);
        Password = (EditText)findViewById(R.id.ed_password);
        Login =(Button)findViewById(R.id.btn_login);
        Fab = (FloatingActionButton)findViewById(R.id.fab);

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

                if(UserNames.length()==0 || Passwords.length()==0){


                    if(UserNames.length()==0){
                       UserName.requestFocus();
                        UserName.setError("FIELD CANNOT BE EMPTY");
                    }else if(UserNames.length()!=0 && !UserNames.matches(namePattern)){
                        UserName.requestFocus();
                        UserName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                    }
                    if(Passwords.length()==0){
                        Password.requestFocus();
                        Password.setError("FIELD CANNOT BE EMPTY");
                    }
                }else{

                    Gson gson = new GsonBuilder().serializeNulls().create();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://staging.php-dev.in:8844/trainingapp/api/users/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    loginAPI = retrofit.create(LoginAPI.class);

                    createLogin();
                }

            }
        });
    }

    private void createLogin(){
//        int n = Integer.parseInt(Phones);
//        System.out.println(n);
       LoginModel loginModel = new LoginModel(UserNames,Passwords);
        System.out.println("-------------------------------------------------------");
        System.out.println(loginModel.getEmail());

        Call<LoginModel> call = loginAPI.createLogin(loginModel);

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(!response.isSuccessful()){
                    System.out.println("------------------UnSucessful------------------");
                    System.out.println(response.code());
                    // return;
                }
//                RegisterModel postResponse = response.body();
//
//                String content = "";
//                content += "Code: " + response.code()+ "\n";
//                content += "First Name: " + postResponse.getFirst_name() + "\n";
//                content += "Last Name: " + postResponse.getLast_name() + "\n";
//                content += "Email : " + postResponse.getEmail() + "\n";
//                content += "Password : " + postResponse.getPassword() + "\n";
//                content += "Con Password : " + postResponse.getConfirm_password() + "\n";
//                content += "Gender  : " + postResponse.getGender() + "\n";
//                content += "Phone no : " + postResponse.getPhone_no() + "\n\n";

//                System.out.println("--------------------------------------------------------------------------------------------------");
//                System.out.println(response.code());
//                System.out.println(content);
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff------UnSucessful------------------");
            }
        });
    }
}
