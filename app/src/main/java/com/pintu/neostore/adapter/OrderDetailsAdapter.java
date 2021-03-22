package com.pintu.neostore.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pintu.neostore.R;
import com.pintu.neostore.drawer.order.OrderDetails;
import com.pintu.neostore.drawer.tabel.ProductDetailed;
import com.pintu.neostore.model.order.Order_List.Datum;
import com.pintu.neostore.model.order.order_details.Data;
import com.pintu.neostore.model.order.order_details.OrderDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.MyViewHolder> {


    List<OrderDetail> al;
    Context context;

    public OrderDetailsAdapter(Context context, List<OrderDetail> al) {
        this.context = context;
        this.al = al;
    }

    @Override
    public OrderDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_order_details_row, parent, false);
        OrderDetailsAdapter.MyViewHolder vh = new OrderDetailsAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.with(context)
                .load(al.get(position).getProdImage())
                .fit()
                .into(holder.Img);
        holder.od1.setText(al.get(position).getProdName());
        holder.od2.setText(al.get(position).getProdCatName());
        holder.qty.setText(al.get(position).getQuantity().toString());
        holder.price.setText("₹. "+al.get(position).getTotal());
    }


    @Override
    public int getItemCount() {
        System.out.println("-=-----------" + al.size());
        System.out.println(al.size());
        return al.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView od1,od2,price,qty;
        ImageView Img;

        public MyViewHolder(View itemView) {
            super(itemView);
            Img = (ImageView) itemView.findViewById(R.id.orderimage);
            od1 = (TextView) itemView.findViewById(R.id.tv_order_1);
            od2 = (TextView) itemView.findViewById(R.id.tv_order_2);
            qty = (TextView) itemView.findViewById(R.id.tv_order_3);
            price = (TextView) itemView.findViewById(R.id.tv_order_price);

            context = itemView.getContext();


        }
    }
}

