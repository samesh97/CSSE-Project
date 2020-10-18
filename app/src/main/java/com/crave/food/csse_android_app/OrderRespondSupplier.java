package com.crave.food.csse_android_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.crave.food.csse_android_app.models.Order;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderRespondSupplier extends AppCompatActivity {


   DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_respond_supplier);

        Order order = OrderViewSupplier.selectedOrder;

        TextView date= findViewById(R.id.orderedDatep);
        TextView refNo = findViewById(R.id.refNop);
        TextView Company = findViewById(R.id.companyp);
        TextView phone = findViewById(R.id.contactNop);
        TextView product = findViewById(R.id.productp);
        TextView quantity = findViewById(R.id.quantityp);
        TextView reqDate = findViewById(R.id.dateRequiredp);
        TextView deliveryAddress = findViewById(R.id.deliveryAddressp);
        TextView price = findViewById(R.id.pricep);
        TextView notes = findViewById(R.id.notesp);

        date.setText("" + order.getDateCurrent());
        refNo.setText("" + order.getRefNo());
        Company.setText("" + order.getCompanyName());
        phone.setText("" + order.getPhone());
        product.setText("" + order.getProduct().getProduct());
        quantity.setText("" + order.getQuantity());
        reqDate.setText("" + order.getDateRequired());
        deliveryAddress.setText("" + order.getSiteAddress());
        price.setText("" + order.getPriceExpected());
        notes.setText("" + order.getNotes());

        reference = FirebaseDatabase.getInstance().getReference("Orders").child(order.getOrderId()).child("status");


    }
    public void updated()
    {
        setResult(RESULT_OK);
        finish();
    }
    public void updateOrderStatus(final String status)
    {
        reference.setValue(status, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref)
            {
                if(error != null)
                {
                    Toast.makeText(OrderRespondSupplier.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(OrderRespondSupplier.this, "Order was successfully " + status, Toast.LENGTH_SHORT).show();
                    updated();
                }
            }
        });
    }
    public void Accepted(View view)
    {
        updateOrderStatus("Accepted");
    }

    public void Rejected(View view)
    {
        updateOrderStatus("Rejected");
    }
}