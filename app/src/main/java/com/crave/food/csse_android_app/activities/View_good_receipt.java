package com.crave.food.csse_android_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.adapters.ReceiptAdapter;
import com.crave.food.csse_android_app.models.Receipt;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class View_good_receipt extends AppCompatActivity {

    DatabaseReference databaseRecept;
    RecyclerView recyclerView;
    ReceiptAdapter receiptAdapter;
    ArrayList<Receipt> receiptList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_good_receipt);
        databaseRecept = FirebaseDatabase.getInstance().getReference("Receipt");
        recyclerView = findViewById(R.id.recyclerView);
        receiptList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseRecept.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                receiptList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    Receipt receipt = dataSnapshot.getValue(Receipt.class);
                    receiptList.add(receipt);
                }

                receiptAdapter = new ReceiptAdapter(View_good_receipt.this,receiptList);
                recyclerView.setAdapter(receiptAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}