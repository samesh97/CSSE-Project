package com.crave.food.csse_android_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.crave.food.csse_android_app.InvoicesMain;
import com.crave.food.csse_android_app.OrderViewSitemanager;
import com.crave.food.csse_android_app.PaymentView;
import com.crave.food.csse_android_app.R;

public class SupplierLoggedActivity extends AppCompatActivity {

    Button add_receipt,view_btn;
    Button btn_Shanu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_logged);

        add_receipt = findViewById(R.id.add_reciept);

        add_receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddReceipt();
            }
        });

        btn_Shanu = findViewById(R.id.btnSupplierShanuImpl);
        btn_Shanu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewInvoices();
            }
        });
    }
    public void openAddReceipt(){
        Intent intent = new Intent(this,add_good_receipt.class);
        startActivity(intent);
    }

    public void viewInvoices(){
        Intent intent = new Intent(this, PaymentView.class);
        startActivity(intent);
    }

    public void viewSupplier()
    {
        Intent intent = new Intent(this, InvoicesMain.class);
        startActivity(intent);
    }
}