package com.crave.food.csse_android_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.models.Receipt;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_good_receipt extends AppCompatActivity {

    EditText cname,rnumber,product,qty,dis;
    Button add_receipt,view_btn;
    DatabaseReference databaseRecept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_good_receipt);

        databaseRecept = FirebaseDatabase.getInstance().getReference("Receipt");
        cname = findViewById(R.id.cname);
        rnumber = findViewById(R.id.Rnumber);
        product = findViewById(R.id.product);
        qty = findViewById(R.id.qty);
        dis = findViewById(R.id.dis);
        add_receipt = findViewById(R.id.add_btn);
        view_btn = findViewById(R.id.viewBtn);
        add_receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addReceipt();
            }
        });
        view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVIewRecipt();
            }
        });

    }
    private void addReceipt(){
        String cmopanyName = cname.getText().toString();
        String Rno = rnumber.getText().toString();
        String product1 = product.getText().toString();
        String qty1 = qty.getText().toString();
        String dis1 = dis.getText().toString();

        if (cmopanyName.equals(null) || Rno.equals(null) || product1.equals(null)){

            String id = databaseRecept.push().getKey();
            Receipt receipt = new Receipt(id,cmopanyName,Rno,product1,qty1,dis1);
            databaseRecept.child(id).setValue(receipt);
            Toast.makeText(this,"Successfully Added!",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Please fill informations!",Toast.LENGTH_LONG).show();
        }

    }
    public void openVIewRecipt(){
        Intent intent = new Intent(this,View_good_receipt.class);
        startActivity(intent);
    }

}