package com.pintu.neostore.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pintu.neostore.R;
import com.pintu.neostore.view.drawer.tabel.ProductDetailed;
import com.pintu.neostore.model.ProductDetailed_Model.ProductImage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductDetailedAdapter extends RecyclerView.Adapter<ProductDetailedAdapter.MyViewHolder> {

    List<ProductImage> productImages;
    Context context;
    int selectedItem ;


    public ProductDetailedAdapter(Context context, List<ProductImage> productImages) {
        this.context = context;;
        this.productImages = productImages;
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
                .load(productImages.get(position).getImage())
                .fit()
                .into(holder.image);

        Picasso.with(context)
                .load(productImages.get(0).getImage())
                .fit()
                .into(ProductDetailed.img_main);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedItem = position;
                Log.d("saurabh","selectedItem  "+selectedItem);
                notifyDataSetChanged();
            }
        });

        Log.d("saurabh","po  "+position);
        if(selectedItem==position){
            Log.d("saurabh","selectedItem-if  "+selectedItem);
            Log.d("saurabh","po-if "+position);
            Drawable d = context.getResources().getDrawable(R.drawable.stroke_box_red);
            holder.itemView.setBackground(d);

        }else{
            Drawable d = context.getResources().getDrawable(R.drawable.stroke_box_black);
            holder.itemView.setBackground(d);
        }
        Picasso.with(context)
                .load(productImages.get(selectedItem).getImage())
                .fit()
                .into(ProductDetailed.img_main);

    }

    @Override
    public int getItemCount() {
        System.out.println("------------------------ffff-----------------------");
      System.out.println("images size "+productImages.size());
        return productImages.size();
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