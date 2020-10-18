package com.crave.food.csse_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.crave.food.csse_android_app.models.Invoice;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddInvoice extends AppCompatActivity {

    EditText invoiceId,cost;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice);

        reference = FirebaseDatabase.getInstance().getReference("Suppliers");
        invoiceId = findViewById(R.id.editTextInvoiceId);
        cost = findViewById(R.id.editTextCost);
    }

    private void addInvoice(){
        String invoiceid = invoiceId.getText().toString();
        String cost1 = cost.getText().toString();


        String id = reference.push().getKey();
        Invoice invoice = new Invoice(invoiceid, cost1);
        reference.child(id).setValue(invoice);
        Toast.makeText(this,"Successfully Added!",Toast.LENGTH_LONG).show();
    }
}