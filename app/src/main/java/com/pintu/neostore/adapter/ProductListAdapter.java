package com.pintu.neostore.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pintu.neostore.R;
import com.pintu.neostore.view.drawer.tabel.ProductDetailed;
import com.pintu.neostore.model.ProductList_Data;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> implements Filterable {


    List<ProductList_Data> al;
    List<ProductList_Data> al_filter;
    Context context;

    public ProductListAdapter(Context context, List<ProductList_Data> al) {
        this.context = context;
        this.al = al;
        this.al_filter = new ArrayList<>(al);

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
        holder.txt3.setText("Rs. " + al.get(position).getCost());
        holder.ratingBar.setRating(al.get(position).getRating().floatValue());

    }

    @Override
    public int getItemCount() {
        System.out.println("-=-----------" + al.size());
        System.out.println(al.size());
        return al.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        // run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<ProductList_Data> filteredList = new ArrayList<>();

            if(constraint.toString().isEmpty()){
                filteredList.addAll(al_filter);
            }else{
                for(ProductList_Data list : al_filter){
                    if(list.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredList.add(list);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }
        //runs on ui thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            al.clear();
            al.addAll((Collection<? extends ProductList_Data>) results.values);
            notifyDataSetChanged();
        }
    };


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView Img;
        TextView txt1, txt2, txt3;
        RatingBar ratingBar;


        public MyViewHolder(View itemView) {
            super(itemView);
            Img = (ImageView) itemView.findViewById(R.id.tableimage);
            txt1 = (TextView) itemView.findViewById(R.id.nametextview);
            txt2 = (TextView) itemView.findViewById(R.id.desctextview);
            txt3 = (TextView) itemView.findViewById(R.id.pricetxtview);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingbar);


            context = itemView.getContext();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int itemPosition = getLayoutPosition();
                    // Toast.makeText(context, "" + itemPosition, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ProductDetailed.class);
//                    intent.putExtra("name",""+android.get(itemPosition).getOffer());
                    intent.putExtra("product id", "" + al.get(itemPosition).getId());
                    intent.putExtra("image", "" + al.get(itemPosition).getProductImages());
                    Log.d("saurabh", "item  position" + itemPosition);
                    context.startActivity(intent);
                }
            });


        }
    }
}

