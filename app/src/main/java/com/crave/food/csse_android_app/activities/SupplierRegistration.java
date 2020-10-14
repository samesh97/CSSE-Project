package com.crave.food.csse_android_app.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.models.Supplier;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;


public class SupplierRegistration extends AppCompatActivity {


    private Uri uri = null;
    private DatabaseReference reference;
    private StorageReference mStorageRef;
    private ProgressDialog progressBar;
    private CircleImageView profile_pic;

    public static final int PICK_IMAGE = 100;
    private ArrayAdapter<CharSequence> adapter;

    private String typeText = "";


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && data != null)
        {
            uri = data.getData();
            profile_pic.setImageURI(uri);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_registration);


        profile_pic = findViewById(R.id.profile_pic);
        progressBar = new ProgressDialog(SupplierRegistration.this);
        progressBar.setMessage("In Progress..");
        progressBar.setCancelable(false);

        Spinner type = findViewById(R.id.type);


        adapter = ArrayAdapter.createFromResource(this, R.array.type, R.layout.layout_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);

        type.setSelection(0);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                typeText = adapter.getItem(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Suppliers");
        mStorageRef = FirebaseStorage.getInstance().getReference();

    }
    public void Register(View view)
    {
        EditText fullName = findViewById(R.id.fullName);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText c_password = findViewById(R.id.c_password);
        EditText phone = findViewById(R.id.phone);
        EditText address = findViewById(R.id.address);



        String emailT = email.getText().toString().trim();

        if(fullName.getText().toString().trim().equals(""))
        {
            showToast("Please enter Full name");
            return;
        }
        if(email.getText().toString().trim().equals(""))
        {
            showToast("Please enter Email");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailT).matches())
        {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(address.getText().toString().trim().equals(""))
        {
            showToast("Please enter Address");
            return;
        }
        if(phone.getText().toString().trim().equals(""))
        {
            showToast("Please enter Phone Number");
            return;
        }
        if(typeText.equals("") || typeText.equals("Type"))
        {
            showToast("Please select Type");
            return;
        }
        if(password.getText().toString().trim().equals(""))
        {
            showToast("Please enter Password");
            return;
        }
        if(c_password.getText().toString().trim().equals(""))
        {
            showToast("Please Confirm Password");
            return;
        }
        if(!password.getText().toString().equals(c_password.getText().toString()))
        {
            showToast("Password mismatched!");
            return;
        }
        if(uri == null)
        {
            showToast("Please pick an image");
            return;
        }

        progressBar.show();
        registerSupplier(fullName.getText().toString(),email.getText().toString(),address.getText().toString(),password.getText().toString(),phone.getText().toString());

    }

    private void registerSupplier(String fullname, String email, String address,String password, String phone)
    {
        long id = System.currentTimeMillis();
        final Supplier supplier = new Supplier();

        supplier.setSupplierId(String.valueOf(id));
        supplier.setSupplierAddress(address);
        supplier.setSupplierEmail(email);
        supplier.setSupplierPhone(phone);
        supplier.setSupplierName(fullname);
        supplier.setPassword(password);
        supplier.setSupplierType(typeText);

        final StorageReference sRef = mStorageRef.child("Supplier/" + supplier.getSupplierId());
        sRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri)
                                {
                                    String profilePicId = uri.toString();
                                    supplier.setSupplierImage(profilePicId);
                                    RegisterUser(supplier);
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener()
            {
                @Override
                public void onFailure(@NonNull Exception exception)
                {
                    progressBar.dismiss();

                }});




    }
    private void RegisterUser(Supplier supplier)
    {

        //register user
        reference.child(supplier.getSupplierId()).setValue(supplier, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference)
            {
                if(databaseError != null)
                {
                    //an error occurred, the error toast will be shown
                    showToast(databaseError.getMessage());
                }
                else
                {
                    showToast("Successfully Registered!");
                    Intent intent = new Intent(SupplierRegistration.this,Login.class);
                    startActivity(intent);
                    finish();
                }

                progressBar.dismiss();

            }

        });
    }

    public void PickImage(View view)
    {
        //this method will be executed to pick an image from the gallery as the profile picture
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }
    public void showToast(String message)
    {
        //show the toast
        if(message != null && !message.equals(""))
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}