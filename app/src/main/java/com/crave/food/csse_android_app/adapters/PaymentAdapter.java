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
import com.crave.food.csse_android_app.models.Payment;

import java.util.ArrayList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Payment> list;

    public PaymentAdapter(Context context,ArrayList<Payment> list)
    {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public PaymentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_payment_item_list,parent,false);
        return new PaymentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Payment payment = list.get(position);

        holder.heading.setText("" + payment.getHeading());
        holder.count.setText("" + payment.getCount());
        holder.balance.setText("" + payment.getBalance());
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView heading, count, balance;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            heading = itemView.findViewById(R.id.textViewHeading);
            count = itemView.findViewById(R.id.textViewCount);
            balance = itemView.findViewById(R.id.textViewBalance);
        }
    }
}
