package com.pintu.neostore.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.Transliterator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pintu.neostore.R;
import com.pintu.neostore.drawer.tabel.ProductDetailed;
import com.pintu.neostore.model.ProductDetailed_Model.ProductImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailedAdapter extends RecyclerView.Adapter<ProductDetailedAdapter.MyViewHolder> {

    List<ProductImage> personImages;
    Context context;


    public ProductDetailedAdapter(Context context, List<ProductImage> personImages) {
        this.context = context;;
        this.personImages = personImages;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_product_detailed_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
   //     holder.image.setImageResource(Integer.parseInt(personImages.get(position).getImage()));

        Picasso.with(context)
                .load(personImages.get(position).getImage())
                .fit()
                .into(holder.image);

        Picasso.with(context)
                .load(personImages.get(0).getImage())
                .fit()
                .into(ProductDetailed.img_main);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          //      view.setBackgroundColor(Color.WHITE);
                holder.itemView.setBackgroundColor(Color.RED);
                Picasso.with(context)
                        .load(personImages.get(position).getImage())
                        .fit()
                        .into(ProductDetailed.img_main);
                view.setBackgroundColor(Color.RED);
            }
        });

    }

    @Override
    public int getItemCount() {
        System.out.println("------------------------ffff-----------------------");
      System.out.println("images size "+personImages.size());
        return personImages.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        private ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}