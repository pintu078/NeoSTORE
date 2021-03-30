package com.pintu.neostore.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.view.drawer.MyAccount.ResetPass;

public class ResetVMFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;

    public ResetVMFactory(ResetPass resetPass) {
        this.context = resetPass;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ResetVM(context);
    }
}
