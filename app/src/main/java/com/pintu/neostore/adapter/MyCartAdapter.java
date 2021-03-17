package com.pintu.neostore.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pintu.neostore.R;
import com.pintu.neostore.drawer.tabel.ProductDetailed;
import com.pintu.neostore.model.Cart.listcart_items.ListCartItem_Data;
import com.pintu.neostore.model.ProductList_Data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {


    List<ListCartItem_Data> al;
    Context context;



    public MyCartAdapter(Context context, List<ListCartItem_Data> al) {
        this.context = context;
        this.al = al;
    }

    @Override
    public MyCartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_cart_row, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.with(context)
                .load(al.get(position).getProduct().getProductImages())
                .fit()
                .into(holder.Img);
        holder.txt1.setText(al.get(position).getProduct().getName());
        holder.txt2.setText(al.get(position).getProduct().getProductCategory());
        holder.listPrice.setText("₹ "+al.get(position).getProduct().getSubTotal()+"0.0");
      //  holder.spinner.setTextAlignment(al.get(position).getQuantity());
        // Spinner Drop down elements
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");

        ArrayAdapter<String> dataAdapter =new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(dataAdapter);
      //  holder.spinner.setText(al.get(position).getQuantity()).toString();
//        int spinnerPosition = al.get(position).getQuantity();
//        holder.spinner.setSelection(spinnerPosition);




    }


    @Override
    public int getItemCount() {
        System.out.println("-=-----------" + al.size());
        System.out.println(al.size());
        return al.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView Img;
        TextView txt1,txt2,txt3,listPrice;
        RatingBar ratingBar;
        Spinner spinner;
        String spin;



        public MyViewHolder(View itemView) {
            super(itemView);
            Img = (ImageView) itemView.findViewById(R.id.cartimage);
            txt1 = (TextView) itemView.findViewById(R.id.tv_cart_1);
            txt2 = (TextView) itemView.findViewById(R.id.tv_cart_2);
            listPrice = (TextView) itemView.findViewById(R.id.tv_cart_price);
            spinner = (Spinner)itemView.findViewById(R.id.spinner);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                    String item = parent.getItemAtPosition(position).toString();
                    Log.d("saurabh","Spinner   "+item);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
}
