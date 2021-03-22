package com.pintu.neostore.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.drawer.order.Address;
import com.pintu.neostore.drawer.tabel.ProductDetailed;

public class AddressVMFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;
    public AddressVMFactory(Address address) {
        this.context = address;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new AddressVM(context);
    }
}
