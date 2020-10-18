package com.crave.food.csse_android_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.config.LoginState;
import com.crave.food.csse_android_app.models.Manager;
import com.crave.food.csse_android_app.models.Supplier;
import com.crave.food.csse_android_app.models.User;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent intent;
                User user = LoginState.getInstance().getUser(SplashScreen.this);

                if(user instanceof Supplier)
                {
                    intent = new Intent(SplashScreen.this,SupplierLoggedActivity.class);
                }
                else if(user instanceof Manager)
                {
                    intent = new Intent(SplashScreen.this,ManagerLoggedActivity.class);
                }
                else
                {
                    intent = new Intent(SplashScreen.this,Login.class);
                }
                startActivity(intent);
                finish();

            }
        },2000);

    }
}