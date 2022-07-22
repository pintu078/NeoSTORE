package com.pintu.neostore.view.forgot;

import android.content.Intent;
import android.os.Bundle;
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

import com.pintu.neostore.model.APIMsg;
import com.pintu.neostore.R;

import com.pintu.neostore.viewmodel.ForgotVM;
import com.pintu.neostore.viewmodel.ForgotVMFactory;

public class Forgot extends AppCompatActivity {

    private APIMsg message;

    EditText Email;
    public static Button Submit;
    public static ProgressBar progressBar;

    String Emails;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ForgotVM forgotVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.forgot_main);

        Email = (EditText) findViewById(R.id.ed_email);
        Submit = (Button) findViewById(R.id.btn_submit);
        progressBar =(ProgressBar)findViewById(R.id.progress_bar);

        forgotVM = new ViewModelProvider(this, new ForgotVMFactory(this)).get(ForgotVM.class);
        forgotVM.getForgotListObserver().observe(this, new Observer<APIMsg>() {
            @Override
            public void onChanged(APIMsg apiMsg) {
                System.out.println("---------1-------");
                if (apiMsg != null) {
                    System.out.println("---------2-------");
                    Intent intent = new Intent(com.pintu.neostore.view.forgot.Forgot.this, com.pintu.neostore.view.login.Login.class);
                   // startActivity(intent);
                    setResult(2,intent);
                    finish();

                } else {
                    System.out.println("---------3-------");

                }
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Emails = Email.getText().toString().trim();

                if (Emails.length() == 0 || !Emails.matches(emailPattern)) {
                    if (Emails.length() == 0) {
                        Email.requestFocus();
                        Email.setError("FIELD CANNOT BE EMPTY");
                    } else if (Emails.length() != 0 && !Emails.matches(emailPattern)) {
                        Email.requestFocus();
                        Email.setError("Invalid Email");
                    }
                } else {

                    forgotVM.forgotApiCall(Emails);
                    Submit.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
