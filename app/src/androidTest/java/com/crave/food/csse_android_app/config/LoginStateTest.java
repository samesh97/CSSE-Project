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
    public void tearDown() throws Exception
    {

    }

    @Test
    public void getInstance()
    {
        //passed
        User user = loginState.getUser(context);
        assertNotNull(user);
    }
    @Test
    public void saveUserNull()
    {
        //failed
        boolean res = loginState.saveUser(context,null);
        assertTrue(res);
    }
    @Test
    public void getUsers()
    {
        //failed
        User user = loginState.getUser(context);
        assertNull(user);
    }
    @Test
    public void saveManager()
    {
        //passed
        boolean res = loginState.saveUser(context,new Manager());
        assertTrue(res);
    }
    @Test
    public void isSame()
    {
        //passed
        User user = loginState.getUser(context);
        if(user instanceof Manager)
        {
            Manager manager = (Manager) user;
            assertEquals(user,manager);
        }
    }
    @Test
    public void isSupplierAndUserSame()
    {
        //failed
        User user = loginState.getUser(context);
        Supplier supplier = (Supplier) user;
        assertEquals(user,supplier);
    }
    @Test
    public void getUserType()
    {
        //passed
        int testValue = 0;
        int value = loginState.getUserType(context);
        assertEquals(testValue,value);
    }

}