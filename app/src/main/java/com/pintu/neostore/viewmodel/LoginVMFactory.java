package com.pintu.neostore.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.login.Login;
import com.pintu.neostore.model.LoginModel;

public class LoginVMFactory extends ViewModelProvider.NewInstanceFactory {

   // private LoginModel loginModel;
    private Context context;
    public LoginVMFactory(Login login) {
        this.context = login;
   //     this.loginModel = loginModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new LoginVM(context);
    }
}
