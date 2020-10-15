package com.crave.food.csse_android_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reference = FirebaseDatabase.getInstance().getReference();

        checkSupplierLogin("holcim@yahoo.com","12345");
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
                            LoginState.saveUser(Login.this,supplier);
                            return;
                        }
                    }
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Login.this, "No path found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                            LoginState.saveUser(Login.this,manager);
                            return;
                        }
                    }
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Login.this, "Path doesn't exists", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}