package com.pintu.neostore.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.register.Register;

public class RegisterVMFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;
    public RegisterVMFactory(Register register) {
        this.context = register;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new RegisterVM(context);
    }
}
