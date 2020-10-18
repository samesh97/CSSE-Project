package com.crave.food.csse_android_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.crave.food.csse_android_app.activities.View_good_receipt;
import com.crave.food.csse_android_app.adapters.OrderAdapter;
import com.crave.food.csse_android_app.adapters.OrderRequestsAdapter;
import com.crave.food.csse_android_app.config.LoginState;
import com.crave.food.csse_android_app.listners.OnOrderClicked;
import com.crave.food.csse_android_app.models.Order;
import com.crave.food.csse_android_app.models.Supplier;
import com.crave.food.csse_android_app.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderViewSupplier extends AppCompatActivity {

    DatabaseReference reference;
    private ArrayAdapter<CharSequence> adapter;
    private ProgressDialog dialog;

    private ArrayList<Order> orderList;
    private RecyclerView recyclerView;

    private OrderRequestsAdapter orderAdapter;

    public static Order selectedOrder = null;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            getOrders();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view_supplier);

        dialog = new ProgressDialog(OrderViewSupplier.this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);

        orderList = new ArrayList<>();


        recyclerView = findViewById(R.id.recyclerView);

        orderAdapter = new OrderRequestsAdapter(OrderViewSupplier.this, orderList, new OnOrderClicked() {
            @Override
            public void orderClick(Order order)
            {
                selectedOrder = order;
                Intent intent = new Intent(OrderViewSupplier.this,OrderRespondSupplier.class);
                startActivityForResult(intent,100);
            }

            @Override
            public void orderDeleteClick(Order order) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(OrderViewSupplier.this));
        recyclerView.setAdapter(orderAdapter);



        Spinner status =(Spinner)findViewById(R.id.spinner_order);
        adapter = ArrayAdapter.createFromResource(this, R.array.orderStatus_supplier, R.layout.layout_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);

    /*    status.setSelection(0);

        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getOrders();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

     */
        getOrders();
    }

    public void getOrders()
    {
        orderList.clear();
        dialog.show();

        reference = FirebaseDatabase.getInstance().getReference("Orders");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    for (DataSnapshot dss : snapshot.getChildren())
                    {
                        Order order = dss.getValue(Order.class);

                        User user = LoginState.getInstance().getUser(OrderViewSupplier.this);
                        if(user instanceof Supplier)
                        {
                            Supplier supplier = (Supplier) user;
                            if(supplier.getSupplierId().equals(order.getSupplier().getSupplierId()) && !(order.getStatus().equals("Pending") || order.getStatus().equals("Declined")))
                            {
                                orderList.add(order);
                                //
                            }
                        }
                       orderAdapter.notifyDataSetChanged();
                    }
                }
                else
                {
                    Toast.makeText(OrderViewSupplier.this, "No orders found", Toast.LENGTH_SHORT).show();
                    orderAdapter.notifyDataSetChanged();
                }

                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                dialog.dismiss();
            }
        });
    }

    public void InvoiceButtonClicked(View view){
        Intent intent = new Intent(OrderViewSupplier.this,InvoicesMain.class);
        startActivity(intent);
    }

    public void GoodReceiptButtonClicked(View view){
        Intent intent = new Intent(this, View_good_receipt.class);
        startActivity(intent);
    }


}