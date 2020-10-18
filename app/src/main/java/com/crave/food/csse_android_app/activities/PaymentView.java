package com.crave.food.csse_android_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.crave.food.csse_android_app.OrderViewSitemanager;
import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.adapters.PaymentAdapter;
import com.crave.food.csse_android_app.models.Payment;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class PaymentView extends AppCompatActivity {
//    DatabaseReference reference;
//    private ArrayList<Payment> paymentList;
//    private RecyclerView recyclerView;
//    private PaymentAdapter paymentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_view);

//        paymentList = new ArrayList<>();
//
//        recyclerView = findViewById(R.id.recyclerviewPayment);
//        paymentAdapter = new PaymentAdapter(PaymentView.this,paymentList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(PaymentView.this));
//        recyclerView.setAdapter(paymentAdapter);


    }

    public void OrderButtonClicked(View view)
    {
        Intent intent = new Intent(this, OrderViewSitemanager.class);
        startActivity(intent);
    }


}