package com.crave.food.csse_android_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crave.food.csse_android_app.activities.Login;
import com.crave.food.csse_android_app.activities.SupplierRegistration;
import com.crave.food.csse_android_app.models.Order;
import com.crave.food.csse_android_app.models.Supplier;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderPlaceSitemanager extends AppCompatActivity {

    public static final String companyNameKey = "csse_android_app.companyName";
    public static final String phoneKey = "csse_android_app.phone";
    public static final String dateCurrentKey = "csse_android_app.dateCurrent";
    public static final String refNoKey = "csse_android_app.refNo";
    public static final String productKey = "csse_android_app.productKey";
    public static final String supplierKey = "csse_android_app.supplier";
    public static final String quantityKey = "csse_android_app.quantity";
    public static final String dateRequiredKey = "csse_android_app.dateRequired";
    public static final String siteAddressKey = "csse_android_app.siteAddress";
    public static final String priceKey = "csse_android_app.price";
    public static final String notesKey = "csse_android_app.notes";


    String producTxt = "";
    String supplierTxt = "";

    Spinner product;
    Spinner supplier;

    private ArrayAdapter<CharSequence> adapter;
    private ArrayAdapter<CharSequence> adapter1;

    private ProgressDialog progressBar;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_place_sitemanager);


        //making the 2 drop down lists for product and suppliers
        product = (Spinner) findViewById(R.id.spinner_product);
        supplier = (Spinner) findViewById(R.id.spinner_supplier);

 /*     product.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View view) {
              reference = FirebaseDatabase.getInstance().getReference().child("Companies").child("Products");
              reference.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot snapshot) {

                      ArrayList<Product> arrayList = new ArrayList<Product>();

                      for (DataSnapshot productSnapshot: snapshot.getChildren()) {
                          Product product = productSnapshot.getValue(Product.class);
                          arrayList.add(product);
                      }

                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError error) {

                  }
              });
          }
      });
*/
        adapter = ArrayAdapter.createFromResource(this, R.array.type, R.layout.layout_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        product.setAdapter(adapter);

        product.setSelection(0);

        product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                producTxt = adapter.getItem(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adapter1 = ArrayAdapter.createFromResource(this, R.array.type, R.layout.layout_spinner);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supplier.setAdapter(adapter1);

        supplier.setSelection(0);

        supplier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                supplierTxt = adapter1.getItem(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Orders");
        progressBar = new ProgressDialog(OrderPlaceSitemanager.this);
        progressBar.setMessage("In Progress..");
        progressBar.setCancelable(false);
    }

 /*   public void sendOrderDetails() {
        //    Intent intent = new Intent(this,OrderViewSitemanager.class);

        EditText price = (EditText) findViewById(R.id.editText_price);
        Float priceValue = Float.parseFloat(price.getText().toString());


        Button btn_sendForApproval = findViewById(R.id.btn_sendForApproval);
        Button btn_placeOrder = findViewById(R.id.btn_placeOrder);

        if (priceValue > 100000.00) {
            btn_placeOrder.setEnabled(false);
            btn_sendForApproval.setEnabled(true);

        } else {
            btn_placeOrder.setEnabled(true);
            btn_placeOrder.setEnabled(false);
        }

    }

  */

    /*    intent.putExtra(companyNameKey,company);
        intent.putExtra(phoneKey,company);
        intent.putExtra(dateCurrentKey,company);
        intent.putExtra(refNoKey,company);
        intent.putExtra(productKey,company);
        intent.putExtra(supplierKey,company);
        intent.putExtra(quantityKey,company);
        intent.putExtra(dateRequiredKey,company);
        intent.putExtra(siteAddressKey,company);
        intent.putExtra(priceKey,company);
        intent.putExtra(notesKey,company);

*/

    //   }

    public void sendForApprovalClicked(View view) {
        EditText companyName = (EditText) findViewById(R.id.editText_companyName);
        EditText phone = (EditText) findViewById(R.id.editTextPhone);
        EditText dateCurrent = (EditText) findViewById(R.id.editTextDate);
        EditText refNo = (EditText) findViewById(R.id.editText_RefNo);
        EditText quantity = (EditText) findViewById(R.id.editText_quantity);
        EditText dateRequired = (EditText) findViewById(R.id.editText_dateRequired);
        EditText siteAddress = (EditText) findViewById(R.id.editTextTextPostalAddress);
        EditText price = (EditText) findViewById(R.id.editText_price);
        EditText notes = (EditText) findViewById(R.id.editText_notes);
        TextView status = (TextView) findViewById(R.id.status);

        String companyTxt = companyName.getText().toString();
        String phoneTxt = phone.getText().toString();
        String dateCurrentTxt = dateCurrent.getText().toString();
        String refNoTxt = refNo.getText().toString();
        int quantityValue = Integer.parseInt(quantity.getText().toString());
        String dateRequiredTxt = dateRequired.getText().toString();
        String siteAddressTxt = siteAddress.getText().toString();
        Float priceValue = Float.parseFloat(price.getText().toString());
        String notesTxt = notes.getText().toString();

        Button btn_sendForApproval = findViewById(R.id.btn_sendForApproval);
        Button btn_placeOrder = findViewById(R.id.btn_placeOrder);


        if (priceValue <= 100000) {
            showToast("Please place the Order");
            btn_sendForApproval.setEnabled(false);

        }

        else
            insertOrderDetailsApprove(companyTxt,phoneTxt,dateCurrentTxt,refNoTxt,quantityValue,dateRequiredTxt,siteAddressTxt,priceValue,notesTxt);

   //     if (!btn_sendForApproval.isEnabled()){
    //        btn_sendForApproval.setEnabled(true);
   //     }


    }

    public void placeOrderClicked(View view) {
        EditText companyName = (EditText) findViewById(R.id.editText_companyName);
        EditText phone = (EditText) findViewById(R.id.editTextPhone);
        EditText dateCurrent = (EditText) findViewById(R.id.editTextDate);
        EditText refNo = (EditText) findViewById(R.id.editText_RefNo);
        EditText quantity = (EditText) findViewById(R.id.editText_quantity);
        EditText dateRequired = (EditText) findViewById(R.id.editText_dateRequired);
        EditText siteAddress = (EditText) findViewById(R.id.editTextTextPostalAddress);
        EditText price = (EditText) findViewById(R.id.editText_price);
        EditText notes = (EditText) findViewById(R.id.editText_notes);
        TextView status = (TextView) findViewById(R.id.status);

        String companyTxt = companyName.getText().toString();
        String phoneTxt = phone.getText().toString();
        String dateCurrentTxt = dateCurrent.getText().toString();
        String refNoTxt = refNo.getText().toString();
        int quantityValue = Integer.parseInt(quantity.getText().toString());
        String dateRequiredTxt = dateRequired.getText().toString();
        String siteAddressTxt = siteAddress.getText().toString();
        Float priceValue = Float.parseFloat(price.getText().toString());
        String notesTxt = notes.getText().toString();


        Button btn_sendForApproval = findViewById(R.id.btn_sendForApproval);
        Button btn_placeOrder = findViewById(R.id.btn_placeOrder);


        if (priceValue > 100000) {
            showToast("Order have to be sent for approval");
            btn_placeOrder.setEnabled(false);

        }

        else
        insertOrderDetailsPlaced(companyTxt,phoneTxt,dateCurrentTxt,refNoTxt,quantityValue,dateRequiredTxt,siteAddressTxt,priceValue,notesTxt);

  //     if (!btn_placeOrder.isEnabled()){
  //        btn_placeOrder.setEnabled(true);
  //      }

    }

    public void insertOrderDetailsApprove(String companyTxt,String phoneTxt,String dateCurrentTxt,String refNoTxt,int quantityValue,String dateRequiredTxt,String siteAddressTxt,float priceValue,String notesTxt){
        long order_id = System.currentTimeMillis();
        final Order order = new Order();

        order.setOrderId(String.valueOf(order_id));
        order.setCompanyName(companyTxt);
        order.setPhone(phoneTxt);
        order.setDateCurrent(dateCurrentTxt);
        order.setRefNo(refNoTxt);
        order.setQuantity(quantityValue);
        order.setDateRequired(dateRequiredTxt);
        order.setSiteAddress(siteAddressTxt);
        order.setPriceExpected(priceValue);
        order.setNotes(notesTxt);
        order.setProduct(producTxt);
        order.setSupplier(supplierTxt);
        order.setStatus("Pending");


        insertOrderApprove(order);

    }

    public void insertOrderDetailsPlaced(String companyTxt,String phoneTxt,String dateCurrentTxt,String refNoTxt,int quantityValue,String dateRequiredTxt,String siteAddressTxt,float priceValue,String notesTxt){
        long order_id = System.currentTimeMillis();
        final Order order = new Order();

        order.setOrderId(String.valueOf(order_id));
        order.setCompanyName(companyTxt);
        order.setPhone(phoneTxt);
        order.setDateCurrent(dateCurrentTxt);
        order.setRefNo(refNoTxt);
        order.setQuantity(quantityValue);
        order.setDateRequired(dateRequiredTxt);
        order.setSiteAddress(siteAddressTxt);
        order.setPriceExpected(priceValue);
        order.setNotes(notesTxt);
        order.setProduct(producTxt);
        order.setSupplier(supplierTxt);
        order.setStatus("Placed");

        insertOrderPlace(order);

    }

    public void insertOrderApprove(final Order order){
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
                    showToast("Successfully sent for Approval!");
                   Intent intent = new Intent(OrderPlaceSitemanager.this,OrderViewSitemanager.class);

                   startActivity(intent);
//                    finish();
                }

                progressBar.dismiss();

            }

        });
    }

    public void insertOrderPlace(final Order order){
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
                    showToast("Successfully Placed!");
                    Intent intent = new Intent(OrderPlaceSitemanager.this,OrderViewSitemanager.class);

                    startActivity(intent);
//                    finish();
                }

                progressBar.dismiss();

            }

        });
    }



    public void showToast(String message)
    {
        //show the toast
        if(message != null && !message.equals(""))
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}