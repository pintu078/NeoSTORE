package com.pintu.neostore.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pintu.neostore.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;


public class Register extends AppCompatActivity {

    private  RegisterAPI registerAPI;

    EditText FirstName,LastName,Email,Password,CPassword,Phone;
    TextView tvGenderEr, tvChkboxEr;
    RadioGroup rdgGender,rdgChkbox;
    RadioButton Male,Female,rbcheckbox;
    Button register;

    String Fnames,Lnames,Emails,Passwords,CPasswords,Phones;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namePattern = "[a-zA-Z]+";
    String passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
    String genders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);
//       getSupportActionBar().hide();
         TextView textView = findViewById(R.id.text);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    //    getSupportActionBar().setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Register");





        FirstName = (EditText)findViewById(R.id.ed_first_name);
        LastName = (EditText)findViewById(R.id.ed_last_name);
        Email = (EditText)findViewById(R.id.ed_email);
        Password = (EditText)findViewById(R.id.ed_password);
        CPassword = (EditText)findViewById(R.id.ed_c_password);
        Phone = (EditText)findViewById(R.id.ed_phone);
        rdgGender = (RadioGroup)findViewById(R.id.rdg_gender);
        Male = (RadioButton)findViewById(R.id.male);
        Female = (RadioButton)findViewById(R.id.female);
        tvGenderEr = (TextView)findViewById(R.id.tv_gender_er);
        register = (Button)findViewById(R.id.btn_register);
        rdgChkbox = (RadioGroup)findViewById(R.id.rdg_chkbox);
        rbcheckbox = (RadioButton)findViewById(R.id.chkbox);
        tvChkboxEr = (TextView) findViewById(R.id.tv_chkbox);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fnames = FirstName.getText().toString().trim();
                Lnames = LastName.getText().toString().trim();
                Emails =Email.getText().toString().trim();
                Passwords = Password.getText().toString().trim();
                CPasswords = CPassword.getText().toString().trim();
                Phones = Phone.getText().toString().trim();
                System.out.println("-------------------------------------Data----------------------------------------");
                System.out.println(Fnames+"  "+Lnames+"  "+Emails+"  "+Passwords+"  "+CPasswords+"  "+genders+"  "+Phones);


                int isSelected_Gender = rdgGender.getCheckedRadioButtonId();
                int isSelected_Chkbox = rdgChkbox.getCheckedRadioButtonId();
                class Radio_Check implements CompoundButton.OnCheckedChangeListener{

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(Male.isChecked()){
                            tvGenderEr.setVisibility(View.GONE);
                            tvGenderEr.setError(null);
                            System.out.println("---------------------------------------------"+genders);
                            genders = Male.getText().toString();
                            System.out.println("---------------------------------------------"+genders);
                        }else if(Female.isChecked()){
                            tvGenderEr.setVisibility(View.GONE);
                            tvGenderEr.setError(null);
                            genders = Female.getText().toString();
                        }
                        if(rbcheckbox.isChecked()){
                            tvChkboxEr.setVisibility(View.GONE);
                            tvChkboxEr.setError(null);
                        }
                    }
                }

                if(Fnames.length()==0 || Lnames.length()==0 || Emails.length()==0 || Passwords.length()==0 || CPasswords.length()==0 || Phones.length()==0 || isSelected_Chkbox == -1 || isSelected_Gender == -1 || !Fnames.matches(namePattern) || !Lnames.matches(namePattern) || !Emails.matches(emailPattern) || Phones.length()!=10 ){

                    if(Fnames.length()==0){
                        FirstName.requestFocus();
                        FirstName.setError("FIELD CANNOT BE EMPTY");
                    }else if(Fnames.length()!=0 && !Fnames.matches(namePattern)){
                        FirstName.requestFocus();
                        FirstName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                    }
                    if(Lnames.length()==0){
                        LastName.requestFocus();
                        LastName.setError("FIELD CANNOT BE EMPTY");
                    }else if(Lnames.length()!=0 && !Lnames.matches(namePattern)){
                        LastName.requestFocus();
                        LastName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                    }
                    if(Emails.length()==0){
                        Email.requestFocus();
                        Email.setError("FIELD CANNOT BE EMPTY");
                    }else if(Emails.length()!=0 && !Emails.matches(emailPattern)){
                        Email.requestFocus();
                        Email.setError("Invalid Email");
                    }
                    if(Passwords.length()==0){
                        Password.requestFocus();
                        Password.setError("FIELD CANNOT BE EMPTY");
                    }
//                    else if(Password.length()!=0 && !Password.matches(passwordPattern)){
//                        Password.requestFocus();
//                        Password.setError("Invalid Password");
//                    }
                    if(CPasswords.length()==0){
                        CPassword.requestFocus();
                        CPassword.setError("FIELD CANNOT BE EMPTY");
                    }
                    if(isSelected_Gender == -1){
                        tvGenderEr.setVisibility(View.VISIBLE);
                        tvGenderEr.setError("Select Gender");
                        Male.setOnCheckedChangeListener(new Radio_Check());
                    }
                    if(isSelected_Gender == -1){
                        tvGenderEr.setVisibility(View.VISIBLE);
                        tvGenderEr.setError("Select Gender");
                        Female.setOnCheckedChangeListener(new Radio_Check());
                    }
                    if(isSelected_Chkbox == -1){
                        tvChkboxEr.setVisibility(View.VISIBLE);
                        tvChkboxEr.setError(" ");
                        rbcheckbox.setOnCheckedChangeListener(new Radio_Check());
                    }

                    if(Phones.length()==0){
                        Phone.requestFocus();
                        Phone.setError("FIELD CANNOT BE EMPTY");
                    }else if (Phones.length() != 10) {
                        Phone.requestFocus();
                        Phone.setError("ENTER 10 DIGIT PHONE NO");
                    }
                }else if(!Passwords.equals(CPasswords)){
                    CPassword.requestFocus();
                    CPassword.setError("Password MisMatch");
                } else{
                    if(Male.isChecked()){
                        System.out.println("---------------------------------------------"+genders);
                        genders = Male.getText().toString();
                        System.out.println("---------------------------------------------"+genders);
                    }else if(Female.isChecked()){
                        genders = Female.getText().toString();
                        System.out.println("---------------------------------------------"+genders);
                    }

                    Gson gson = new GsonBuilder().serializeNulls().create();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://staging.php-dev.in:8844/trainingapp/api/users/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    registerAPI = retrofit.create(RegisterAPI.class);

                    createPostt();

                    System.out.println("---------------------------------Register-------------------------------------------");
//                    AppConstant.mydatas.add(new MyData(Fnames,Lnames,Emails,Passwords,CPasswords,genders,Phones));
//                    System.out.println(AppConstant.mydatas.get(0));
//                    Intent intent = new Intent(getApplicationContext(), Display.class);
//                    startActivity(intent);
                }
            }
        });
    }

    private void createPostt(){
//        Integer n = Integer.parseInt(Phones);
//        System.out.println(n);
        RegisterModel registerModel = new RegisterModel(Fnames,Lnames,Emails,Passwords,CPasswords,genders,Phones);
        System.out.println("-------------------------------------------------------");
        System.out.println(registerModel.getFirst_name());

          Call<RegisterModel> call = registerAPI.createPost(registerModel.getFirst_name(),registerModel.getLast_name(),registerModel.getEmail(),registerModel.getPassword(),registerModel.getConfirm_password(),registerModel.getGender(),registerModel.getPhone_no());
//        Call<RegisterModel> call = registerAPI.createPost(registerModel);
        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                if(!response.isSuccessful()){
                    System.out.println("------------------UnSucessful------------------");
                    System.out.println(response.message());
                    System.out.println(response.code());
//                    return
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
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                System.out.println("---------------Edited------------------------------------------");
                System.out.println(t.getMessage());
                System.out.println("------------ff------UnSucessful------------------");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
