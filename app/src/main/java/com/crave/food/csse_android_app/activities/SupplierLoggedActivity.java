package com.crave.food.csse_android_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.crave.food.csse_android_app.InvoicesMain;
import com.crave.food.csse_android_app.OrderViewSitemanager;
import com.crave.food.csse_android_app.OrderViewSupplier;
import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.config.LoginState;

public class SupplierLoggedActivity extends AppCompatActivity {

    Button add_receipt,view_btn;
    Button shanu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_logged);

        add_receipt = findViewById(R.id.add_reciept);
        Button buttonOrder= findViewById(R.id.btn_viewOrdersSupplier);


        add_receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddReceipt();
            }
        });

        shanu = findViewById(R.id.btn_shanu);
        shanu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSupplier();
            }
        });
    }
    public void openAddReceipt(){
        Intent intent = new Intent(this,add_good_receipt.class);
        startActivity(intent);
    }

    public void ViewOrdersSupplier(View view){
        Intent intent = new Intent(SupplierLoggedActivity.this, OrderViewSupplier.class);
        startActivity(intent);
    }

    public void viewSupplier()
    {
        Intent intent = new Intent(SupplierLoggedActivity.this, InvoicesMain.class);
        startActivity(intent);
    }

    public void Logout(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(SupplierLoggedActivity.this);
        builder.setTitle("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                LoginState.getInstance().saveUser(SupplierLoggedActivity.this,null);
                Intent intent = new Intent(SupplierLoggedActivity.this,SplashScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }
}