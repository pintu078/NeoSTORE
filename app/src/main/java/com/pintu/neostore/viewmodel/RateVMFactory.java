package com.pintu.neostore.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.drawer.tabel.ProductDetailed;

public class RateVMFactory extends ViewModelProvider.NewInstanceFactory {

    // private LoginModel loginModel;
    private Context context;
    public RateVMFactory(ProductDetailed productDetailed) {
        this.context = productDetailed;
        //     this.loginModel = loginModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new RateVM(context);
    }
}
