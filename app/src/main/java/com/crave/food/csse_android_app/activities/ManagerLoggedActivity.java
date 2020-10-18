package com.crave.food.csse_android_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.crave.food.csse_android_app.InvoicesMain;
import com.crave.food.csse_android_app.OrderViewSitemanager;
import com.crave.food.csse_android_app.R;

public class ManagerLoggedActivity extends AppCompatActivity {

    Button receptManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_logged);

        receptManage = findViewById(R.id.receipt_manage);
        receptManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewManageReceipt();
            }
        });
    }

    public void ViewOrders(View view)
    {
        Intent intent = new Intent(ManagerLoggedActivity.this, OrderViewSitemanager.class);
        startActivity(intent);
    }
    public void viewManageReceipt()
    {
        Intent intent = new Intent(ManagerLoggedActivity.this, Manage_receipt.class);
        startActivity(intent);
    }


}