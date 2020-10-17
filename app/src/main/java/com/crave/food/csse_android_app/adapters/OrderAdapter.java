package com.crave.food.csse_android_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.models.Order;
import com.crave.food.csse_android_app.models.Product;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder>
{
    private Context context;
    private ArrayList<Order> list;

    public OrderAdapter(Context context,ArrayList<Order> list)
    {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position)
    {

        Order order = list.get(position);

        holder.ref.setText(order.getRefNo());
        holder.company.setText(order.getProduct() + " -");
        holder.cost.setText("" + order.getPriceExpected());
        holder.date.setText(order.getDateRequired());
        holder.name.setText(order.getCompanyName());
        holder.status.setText(order.getStatus());
        holder.qty.setText(order.getQuantity() + " " + order.getUnit());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView ref,name,cost,company,date,qty,status;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ref = itemView.findViewById(R.id.ref);
            name = itemView.findViewById(R.id.name);
            cost = itemView.findViewById(R.id.cost);
            company = itemView.findViewById(R.id.company);
            date = itemView.findViewById(R.id.date);
            qty = itemView.findViewById(R.id.qty);
            status = itemView.findViewById(R.id.status);
        }
    }
}
