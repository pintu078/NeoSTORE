package com.pintu.neostore.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pintu.neostore.view.drawer.order.OrderList;

public class OrderListVMFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;

    public OrderListVMFactory(OrderList orderList) {
        this.context = orderList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new OrderListVM(context);
    }
}
