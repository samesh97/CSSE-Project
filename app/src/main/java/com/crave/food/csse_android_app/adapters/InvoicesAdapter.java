package com.crave.food.csse_android_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.models.Invoice;

import java.util.ArrayList;

public class InvoicesAdapter extends RecyclerView.Adapter<InvoicesAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Invoice> list;

    public InvoicesAdapter(Context context,ArrayList<Invoice> list)
    {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InvoicesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_invoices_item_list,parent,false);
        return new InvoicesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Invoice invoice = list.get(position);

        holder.id.setText("" + invoice.getId());
        holder.cost.setText("" + invoice.getCost());
        holder.price.setText("" + invoice.getPrice());
    }


    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView id, cost, price;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            cost = itemView.findViewById(R.id.textViewCost);
            price = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
