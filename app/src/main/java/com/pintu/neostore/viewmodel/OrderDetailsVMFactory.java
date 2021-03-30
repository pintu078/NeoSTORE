package com.pintu.neostore.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.view.drawer.order.OrderDetails;

public class OrderDetailsVMFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;

    public OrderDetailsVMFactory(OrderDetails orderDetails) {
        this.context = orderDetails;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new OrderDetailsVM(context);
    }
}
