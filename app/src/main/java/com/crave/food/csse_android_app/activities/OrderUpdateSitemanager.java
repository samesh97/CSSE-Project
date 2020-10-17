package com.crave.food.csse_android_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.crave.food.csse_android_app.OrderPlaceSitemanager;
import com.crave.food.csse_android_app.OrderViewSitemanager;
import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.adapters.ProductSpinnerAdapter;
import com.crave.food.csse_android_app.models.Order;
import com.crave.food.csse_android_app.models.Product;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class OrderUpdateSitemanager extends AppCompatActivity {


    private String supplierTxt = "";
    private Spinner product;
    private Spinner supplier;
    private ProductSpinnerAdapter adapter;
    private ArrayAdapter<CharSequence> adapter1;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_update_sitemanager);

        Order order = OrderViewSitemanager.editingOrder;

        dialog = new ProgressDialog(OrderUpdateSitemanager.this);
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
       btn_placeOrder = findViewById(R.id.btn_placeOrder);




        companyName.setText(""+order.getCompanyName());
        phone.setText(""+order.getPhone());
        quantity.setText("" + order.getQuantity());
        dateRequired.setText(""+order.getDateRequired());
        siteAddress.setText(""+order.getSiteAddress());
        price.setText(""+order.getPriceExpected());
        notes.setText(""+order.getNotes());
        status.setText(""+order.getStatus());
        product.setPrompt(""+order.getProduct());
        supplier.setPrompt(""+order.getSupplier());



        adapter = new ProductSpinnerAdapter(OrderUpdateSitemanager.this, productList);
        product.setAdapter(adapter);

        product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                selectedProduct = productList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        adapter1 = ArrayAdapter.createFromResource(this, R.array.type, R.layout.layout_spinner);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supplier.setAdapter(adapter1);

        supplier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                supplierTxt = adapter1.getItem(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}