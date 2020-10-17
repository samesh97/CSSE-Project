package com.crave.food.csse_android_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.crave.food.csse_android_app.InvoicesMain;
import com.crave.food.csse_android_app.OrderViewSitemanager;
import com.crave.food.csse_android_app.R;

public class SupplierLoggedActivity extends AppCompatActivity {

    Button add_receipt,view_btn;
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
    }
    public void openAddReceipt(){
        Intent intent = new Intent(this,add_good_receipt.class);
        startActivity(intent);
    }


    public void viewSupplier(View view)
    {
        Intent intent = new Intent(SupplierLoggedActivity.this, InvoicesMain.class);
        startActivity(intent);
    }
}