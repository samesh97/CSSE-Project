package com.crave.food.csse_android_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
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
import com.crave.food.csse_android_app.adapters.OrderAdapter;
import com.crave.food.csse_android_app.config.LoginState;
import com.crave.food.csse_android_app.models.Manager;
import com.crave.food.csse_android_app.models.Order;
import com.crave.food.csse_android_app.models.Product;
import com.crave.food.csse_android_app.models.Supplier;
import com.crave.food.csse_android_app.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderViewSitemanager extends AppCompatActivity {

    DatabaseReference reference;
    private ArrayAdapter<CharSequence> adapter;
    private ProgressDialog dialog;
    private String statusTxt ="All orders";
    private ArrayList<Order> orderList;
    private RecyclerView recyclerView;

    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view_sitemanager);

        dialog = new ProgressDialog(OrderViewSitemanager.this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);

        orderList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        orderAdapter = new OrderAdapter(OrderViewSitemanager.this,orderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderViewSitemanager.this));
        recyclerView.setAdapter(orderAdapter);



        Spinner status = findViewById(R.id.spinner_orderStatus);
        adapter = ArrayAdapter.createFromResource(this, R.array.orderStatus_sitemanager, R.layout.layout_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);


        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                statusTxt = adapter.getItem(i).toString();
                getOrders(statusTxt);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       getOrders(statusTxt);

    }

   public void getProducts()
   {

//       User user = LoginState.getUser(OrderViewSitemanager.this);
//
//       if(user instanceof Manager)
//       {
//           productList.clear();
//           dialog.show();
//           Manager manager = (Manager) user;
//
//           reference= FirebaseDatabase.getInstance().getReference().child("Companies").child(manager.getCompanyId()).child("Products");
//           reference.addValueEventListener(new ValueEventListener() {
//               @Override
//               public void onDataChange(@NonNull DataSnapshot snapshot)
//               {
//                   if(snapshot.exists())
//                   {
//
//                       for(final DataSnapshot snapshot1 : snapshot.getChildren())
//                       {
//                           final Product product = snapshot1.getValue(Product.class);
//                           productList.add(product);
//                       }
//                   }
//                   else
//                   {
//                       Toast.makeText(OrderViewSitemanager.this, "No Products Found!", Toast.LENGTH_SHORT).show();
//                   }
//                   dialog.dismiss();
//
//
//
//               }
//
//               @Override
//               public void onCancelled(@NonNull DatabaseError error) {
//                   dialog.dismiss();
//               }
//           });
//       }
//       else
//       {
//           Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
//       }


    }
    public void getOrders(final String status)
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
                        if(status.equals("All orders"))
                        {
                            orderList.add(order);
                        }
                        else if(order != null && order.getStatus().equals(status))
                        {
                            orderList.add(order);
                        }
                        orderAdapter.notifyDataSetChanged();
                    }
                }
                else
                {
                    Toast.makeText(OrderViewSitemanager.this, "No orders found", Toast.LENGTH_SHORT).show();
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

    public void onClickAddButton(View view)
    {
        Intent intent = new Intent(this,OrderPlaceSitemanager.class);
        startActivity(intent);
    }
}