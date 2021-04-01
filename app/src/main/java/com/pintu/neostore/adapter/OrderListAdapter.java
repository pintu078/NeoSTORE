package com.pintu.neostore.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pintu.neostore.R;
import com.pintu.neostore.model.ProductList_Data;
import com.pintu.neostore.view.drawer.order.OrderDetails;
import com.pintu.neostore.model.order.Order_List.Datum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> implements Filterable {


    List<Datum> al;
    List<Datum> al_filter;
    Context context;

    public OrderListAdapter(Context context, List<Datum> al) {
        this.context = context;
        this.al = al;
        this.al_filter = new ArrayList<>(al);
    }

    @Override
    public OrderListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_order_list_row, parent, false);
        OrderListAdapter.MyViewHolder vh = new OrderListAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final OrderListAdapter.MyViewHolder holder, final int position) {
        holder.id.setText(al.get(position).getId().toString());
        holder.date.setText(al.get(position).getCreated());
        holder.price.setText("₹. " + al.get(position).getCost());

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

            List<Datum> filteredList = new ArrayList<>();

            if(constraint.toString().isEmpty()){
                filteredList.addAll(al_filter);
            }else{
                for(Datum list : al_filter){
                    if(list.getId().toString().contains(constraint.toString())){
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
            al.addAll((Collection<? extends Datum>) results.values);
            notifyDataSetChanged();
        }
    };


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, date, price;


        public MyViewHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.tv_orderID_no);
            date = (TextView) itemView.findViewById(R.id.tv_order_date);
            price = (TextView) itemView.findViewById(R.id.tv_price);

            context = itemView.getContext();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int itemPosition = getLayoutPosition();
                    Intent intent = new Intent(context, OrderDetails.class);
                    intent.putExtra("order id", "" + al.get(itemPosition).getId().toString());
                    Log.d("saurabh", "item  position" + itemPosition);
                    context.startActivity(intent);
                }
            });
        }
    }
}
