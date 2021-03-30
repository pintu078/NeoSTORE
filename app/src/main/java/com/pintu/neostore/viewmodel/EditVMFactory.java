package com.pintu.neostore.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.view.drawer.MyAccount.EditProfile;


public class EditVMFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;

    public EditVMFactory(EditProfile editProfile) {
        this.context = editProfile;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EditProfileVM(context);
    }
}
