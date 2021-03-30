package com.pintu.neostore.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pintu.neostore.R;
import com.pintu.neostore.view.drawer.mycart.MyCart;
import com.pintu.neostore.view.login.Login;

import com.pintu.neostore.model.Cart.listcart_items.ListCartItem_Data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {

    String token;
    SharedPreferences sp;
    Boolean initialDisplay = true;
    List<ListCartItem_Data> al;
    Context context;
    private boolean mSpinnerInitialized;


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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Picasso.with(context)
                .load(al.get(position).getProduct().getProductImages())
                .fit()
                .into(holder.Img);
        holder.txt1.setText(al.get(position).getProduct().getName());
        holder.txt2.setText(al.get(position).getProduct().getProductCategory());
        holder.listPrice.setText("₹ " + al.get(position).getProduct().getSubTotal() + "0.0");


        sp = context.getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
        token = sp.getString("Token", "");
        Log.d("saurabh", "token " + token);


        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(dataAdapter);
        //  holder.spinner.setText(al.get(position).getQuantity()).toString();
        int spinnerPosition = al.get(position).getQuantity();
        holder.spinner.setSelection(spinnerPosition - 1);


//        holder.Delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String Id = al.get(position).getProductId().toString();
//                MyCart.deleteCartVM.loadDeleteCart(token,Id);
//               al.remove(position);
//               notifyItemRemoved(position);
//            }
//        });
        initialDisplay = false;
        holder.spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                initialDisplay = true;
                v.performClick();
                return false;
            }
        });



    }

    @Override
    public int getItemCount() {
        System.out.println("-=-----------" + al.size());
        System.out.println(al.size());
        return al.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView Img;
        ImageButton Delete;
        TextView txt1, txt2, listPrice;
        Spinner spinner;
        SharedPreferences sp;


        public MyViewHolder(final View itemView) {
            super(itemView);
            Img = (ImageView) itemView.findViewById(R.id.cartimage);
            txt1 = (TextView) itemView.findViewById(R.id.tv_cart_1);
            txt2 = (TextView) itemView.findViewById(R.id.tv_cart_2);
            listPrice = (TextView) itemView.findViewById(R.id.tv_cart_price);
            spinner = (Spinner) itemView.findViewById(R.id.spinner);
            Delete = (ImageButton) itemView.findViewById(R.id.delete);

            context = itemView.getContext();


            Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemPosition = getLayoutPosition();
                    final String Id = al.get(itemPosition).getProductId().toString();
                    Log.d("saurabh", "delete " + Id);
                    MyCart.deleteCartVM.loadDeleteCart(token, Id);
                 //   al.clear();
                }
            });

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                    int itemPosition = getLayoutPosition();
                    String quantity = parent.getItemAtPosition(position).toString();
                    Log.d("saurabh", "Spinner   " + quantity);

                    String Id = al.get(itemPosition).getProductId().toString();
                    Log.d("saurabh", "delete " + Id);

                    if(initialDisplay) {
                        MyCart.editCartVM.loadEdit(token, Id, quantity);
//                        al.clear();
                    }

                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }
}
