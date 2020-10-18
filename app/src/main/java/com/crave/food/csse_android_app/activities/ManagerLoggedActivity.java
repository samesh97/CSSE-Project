package com.crave.food.csse_android_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crave.food.csse_android_app.InvoicesMain;
import com.crave.food.csse_android_app.OrderViewSitemanager;
import com.crave.food.csse_android_app.OrderViewSupplier;
import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.config.LoginState;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ManagerLoggedActivity extends AppCompatActivity {

    Button receptManage;
    Button payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_logged);

        payment = findViewById(R.id.button7);
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

    public void viewPayment(View view)
    {
        Intent intent = new Intent(ManagerLoggedActivity.this,PaymentView.class);
        startActivity(intent);
    }


    public void Logout(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ManagerLoggedActivity.this);
        builder.setTitle("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                LoginState.getInstance().saveUser(ManagerLoggedActivity.this,null);
                Intent intent = new Intent(ManagerLoggedActivity.this,SplashScreen.class);
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