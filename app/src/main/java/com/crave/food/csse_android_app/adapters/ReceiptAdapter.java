package com.crave.food.csse_android_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.models.Receipt;

import java.util.ArrayList;

public class ReceiptAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Receipt> models;


    public ReceiptAdapter(Context c, ArrayList<Receipt> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_row,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.cname.setText(models.get(position).getCompanyName());
        holder.product.setText(models.get(position).getPrduct());
        holder.refNo.setText(models.get(position).getRefNo());
        holder.qty.setText(models.get(position).getQty());
        holder.description.setText(models.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}

class MyHolder extends RecyclerView.ViewHolder {

        TextView cname,product,qty,description,refNo;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            this.cname = itemView.findViewById(R.id.cname);
            this.product = itemView.findViewById(R.id.product);
            this.qty = itemView.findViewById(R.id.qty);
            this.description = itemView.findViewById(R.id.dis);
            this.refNo = itemView.findViewById(R.id.refno);


        }
}
