package com.pintu.neostore.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.view.drawer.mycart.MyCart;

public class DeleteCartVMFactory extends ViewModelProvider.NewInstanceFactory {

    // private LoginModel loginModel;
    private Context context;

    public DeleteCartVMFactory(MyCart myCart) {
        this.context = myCart;
        //     this.loginModel = loginModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DeleteCartVM(context);
    }
}
