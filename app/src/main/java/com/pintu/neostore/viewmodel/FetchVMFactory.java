package com.pintu.neostore.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.home.Home;

public class FetchVMFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;

    public FetchVMFactory(Home home) {
        this.context = home;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FetchVM(context);
    }
}