package com.crave.food.csse_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderViewSitemanager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view_sitemanager);

        Intent intent=getIntent();

        //String company = intent.getStringExtra(OrderPlaceSitemanager.companyNameKey);


    }
}