package com.pintu.neostore.register;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.login.Login;
import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.R;

import com.pintu.neostore.viewmodel.RegisterVM;
import com.pintu.neostore.viewmodel.RegisterVMFactory;


public class Register extends AppCompatActivity {


    RegisterVM registerVM;

    EditText FirstName, LastName, Email, Password, CPassword, Phone;
    TextView tvGenderEr, tvChkboxEr;
    RadioGroup rdgGender, rdgChkbox;
    RadioButton Male, Female, rbcheckbox;
    public static Button register;
    public static ProgressBar progressBar;

    String Fnames, Lnames, Emails, Passwords, CPasswords, Phones;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namePattern = "[a-zA-Z]+";
    String passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
    String genders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        TextView textView = findViewById(R.id.text);
        ImageButton imgbtn = findViewById(R.id.imgbtn);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register.super.onBackPressed();
                finish();
            }
        });

        FirstName = (EditText) findViewById(R.id.ed_first_name);
        LastName = (EditText) findViewById(R.id.ed_last_name);
        Email = (EditText) findViewById(R.id.ed_email);
        Password = (EditText) findViewById(R.id.ed_password);
        CPassword = (EditText) findViewById(R.id.ed_c_password);
        Phone = (EditText) findViewById(R.id.ed_phone);
        rdgGender = (RadioGroup) findViewById(R.id.rdg_gender);
        Male = (RadioButton) findViewById(R.id.male);
        Female = (RadioButton) findViewById(R.id.female);
        tvGenderEr = (TextView) findViewById(R.id.tv_gender_er);
        register = (Button) findViewById(R.id.btn_register);
        rdgChkbox = (RadioGroup) findViewById(R.id.rdg_chkbox);
        rbcheckbox = (RadioButton) findViewById(R.id.chkbox);
        tvChkboxEr = (TextView) findViewById(R.id.tv_chkbox);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        registerVM = new ViewModelProvider(this, new RegisterVMFactory(this)).get(RegisterVM.class);
        registerVM.getLoginListObserver().observe(this, new Observer<APIMsg>() {
            @Override
            public void onChanged(APIMsg apiMsg) {
                System.out.println("---------1-------");
                if (apiMsg != null) {
                    System.out.println("---------2-------");
                    Intent intent = new Intent(Register.this, Login.class);
                   // startActivity(intent);
                    setResult(2,intent);
                    finish();
                } else {
                    System.out.println("---------3-------");

                }
            }

        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fnames = FirstName.getText().toString().trim();
                Lnames = LastName.getText().toString().trim();
                Emails = Email.getText().toString().trim();
                Passwords = Password.getText().toString().trim();
                CPasswords = CPassword.getText().toString().trim();
                Phones = Phone.getText().toString().trim();
                System.out.println("-------------------------------------Data----------------------------------------");
                System.out.println(Fnames + "  " + Lnames + "  " + Emails + "  " + Passwords + "  " + CPasswords + "  " + genders + "  " + Phones);
                Drawable icon = getResources().getDrawable(R.drawable.ic_error);


                int isSelected_Gender = rdgGender.getCheckedRadioButtonId();
                int isSelected_Chkbox = rdgChkbox.getCheckedRadioButtonId();
                class Radio_Check implements CompoundButton.OnCheckedChangeListener {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (Male.isChecked()) {
                            tvGenderEr.setVisibility(View.GONE);
                            tvGenderEr.setError(null);
                            System.out.println("---------------------------------------------" + genders);
                            genders = Male.getText().toString().substring(0, 1);
                            System.out.println("---------------------------------------------" + genders);
                        } else if (Female.isChecked()) {
                            tvGenderEr.setVisibility(View.GONE);
                            tvGenderEr.setError(null);
                            genders = Female.getText().toString().substring(0, 1);
                        }
                        if (rbcheckbox.isChecked()) {
                            tvChkboxEr.setVisibility(View.GONE);
                            tvChkboxEr.setError(null);
                        }
                    }
                }

                if (Fnames.length() == 0 || Lnames.length() == 0 || Emails.length() == 0 || Passwords.length() == 0 || CPasswords.length() == 0 || Phones.length() == 0 || isSelected_Chkbox == -1 || isSelected_Gender == -1 || !Fnames.matches(namePattern) || !Lnames.matches(namePattern) || !Emails.matches(emailPattern) || Phones.length() != 10 || (Passwords.length()>0 && Passwords.length()<5) || (CPasswords.length()>0 && CPasswords.length()<5)) {

                    if (Fnames.length() == 0) {
                        FirstName.requestFocus();
                        FirstName.setError("FIELD CANNOT BE EMPTY");
                    } else if (Fnames.length() != 0 && !Fnames.matches(namePattern)) {
                        FirstName.requestFocus();
                        FirstName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                    }
                    if (Lnames.length() == 0) {
                        LastName.requestFocus();
                        LastName.setError("FIELD CANNOT BE EMPTY");
                    } else if (Lnames.length() != 0 && !Lnames.matches(namePattern)) {
                        LastName.requestFocus();
                        LastName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                    }
                    if (Emails.length() == 0) {
                        Email.requestFocus();
                        Email.setError("FIELD CANNOT BE EMPTY");
                    } else if (Emails.length() != 0 && !Emails.matches(emailPattern)) {
                        Email.requestFocus();
                        Email.setError("Invalid Email");
                    }
                    if (Passwords.length() == 0) {
                        Password.requestFocus();
                        Password.setError("FIELD CANNOT BE EMPTY");
                    }
                    if (CPasswords.length() == 0) {
                        CPassword.requestFocus();
                        CPassword.setError("FIELD CANNOT BE EMPTY");
                    }
                    if (isSelected_Gender == -1) {
                        tvGenderEr.setVisibility(View.VISIBLE);
                        tvGenderEr.setError("Select Gender");
                        Male.setOnCheckedChangeListener(new Radio_Check());
                    }
                    if (isSelected_Gender == -1) {
                        tvGenderEr.setVisibility(View.VISIBLE);
                        tvGenderEr.setError("Select Gender");
                        Female.setOnCheckedChangeListener(new Radio_Check());
                    }
                    if (isSelected_Chkbox == -1) {
                        tvChkboxEr.setVisibility(View.VISIBLE);
                        tvChkboxEr.setError(" ");
                        rbcheckbox.setOnCheckedChangeListener(new Radio_Check());
                    }
                    if (Phones.length() == 0) {
                        Phone.requestFocus();
                        Phone.setError("FIELD CANNOT BE EMPTY");
                    } else if (Phones.length() != 10) {
                        Phone.requestFocus();
                        Phone.setError("ENTER 10 DIGIT PHONE NO");
                    }
                    if(Passwords.length()>0 && Passwords.length()<6){
                        Password.requestFocus();
                        Password.setError("Password Greater than 6 Digit");
                    }
                    if(CPasswords.length()>0 && CPasswords.length()<6){
                        CPassword.requestFocus();
                        CPassword.setError("Password Greater than 6 Digit");
                    }
                } else if (!Passwords.equals(CPasswords)) {
                    if (!Passwords.equals(CPasswords)) {
                        CPassword.requestFocus();
                        CPassword.setError("Password MisMatch");
                    }
                } else {
                    if (Male.isChecked()) {
                        System.out.println("---------------------------------------------" + genders);
                        genders = Male.getText().toString().substring(0, 1);
                        System.out.println("---------------------------------------------" + genders);
                    } else if (Female.isChecked()) {
                        genders = Female.getText().toString().substring(0, 1);
                        System.out.println("---------------------------------------------" + genders);
                    }

                    System.out.println("-------------------------------------Data----------------------------------------");
                    System.out.println(Fnames + "  " + Lnames + "  " + Emails + "  " + Passwords + "  " + CPasswords + "  " + genders + "  " + Phones);

                    registerVM.makeRegisterApiCall(Fnames, Lnames, Emails, Passwords, CPasswords, genders, Phones);
                    register.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    System.out.println("---------------------------------Register-------------------------------------------");
                }
            }
        });
    }
}
