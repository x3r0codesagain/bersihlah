package com.myapp.bersihlah.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.bersihlah.R;
import com.myapp.bersihlah.model.OrderHelper;


import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {

    Context context;
    List<OrderHelper> orderItems;

    public ItemRecyclerAdapter(Context context, List<OrderHelper> orderItems) {
        this.context = context;
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {


        OrderHelper order = orderItems.get(position);

        holder.cat.setText(order.getType());
        holder.price.setText(order.getPrice());
        holder.status.setText("Completed");
        holder.date.setText(order.getDate());




    }



    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder{


        TextView cat, date, status, price;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cat = itemView.findViewById(R.id.orderHistoryCategory);
            date = itemView.findViewById(R.id.orderHistoryDate);
            price = itemView.findViewById(R.id.orderHistoryPrice);
            status = itemView.findViewById(R.id.orderHistoryStatus);

        }
    }

}
