package com.pintu.neostore.view.drawer.MyAccount;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.pintu.neostore.R;
import com.pintu.neostore.view.login.Login;
import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.viewmodel.EditProfileVM;
import com.pintu.neostore.viewmodel.EditVMFactory;
import com.pintu.neostore.viewmodel.FetchVM;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class EditProfile extends AppCompatActivity {

    EditText FirstName, LastName, Email, Phone, DOB;
    TextView tv_Img;
    public static Button Submit;
    ImageButton imgbtn_Back;
    ImageView imgProfile;
    public static ProgressBar progressBar;
    String Fnames, Lnames, Emails, Phones, DOBs;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namePattern = "[a-zA-Z]+";
    String token;

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    EditProfileVM editProfileVM;
    FetchVM fetchVM;
    ImageView profileImg;
    Bitmap bitmap;
    String bitprofile = "";


    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        FirstName = (EditText) findViewById(R.id.ed_first_name);
        LastName = (EditText) findViewById(R.id.ed_last_name);
        Email = (EditText) findViewById(R.id.ed_email);
        Phone = (EditText) findViewById(R.id.ed_phone);
        DOB = (EditText) findViewById(R.id.ed_dob);
        Submit = (Button) findViewById(R.id.btn_submit);
        imgbtn_Back = (ImageButton) findViewById(R.id.imgbtn_back);
        profileImg = findViewById(R.id.profile_img);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        tv_Img = (TextView)findViewById(R.id.tv_img);

        sp = getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
        FirstName.setText(sp.getString("FName", ""));
        LastName.setText(sp.getString("LName", ""));
        Email.setText(sp.getString("Email", ""));
        Phone.setText(sp.getString("Phone", ""));
        DOB.setText(sp.getString("DOB", "00-00-0000"));
        token = sp.getString("Token", "");

        String image = sp.getString("Profile", "");
        Log.d("saurabh","image "+image);

        if (!image.equals("null")) {
            Picasso.with(getApplicationContext())
                    .load(image)
                    .fit()
                    .into(profileImg);
            Log.d("saurabh", token + "  edit");
            tv_Img.setVisibility(View.INVISIBLE);
        }else{
            tv_Img.setVisibility(View.VISIBLE);
            String fini=(sp.getString("FName","")).toUpperCase();
            String lini=(sp.getString("LName","")).toUpperCase();
            String initials=fini.substring(0,1)+lini.substring(0,1);

            tv_Img.setText(initials);
        }

        editProfileVM = new ViewModelProvider(this, new EditVMFactory(this)).get(EditProfileVM.class);
        editProfileVM.getEditListObserver().observe(this, new Observer<APIMsg>() {
            @Override
            public void onChanged(APIMsg apiMsg) {

                Log.d("saurabh", "OnChanged");
                if (apiMsg != null) {
                    String FName = apiMsg.getData().getFirstName();
                    String LName = apiMsg.getData().getLastName();
                    String UName = apiMsg.getData().getUsername();
                    String Email = apiMsg.getData().getEmail();
                    String Gender = apiMsg.getData().getGender();
                    String Phone = apiMsg.getData().getPhoneNo();
                    String Token = apiMsg.getData().getAccessToken();
                    String dob = String.valueOf(apiMsg.getData().getDob());
                    String profile = String.valueOf(apiMsg.getData().getProfilePic());

                    SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("FName", FName.toUpperCase());
                    myEdit.putString("LName", LName.toUpperCase());
                    myEdit.putString("UName", UName);
                    myEdit.putString("Email", Email);
                    myEdit.putString("Gender", Gender);
                    myEdit.putString("Phone", Phone);
                    myEdit.putString("Token", Token);
                    myEdit.putString("DOB", dob);
                    myEdit.putString("Profile", profile);

                   // Log.d("saurabh", "Profile " + profile);

                    myEdit.apply();
                    myEdit.commit();
                    Intent intent = new Intent(EditProfile.this, MyAccount.class);
                    setResult(2, intent);
                    finish();

                }
            }
        });

        imgbtn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfile.super.onBackPressed();
            }
        });

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfile.this, R.style.DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                DOB.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fnames = FirstName.getText().toString().trim();
                Lnames = LastName.getText().toString().trim();
                Emails = Email.getText().toString().trim();
                Phones = Phone.getText().toString().trim();
                DOBs = DOB.getText().toString().trim();
//                System.out.println("-------------------------------------Data----------------------------------------");
//                System.out.println(Fnames+"  "+Lnames+"  "+Emails+"  "+Phones);

                if (Fnames.length() == 0 || Lnames.length() == 0 || Emails.length() == 0 || !Fnames.matches(namePattern) || !Lnames.matches(namePattern) || !Emails.matches(emailPattern) || Phones.length() != 10) {

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
                    if (Phones.length() == 0) {
                        Phone.requestFocus();
                        Phone.setError("FIELD CANNOT BE EMPTY");
                    } else if (Phones.length() != 10) {
                        Phone.requestFocus();
                        Phone.setError("ENTER 10 DIGIT PHONE NO");
                    }
//                    if(DOBs.length()==0) {
//                        DOB.requestFocus();
//                        DOB.setError("FIELD CANNOT BE EMPTY");
//                    }
                    // EditVM.makeRegisterApiCall(Fnames,Lnames,Emails,Phones);
                    System.out.println("---------------------------------Register-------------------------------------------");
                } else {
                    System.out.println("-------------------------------------Data----------------------------------------");
                    System.out.println(token + " " + Fnames + "  " + Lnames + "  " + Emails + "  " + DOBs + "  " + Phones);

                    if (bitprofile.equals("")) {
                        bitprofile="";
                        editProfileVM.loadEditLists(token, Fnames, Lnames, Emails, DOBs, bitprofile, Phones);
                        progressBar.setVisibility(View.VISIBLE);
                        Submit.setVisibility(View.GONE);
                        Log.d("saurabh", "if " + bitprofile);
                    } else {
                        editProfileVM.loadEditLists(token, Fnames, Lnames, Emails, DOBs, bitprofile, Phones);
                        progressBar.setVisibility(View.VISIBLE);
                        Submit.setVisibility(View.GONE);
                        Log.d("saurabh", "else " + bitprofile);
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            Log.d("saurabh", "path  " + path);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                profileImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
            byte[] imgByte = byteArrayOutputStream.toByteArray();
            bitprofile = Base64.encodeToString(imgByte, Base64.DEFAULT);
            bitprofile = "data:image/jpg;base64," + bitprofile;
            tv_Img.setVisibility(View.INVISIBLE);
            Log.d("saurabh", "bit profilr-- " + bitprofile);
        }
    }
}