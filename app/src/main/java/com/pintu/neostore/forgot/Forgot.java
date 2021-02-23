package com.pintu.neostore.forgot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pintu.neostore.APIMsg;
import com.pintu.neostore.R;
import com.pintu.neostore.login.Login;
import com.pintu.neostore.login.LoginAPI;
import com.pintu.neostore.login.LoginModel;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Forgot extends AppCompatActivity {

    private APIMsg message;

    TextView  Email;
    Button  Submit;

    String Emails;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ForgotAPI forgotAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Forgot Password");

        Email = (TextView)findViewById(R.id.ed_email);
        Submit = (Button)findViewById(R.id.btn_submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Emails =Email.getText().toString().trim();

                if(Emails.length() == 0 || !Emails.matches(emailPattern)){
                    if(Emails.length()==0){
                        Email.requestFocus();
                        Email.setError("FIELD CANNOT BE EMPTY");
                    }else if(Emails.length()!=0 && !Emails.matches(emailPattern)){
                        Email.requestFocus();
                        Email.setError("Invalid Email");
                    }
                }else{
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://staging.php-dev.in:8844/trainingapp/api/users/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    forgotAPI = retrofit.create(ForgotAPI.class);

                    createForgot();

                }
            }
        });
    }

    private void createForgot(){
//        int n = Integer.parseInt(Phones);
//        System.out.println(n);
        ForgotModel forgotModel = new ForgotModel(Emails);
        System.out.println("-------------------------------------------------------");
        System.out.println(forgotModel.getEmail());

        Call<APIMsg> call = forgotAPI.createForgot(Emails);

        call.enqueue(new Callback<APIMsg>() {
            @Override
            public void onResponse(Call<APIMsg> call, Response<APIMsg> response) {
                if(response.isSuccessful()){

                    Toast.makeText(Forgot.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(
                                Forgot.this,
                                jObjError.getString("user_msg"),
                                Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(Forgot.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }
            @Override
            public void onFailure(Call<APIMsg> call, Throwable t) {
                Toast.makeText(Forgot.this,"Check Internet Connection",Toast.LENGTH_LONG).show();
                System.out.println("-------------------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff------UnSucessful------------------");
            }
        });
    }

    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
