package com.crave.food.csse_android_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crave.food.csse_android_app.activities.Login;
import com.crave.food.csse_android_app.config.LoginState;
import com.crave.food.csse_android_app.models.Product;
import com.crave.food.csse_android_app.models.Supplier;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderViewSitemanager extends AppCompatActivity {

    DatabaseReference reference;

    private ArrayAdapter<CharSequence> adapter;

    String statusTxt ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view_sitemanager);


        Intent intentGet =getIntent();

        Button test = (Button) findViewById(R.id.Test);

        //making the dropdown list for sort order status
        Spinner status =(Spinner)findViewById(R.id.spinner_orderStatus);
        adapter = ArrayAdapter.createFromResource(this, R.array.orderStatus_sitemanager, R.layout.layout_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);

        status.setSelection(0);

        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                statusTxt = adapter.getItem(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

   public void onClickTest(View view){
        reference= FirebaseDatabase.getInstance().getReference().child("Companies").child("691446458").child("Products");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {

                    long count= snapshot.getChildrenCount();
                    final ArrayList<Product> products = new ArrayList<Product>();

                    for(final DataSnapshot snapshot1:snapshot.getChildren())
                    {
                        final Product product = snapshot1.getValue(Product.class);
                        products.add(product);
                        Log.d("DebugKey","" +product.getProductId());

                    }
                }
                else
                {
                    Log.d("DebugKey","Path doesnt exists");
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onClickAddButton(View view){
        Intent intent = new Intent(this,OrderPlaceSitemanager.class);
        startActivity(intent);
    }

    public void showToast(String message)
    {
        //show the toast
        if(message != null && !message.equals(""))
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}