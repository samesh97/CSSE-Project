package com.crave.food.csse_android_app.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crave.food.csse_android_app.OrderPlaceSitemanager;
import com.crave.food.csse_android_app.OrderViewSitemanager;
import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.adapters.ProductSpinnerAdapter;
import com.crave.food.csse_android_app.adapters.SupplierSpinnerAdapter;
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

public class OrderUpdateSitemanager extends AppCompatActivity {



    private Spinner product;
    private Spinner supplier;
    private ProductSpinnerAdapter adapter;
    private SupplierSpinnerAdapter supplierSpinnerAdapter;


    private ProgressDialog progressBar;
    private DatabaseReference reference;

    private ProgressDialog dialog;
    private ArrayList<Product> productList;

    private EditText companyName;
    private EditText phone;
    private EditText quantity;
    private EditText dateRequired;
    private EditText siteAddress;
    private EditText price;
    private EditText notes;
    private TextView status;

    private Button btn_placeOrder;
    private Product selectedProduct = null;
    private Supplier selectedSupplier = null;

    private Order updatingOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_update_sitemanager);

        updatingOrder = OrderViewSitemanager.editingOrder;

        dialog = new ProgressDialog(OrderUpdateSitemanager.this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);

        dialog.show();

        productList = new ArrayList<>();

        //making the 2 drop down lists for product and suppliers
        product =  findViewById(R.id.spinner_product);
        supplier = findViewById(R.id.spinner_supplier);

        companyName = findViewById(R.id.editText_companyName);
        phone = findViewById(R.id.editTextPhone);
        quantity = findViewById(R.id.editText_quantity);
        dateRequired = findViewById(R.id.editText_dateRequired);
        siteAddress = findViewById(R.id.editTextTextPostalAddress);
        price = findViewById(R.id.editText_price);
        notes = findViewById(R.id.editText_notes);
        status = findViewById(R.id.status);
       btn_placeOrder = findViewById(R.id.btn_placeOrder);


        companyName.setText(""+updatingOrder.getCompanyName());
        phone.setText(""+updatingOrder.getPhone());
        quantity.setText("" + updatingOrder.getQuantity());
        dateRequired.setText(""+updatingOrder.getDateRequired());
        siteAddress.setText(""+updatingOrder.getSiteAddress());
        price.setText(""+updatingOrder.getPriceExpected());
        notes.setText(""+updatingOrder.getNotes());
        status.setText(""+updatingOrder.getStatus());
        product.setPrompt(""+updatingOrder.getProduct());
        supplier.setPrompt(""+updatingOrder.getSupplier());



        adapter = new ProductSpinnerAdapter(OrderUpdateSitemanager.this, productList);
        product.setAdapter(adapter);

        product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                selectedProduct = productList.get(i);
                supplierSpinnerAdapter = new SupplierSpinnerAdapter(OrderUpdateSitemanager.this,selectedProduct);
                supplier.setAdapter(supplierSpinnerAdapter);

                supplier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        selectedSupplier = selectedProduct.getSuppliers().get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getProducts(updatingOrder);

    }
    public void getProducts(final Order order)
    {

        User user = LoginState.getInstance().getUser(OrderUpdateSitemanager.this);

        if(user instanceof Manager)
        {
            productList.clear();
            dialog.show();
            Manager manager = (Manager) user;

            reference= FirebaseDatabase.getInstance().getReference().child("Companies").child(manager.getCompanyId()).child("Products");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    if(snapshot.exists())
                    {

                        for(final DataSnapshot snapshot1 : snapshot.getChildren())
                        {
                            final Product product = snapshot1.getValue(Product.class);
                            productList.add(product);

                            adapter.notifyDataSetChanged();
                        }
                    }
                    else
                    {
                        Toast.makeText(OrderUpdateSitemanager.this, "No Products Found!", Toast.LENGTH_SHORT).show();
                    }
                    setProductSpinnerPosition(order);
                    dialog.dismiss();



                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    dialog.dismiss();
                }
            });
        }
        else
        {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }


    }


    public void updateClicked(View view){
        Order order=new Order();
        updateOrder(order);
       Toast.makeText(this,"Update button clicked",Toast.LENGTH_LONG).show();

   }


    public void updateOrder(Order order){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Orders").child(order.getOrderId());

        Order order1 = new Order();
        reference.setValue(order1);
        Toast.makeText(this,"Updated successfully",Toast.LENGTH_LONG).show();
    }
    public void setProductSpinnerPosition(Order order)
    {
        Product prod = order.getProduct();

        for(int i = 0; i < productList.size(); i++)
        {
            if(prod.getProductId() == productList.get(i).getProductId())
            {
                product.setSelection(i,true);
                setSupplierSpinnerPosition(order,productList.get(i));

            }
        }
    }
    public void setSupplierSpinnerPosition(Order order,Product product)
    {
        for(int i = 0; i < product.getSuppliers().size(); i++)
        {
            if(order.getSupplier().getSupplierId().equals(product.getSuppliers().get(i).getSupplierId()))
            {
                final int finalI = i;
                supplier.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        supplier.setSelection(finalI,true);
                    }
                },500);


            }
        }
    }
    public void updateOrder(View view)
    {
        String companyTxt = companyName.getText().toString();
        String phoneTxt = phone.getText().toString();
        String dateRequiredTxt = dateRequired.getText().toString();
        String siteAddressTxt = siteAddress.getText().toString();
        String notesTxt = notes.getText().toString();


        if(companyTxt.equals(""))
        {
            Toast.makeText(this, "Enter Company Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(phoneTxt.equals(""))
        {
            Toast.makeText(this, "Enter Contact Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if(selectedProduct == null)
        {
            Toast.makeText(this, "Please select a product", Toast.LENGTH_SHORT).show();
            return;
        }
        if(selectedSupplier == null)
        {
            Toast.makeText(this, "Please select a supplier", Toast.LENGTH_SHORT).show();
            return;
        }
        if(dateRequiredTxt.equals(""))
        {
            Toast.makeText(this, "Enter Required Date", Toast.LENGTH_SHORT).show();
            return;
        }
        if(siteAddressTxt.equals(""))
        {
            Toast.makeText(this, "Enter site address", Toast.LENGTH_SHORT).show();
            return;
        }
        int quantityValue;
        float priceValue;

        try
        {
            quantityValue = Integer.parseInt(quantity.getText().toString());
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Enter Quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        try
        {
            priceValue = Float.parseFloat(price.getText().toString());
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Enter price range!", Toast.LENGTH_SHORT).show();
            return;
        }

        dialog.show();

        updatingOrder.setCompanyName(companyTxt);
        updatingOrder.setPhone(phoneTxt);
        updatingOrder.setDateRequired(dateRequiredTxt);
        updatingOrder.setSiteAddress(siteAddressTxt);
        updatingOrder.setNotes(notesTxt);
        updatingOrder.setProduct(selectedProduct);
        updatingOrder.setSupplier(selectedSupplier);
        updatingOrder.setQuantity(quantityValue);
        updatingOrder.setPriceExpected(priceValue);

        reference = FirebaseDatabase.getInstance().getReference("Orders").child(updatingOrder.getOrderId());
        reference.setValue(updatingOrder, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref)
            {
                if(error != null)
                {
                    Toast.makeText(OrderUpdateSitemanager.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(OrderUpdateSitemanager.this, "Successfully Updated!", Toast.LENGTH_SHORT).show();

                    setResult(Activity.RESULT_OK);
                    finish();
                }
                dialog.dismiss();
            }
        });


    }
}