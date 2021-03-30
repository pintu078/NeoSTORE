package com.pintu.neostore.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.view.drawer.tabel.ProductDetailed;


public class ProductDetailedVMFactory extends ViewModelProvider.NewInstanceFactory {

    // private LoginModel loginModel;
    private Context context;
    public ProductDetailedVMFactory(ProductDetailed productDetailed) {
        this.context = productDetailed;
        //     this.loginModel = loginModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new ProductDetailedVM(context);
    }
}