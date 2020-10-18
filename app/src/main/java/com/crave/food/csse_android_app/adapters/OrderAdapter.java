package com.crave.food.csse_android_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crave.food.csse_android_app.OrderPlaceSitemanager;
import com.crave.food.csse_android_app.OrderViewSitemanager;
import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.listners.OnOrderClicked;
import com.crave.food.csse_android_app.models.Order;
import com.crave.food.csse_android_app.models.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder>
{
    private Context context;
    private ArrayList<Order> list;
    private OnOrderClicked orderClicked;



    public OrderAdapter(Context context,ArrayList<Order> list,OnOrderClicked orderClicked)
    {
        this.context = context;
        this.list = list;
        this.orderClicked= orderClicked;

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

        final Order order = list.get(position);

        holder.ref.setText( order.getRefNo());
        holder.company.setText( order.getCompanyName());
        holder.cost.setText(order.getPriceExpected() + " LKR");
        holder.date.setText(order.getDateRequired());
        holder.name.setText(order.getProduct().getProduct()  + " -");
        holder.status.setText(order.getStatus());
        holder.qty.setText(order.getQuantity() + " " + order.getUnit());

        if(order.getStatus().equals("Pending") || order.getStatus().equals("Placed"))
        {
            holder.btn_EditOrder.setVisibility(View.VISIBLE);
            holder.btn_DeleteOrder.setVisibility(View.VISIBLE);

            holder.btn_EditOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    orderClicked.orderClick(order);

                }
            });

            holder.btn_DeleteOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    orderClicked.orderDeleteClick(order);
                }
            });
        }
        else
        {
            holder.btn_EditOrder.setVisibility(View.INVISIBLE);
            holder.btn_DeleteOrder.setVisibility(View.INVISIBLE);
        }



    }


    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView ref,name,cost,company,date,qty,status,ConstructionCompanyName;
        private ImageView btn_EditOrder;
        private ImageView btn_DeleteOrder;


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


            btn_EditOrder = itemView.findViewById(R.id.btn_EditOrder);
            btn_DeleteOrder=itemView.findViewById(R.id.btn_DeleteOrder);
        }
    }



}
