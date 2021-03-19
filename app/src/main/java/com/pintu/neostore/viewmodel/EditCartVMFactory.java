package com.pintu.neostore.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.adapter.MyCartAdapter;
import com.pintu.neostore.drawer.mycart.MyCart;

public class EditCartVMFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;

    public EditCartVMFactory(MyCart myCart) {
        this.context = myCart;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EditCartVM(context);
    }
}
