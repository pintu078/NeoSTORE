package com.pintu.neostore.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pintu.neostore.R;
import com.pintu.neostore.drawer.order.OrderDetails;
import com.pintu.neostore.model.order.Order_List.Datum;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {


    List<Datum> al;
    Context context;

    public OrderListAdapter(Context context, List<Datum> al) {
        this.context = context;
        this.al = al;
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
