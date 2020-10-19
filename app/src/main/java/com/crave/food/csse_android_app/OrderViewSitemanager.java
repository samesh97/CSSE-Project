package com.crave.food.csse_android_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.crave.food.csse_android_app.activities.OrderUpdateSitemanager;
import com.crave.food.csse_android_app.activities.PaymentView;
import com.crave.food.csse_android_app.adapters.OrderAdapter;
import com.crave.food.csse_android_app.config.LoginState;
import com.crave.food.csse_android_app.listners.OnOrderClicked;
import com.crave.food.csse_android_app.models.Manager;
import com.crave.food.csse_android_app.models.Order;
import com.crave.food.csse_android_app.models.Product;
import com.crave.food.csse_android_app.models.Supplier;
import com.crave.food.csse_android_app.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderViewSitemanager extends AppCompatActivity  {

    DatabaseReference reference;
    private ArrayAdapter<CharSequence> adapter;
    private ProgressDialog dialog;
    private String statusTxt ="All orders";
    private ArrayList<Order> orderList;
    private RecyclerView recyclerView;
    private Context context;

    private OrderAdapter orderAdapter;

    public static Order editingOrder;
    public static Order deletingOrder;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK)
        {
            getOrders(statusTxt);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view_sitemanager);

        dialog = new ProgressDialog(OrderViewSitemanager.this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);

        orderList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
/*
        public void deleteOrder(Order order){
            DatabaseReference delete_Order = FirebaseDatabase.getInstance().getReference("Orders").child(order.getOrderId());
            delete_Order.removeValue();

        }

 */
        orderAdapter = new OrderAdapter(OrderViewSitemanager.this, orderList, new OnOrderClicked() {


            @Override
            public void orderClick(Order order) {

                editingOrder = order;
                Intent intent = new Intent(OrderViewSitemanager.this, OrderUpdateSitemanager.class);
                startActivityForResult(intent,100);

            }

            @Override
            public void orderDeleteClick(final Order order)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderViewSitemanager.this);
                builder.setTitle("Are you sure you want to delete?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        deletingOrder = order;

                        //Toast.makeText(OrderViewSitemanager.this, "deleted " + order.getCompanyName(), Toast.LENGTH_SHORT).show();
                        DatabaseReference delete_Order = FirebaseDatabase.getInstance().getReference("Orders").child(order.getOrderId());
                        delete_Order.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if (task.isSuccessful()) {

                                    Toast.makeText(OrderViewSitemanager.this, "deleted " , Toast.LENGTH_SHORT).show();
                                    getOrders(statusTxt);


                                } else {
                                    Toast.makeText(OrderViewSitemanager.this, "Not deleted " , Toast.LENGTH_SHORT).show();
                                }


                            }
                        });;
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();



            }


        });


        //    orderAdapter = new OrderAdapter(OrderViewSitemanager.this, orderList, new OnOrderClicked() {
           // @Override
           // public void orderClick(Order order) {


      //      } });


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

      // getOrders(statusTxt);

    }

  //  public void deleteOrder(Order order) {
  //      DatabaseReference delete_Order = FirebaseDatabase.getInstance().getReference("Orders");
    //     delete_Order.removeValue();
  //  }

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
                    User user = LoginState.getInstance().getUser(OrderViewSitemanager.this);
                    Manager manager = (Manager) user;
                    for (DataSnapshot dss : snapshot.getChildren())
                    {
                        Order order = dss.getValue(Order.class);
                        if(order != null & manager != null && order.getCompanyId().equals(manager.getCompanyId()))
                        {
                            if(status.equals("All orders"))
                            {
                                orderList.add(order);
                            }
                            else if(order.getStatus().equals(status))
                            {
                                orderList.add(order);
                            }
                        }

                        orderAdapter.notifyDataSetChanged();
                    }
                }
                else
                {
                    Toast.makeText(OrderViewSitemanager.this, "No orders found", Toast.LENGTH_SHORT).show();
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

   // protected void onResume(){
  //      super.onResume();
  //      getOrders();
 //   }

    public void onClickAddButton(View view)
    {
        Intent intent = new Intent(this,OrderPlaceSitemanager.class);
        startActivity(intent);
    }

    public void DeliveryButtonClicked(View view)
    {
   //     Intent intent = new Intent(this,OrderPlaceSitemanager.class);
   //     startActivity(intent);
    }

    public void PaymentButtonClicked(View view)
    {
        Intent intent = new Intent(this, PaymentView.class);
        startActivity(intent);
    }

}