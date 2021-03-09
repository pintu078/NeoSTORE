package com.pintu.neostore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.pintu.neostore.R;
import com.pintu.neostore.model.ProductList_APIMsg;
import com.pintu.neostore.model.ProductList_Data;
import com.squareup.picasso.Picasso;


import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    List<ProductList_Data> al;
    Context context;

    public ProductListAdapter(Context context, List<ProductList_Data> al) {
        this.context = context;
        this.al = al;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tables_row, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Picasso.with(context)
                .load(al.get(position).getProductImages())
                .fit()
                .into(holder.Img);
        holder.txt1.setText(al.get(position).getName());
        holder.txt2.setText(al.get(position).getProducer());
        holder.txt3.setText("Rs. "+al.get(position).getCost());
        holder.ratingBar.setRating(al.get(position).getRating().floatValue());

    }

    @Override
    public int getItemCount() {
        System.out.println("-=-----------" + al.size());
        System.out.println(al.size());
        return al.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView Img;
        TextView txt1,txt2,txt3;
        RatingBar ratingBar;


        public MyViewHolder(View itemView) {
            super(itemView);
            Img = (ImageView) itemView.findViewById(R.id.tableimage);
            txt1 = (TextView) itemView.findViewById(R.id.nametextview);
            txt2 = (TextView) itemView.findViewById(R.id.desctextview);
            txt3 = (TextView) itemView.findViewById(R.id.pricetxtview);
            ratingBar = (RatingBar)itemView.findViewById(R.id.ratingbar);
        }
    }
}

