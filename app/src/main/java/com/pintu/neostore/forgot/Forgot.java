package com.pintu.neostore.forgot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.pintu.neostore.R;

public class Forgot extends AppCompatActivity {

    TextView  Email;
    Button  Submit;

    String Emails;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

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

                }
            }
        });
    }
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
