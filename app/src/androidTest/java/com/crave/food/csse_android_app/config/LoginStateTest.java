package com.crave.food.csse_android_app.config;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.crave.food.csse_android_app.MainActivity;
import com.crave.food.csse_android_app.activities.Login;
import com.crave.food.csse_android_app.models.Manager;
import com.crave.food.csse_android_app.models.Supplier;
import com.crave.food.csse_android_app.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginStateTest {

    @Rule
    public ActivityTestRule<Login> login = new ActivityTestRule(Login.class);

    public LoginState loginState;

    private Context context;

    @Before
    public void setUp() throws Exception
    {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        loginState = LoginState.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getInstance()
    {
        User user = loginState.getUser(context);
        //if a user has logged in
        //test case will be successful
        assertNotNull(user);
    }
    @Test
    public void getUser()
    {
        User user = loginState.getUser(context);
        if(user instanceof Manager)
        {
            //if manager
            Manager manager = (Manager) user;
            assertEquals(user,manager);
        }
        else if(user instanceof Supplier)
        {
            //if supplier
            Supplier supplier = (Supplier) user;
            assertNotEquals(((Supplier) user).getSupplierId(),supplier.getSupplierId());
        }
        else
        {
            //if null
            assertNull(user);
        }

    }
    @Test
    public void saveUser()
    {
        boolean res = loginState.saveUser(context,new Supplier());
        assertTrue(res);
    }

}