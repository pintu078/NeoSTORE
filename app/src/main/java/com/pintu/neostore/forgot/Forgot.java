package com.pintu.neostore.forgot;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.R;

import com.pintu.neostore.viewmodel.ForgotVM;
import com.pintu.neostore.viewmodel.ForgotVMFactory;

public class Forgot extends AppCompatActivity {

    private APIMsg message;

    TextView  Email;
    Button  Submit;

    String Emails;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ForgotVM forgotVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.forgot_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Forgot Password");

        Email = (TextView)findViewById(R.id.ed_email);
        Submit = (Button)findViewById(R.id.btn_submit);

        forgotVM = new ViewModelProvider(this,new ForgotVMFactory(this)).get(ForgotVM.class);
        forgotVM.getForgotListObserver().observe(this, new Observer<APIMsg>() {
            @Override
            public void onChanged(APIMsg apiMsg) {
                System.out.println("---------1-------");
                if(apiMsg != null){
                    System.out.println("---------2-------");

                }else{
                    System.out.println("---------3-------");

                }
            }
        });

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

                    forgotVM.forgotApiCall(Emails);

                }
            }
        });
    }

}
