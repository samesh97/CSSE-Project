package com.crave.food.csse_android_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.crave.food.csse_android_app.activities.SupplierLoggedActivity;
import com.crave.food.csse_android_app.adapters.InvoicesAdapter;
import com.crave.food.csse_android_app.adapters.OrderAdapter;
import com.crave.food.csse_android_app.models.Invoice;
import com.crave.food.csse_android_app.models.Order;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;



public class InvoicesMain extends AppCompatActivity {
    DatabaseReference reference;
    private ArrayAdapter<CharSequence> adapter;
    private ArrayList<Invoice> invoicesList;
    private RecyclerView recyclerView;
    private InvoicesAdapter invoicesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoices_main);

        invoicesList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerviewInvoice);
        invoicesAdapter = new InvoicesAdapter(InvoicesMain.this,invoicesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(InvoicesMain.this));
        recyclerView.setAdapter(invoicesAdapter);



    }

    public void addNewInvoiceBtnClick(View view)
    {
        Intent intent = new Intent(InvoicesMain.this, AddInvoice.class);
        startActivity(intent);
    }
}