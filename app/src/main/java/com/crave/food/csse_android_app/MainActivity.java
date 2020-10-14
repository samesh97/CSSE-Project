package com.crave.food.csse_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.crave.food.csse_android_app.activities.SupplierRegistration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Reg(View view)
    {
        Intent intent = new Intent(MainActivity.this, SupplierRegistration.class);
        startActivity(intent);
    }
}