package com.pintu.neostore.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.view.login.Login;

public class LoginVMFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;

    public LoginVMFactory(Login login) {
        this.context = login;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginVM(context);
    }
}
