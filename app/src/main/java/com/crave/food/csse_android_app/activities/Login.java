package com.crave.food.csse_android_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.config.LoginState;
import com.crave.food.csse_android_app.models.Manager;
import com.crave.food.csse_android_app.models.Supplier;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private DatabaseReference reference;

    private EditText email,password;
    private RadioButton manager,supplier;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        manager = findViewById(R.id.manager);
        supplier = findViewById(R.id.supplier);

        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Checking..");
        progressDialog.setCancelable(false);

        reference = FirebaseDatabase.getInstance().getReference();
    }

    public void checkSupplierLogin(final String email, final String password)
    {
        reference.child("Suppliers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    for(DataSnapshot dss : snapshot.getChildren())
                    {
                        Supplier supplier = dss.getValue(Supplier.class);
                        if(supplier != null && supplier.getSupplierEmail().equals(email) && supplier.getPassword().equals(password))
                        {
                            Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                            LoginState.getInstance().saveUser(Login.this,supplier);
                            progressDialog.dismiss();

                            Intent intent = new Intent(Login.this,SupplierLoggedActivity.class);
                            startActivity(intent);
                            finish();
                            return;
                        }
                    }
                    Toast.makeText(Login.this, "No valid Supplier Found with these credentials", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Login.this, "No path found", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                progressDialog.dismiss();
            }
        });
    }

    public void checkManagerLogin(final String email, final String password)
    {

        reference.child("Managers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    for(DataSnapshot dss : snapshot.getChildren())
                    {
                        Manager manager = dss.getValue(Manager.class);
                        if(manager != null && manager.getEmail().equals(email) && manager.getPassword().equals(password))
                        {
                            Toast.makeText(Login.this, "success", Toast.LENGTH_SHORT).show();
                            LoginState.getInstance().saveUser(Login.this,manager);

                            progressDialog.dismiss();
                            Intent intent = new Intent(Login.this,ManagerLoggedActivity.class);
                            startActivity(intent);
                            finish();
                            return;
                        }
                    }
                    Toast.makeText(Login.this, "No valid manager Found with these credentials", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Login.this, "Path doesn't exists", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                progressDialog.dismiss();
            }
        });
    }

    public void Login(View view)
    {
       String emailText = email.getText().toString().trim();
       String passwordText = password.getText().toString().trim();

       boolean isManager = manager.isChecked();
       boolean isSupplier = supplier.isChecked();

       if(emailText.equals(""))
       {
           Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
           return;
       }
       else if(passwordText.equals(""))
       {
           Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
           return;
       }
       if(!isManager && !isSupplier)
       {
           Toast.makeText(this, "Please select account type", Toast.LENGTH_SHORT).show();
           return;
       }

        progressDialog.show();
       if(isManager)
       {
           checkManagerLogin(emailText,passwordText);
       }
       else if(isSupplier)
       {
           checkSupplierLogin(emailText,passwordText);
       }
    }

    public void GoToRegister(View view)
    {
        Intent intent = new Intent(Login.this,SupplierRegistration.class);
        startActivity(intent);
    }
}