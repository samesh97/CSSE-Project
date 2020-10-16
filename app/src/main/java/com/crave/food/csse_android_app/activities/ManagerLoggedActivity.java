package com.crave.food.csse_android_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.crave.food.csse_android_app.OrderViewSitemanager;
import com.crave.food.csse_android_app.R;

public class ManagerLoggedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_logged);
    }

    public void ViewOrders(View view)
    {
        Intent intent = new Intent(ManagerLoggedActivity.this, OrderViewSitemanager.class);
        startActivity(intent);
    }
}