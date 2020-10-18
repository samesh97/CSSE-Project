package com.crave.food.csse_android_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crave.food.csse_android_app.adapters.ProductSpinnerAdapter;
import com.crave.food.csse_android_app.adapters.SupplierSpinnerAdapter;
import com.crave.food.csse_android_app.config.LoginState;
import com.crave.food.csse_android_app.listners.OnProductClicked;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class OrderPlaceSitemanager extends AppCompatActivity {



    private Spinner product;
    private Spinner supplier;
    private ProductSpinnerAdapter adapter;
    SupplierSpinnerAdapter spinnerAdapter;


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

    private Product selectedProduct = null;
    private Supplier selectedSupplier= null;

    private Button btn_sendForApproval,btn_placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_place_sitemanager);


        dialog = new ProgressDialog(OrderPlaceSitemanager.this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);

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
        btn_sendForApproval = findViewById(R.id.btn_sendForApproval);
        btn_placeOrder = findViewById(R.id.btn_placeOrder);


        adapter = new ProductSpinnerAdapter(OrderPlaceSitemanager.this, productList);
        product.setAdapter(adapter);

        product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                selectedProduct = productList.get(i);

                spinnerAdapter = new SupplierSpinnerAdapter(OrderPlaceSitemanager.this,selectedProduct);
                supplier.setAdapter(spinnerAdapter);

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



        reference = FirebaseDatabase.getInstance().getReference("Orders");
        progressBar = new ProgressDialog(OrderPlaceSitemanager.this);
        progressBar.setMessage("In Progress..");
        progressBar.setCancelable(false);


        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                try
                {
                    String text = charSequence.toString();
                    int value = Integer.parseInt(text);
                    if(value <= 100000)
                    {
                        btn_placeOrder.setEnabled(true);
                        btn_sendForApproval.setEnabled(false);
                        btn_placeOrder.setBackground(getResources().getDrawable(R.drawable.button_primary_color_background));
                        btn_sendForApproval.setBackground(null);
                    }
                    else
                    {
                        btn_placeOrder.setEnabled(false);
                        btn_sendForApproval.setEnabled(true);
                        btn_sendForApproval.setBackground(getResources().getDrawable(R.drawable.button_primary_color_background));
                        btn_placeOrder.setBackground(null);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        getProducts();

    }

    public void sendForApprovalClicked(View view)
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


        if (priceValue <= 100000)
        {
            showToast("Please place the Order");
        }
        else
        {
            insertOrder(companyTxt,phoneTxt,quantityValue,dateRequiredTxt,siteAddressTxt,priceValue,notesTxt,"Pending","Order successfully sent for approval");
        }

    }

    public void getProducts()
    {

       User user = LoginState.getInstance().getUser(OrderPlaceSitemanager.this);

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
                       Toast.makeText(OrderPlaceSitemanager.this, "No Products Found!", Toast.LENGTH_SHORT).show();
                   }
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

    public void placeOrderClicked(View view)
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



        if (priceValue > 100000)
        {
            showToast("Order has to be sent for approval");

        }
        {
            insertOrder(companyTxt,phoneTxt,quantityValue,dateRequiredTxt,siteAddressTxt,priceValue,notesTxt,"Placed","Successfully Placed the Order!");
        }


    }
    public void insertOrder(String companyTxt,String phoneTxt,int quantityValue,String dateRequiredTxt,String siteAddressTxt,float priceValue,String notesTxt,String status,String toastText)
    {
        long order_id = System.currentTimeMillis();
        Order order = new Order();

        order.setOrderId(String.valueOf(order_id));
        order.setCompanyName(companyTxt);
        order.setPhone(phoneTxt);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = new Date();
        order.setDateCurrent(formatter.format(date));

        order.setRefNo(String.valueOf(order_id));
        order.setQuantity(quantityValue);
        order.setDateRequired(dateRequiredTxt);
        order.setSiteAddress(siteAddressTxt);
        order.setPriceExpected(priceValue);
        order.setNotes(notesTxt);
        order.setProduct(selectedProduct);
        order.setSupplier(selectedSupplier);
        order.setStatus(status);
        order.setUnit(selectedProduct.getUnit());

        User user = LoginState.getInstance().getUser(OrderPlaceSitemanager.this);
        if(user instanceof Manager)
        {
            Manager manager = (Manager) user;
            order.setCompanyId(manager.getCompanyId());
        }



        insertToDatabase(order,toastText);

    }

    public void insertToDatabase(final Order order, final String message)
    {
        reference = FirebaseDatabase.getInstance().getReference("Orders");
        reference.child(order.getOrderId()).setValue(order, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference)
            {
                if(databaseError != null)
                {

                    showToast(databaseError.getMessage());
                }
                else
                {
                    showToast(message);
                   // showToast("Successfully sent for Approval!");
                    Intent intent = new Intent(OrderPlaceSitemanager.this,OrderViewSitemanager.class);
                    startActivity(intent);
                    finish();
                }
                progressBar.dismiss();

            }

        });
    }

    public boolean showToast(String message)
    {
        //show the toast
        if(message != null && !message.equals(""))
        {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return true;
        }
        else
        {
            return false;
        }

    }
}