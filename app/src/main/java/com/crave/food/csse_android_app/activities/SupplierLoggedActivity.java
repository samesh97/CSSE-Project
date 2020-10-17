package com.crave.food.csse_android_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.crave.food.csse_android_app.InvoicesMain;
import com.crave.food.csse_android_app.OrderViewSitemanager;
import com.crave.food.csse_android_app.R;

public class SupplierLoggedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_logged);
    }


    public void viewSupplier(View view)
    {
        Intent intent = new Intent(SupplierLoggedActivity.this, InvoicesMain.class);
        startActivity(intent);
    }
}