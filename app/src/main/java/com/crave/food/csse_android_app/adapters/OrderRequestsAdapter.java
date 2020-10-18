package com.crave.food.csse_android_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.listners.OnOrderClicked;
import com.crave.food.csse_android_app.models.Order;

import java.util.ArrayList;

public class OrderRequestsAdapter extends RecyclerView.Adapter<OrderRequestsAdapter.MyViewHolder>
{
    private Context context;
    private ArrayList<Order> list;
    private OnOrderClicked orderClicked;


    public OrderRequestsAdapter(Context context, ArrayList<Order> list, OnOrderClicked orderClicked)
    {
        this.context = context;
        this.list = list;
        this.orderClicked= orderClicked;


    }

    @NonNull
    @Override
    public OrderRequestsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item_supplier_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRequestsAdapter.MyViewHolder holder, int position)
    {

        final Order order = list.get(position);

        holder.ref.setText( order.getRefNo());
        holder.status.setText("" + order.getStatus());
        holder.from.setText("" + order.getCompanyName());

        holder.date.setText(order.getDateRequired());
        holder.name.setText(order.getProduct().getProduct()  + " -");
        holder.qty.setText(order.getQuantity() + " " + order.getUnit());
        holder.ConstructionCompanyName.setText(order.getCompanyName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderClicked.orderClick(order);
            }
        });


    }


    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        private TextView ref,name,date,qty,ConstructionCompanyName,from,status;


        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ref = itemView.findViewById(R.id.ref);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            qty = itemView.findViewById(R.id.qty);
            from = itemView.findViewById(R.id.from);
            status = itemView.findViewById(R.id.status);

            ConstructionCompanyName = itemView.findViewById(R.id.ConstructionCompanyName);


        }
    }



}
