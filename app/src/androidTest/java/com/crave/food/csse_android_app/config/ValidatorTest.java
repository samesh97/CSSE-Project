package com.crave.food.csse_android_app.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception
    {
        validator = Validator.getInstance();
    }

    @After
    public void tearDown() throws Exception
    {
        validator = null;
    }

    @Test
    public void getInstance()
    {
        Validator validator = Validator.getInstance();
        assertNotNull(validator);
    }

    @Test
    public void isValidEmail()
    {
        boolean isTrue = validator.isValidEmail("pamoshakishani@gmail.com");
        assertTrue(isTrue);
    }
    @Test
    public void isNotValidEmail()
    {
        boolean isTrue = validator.isValidEmail("pamoshakishanigmail.com");
        assertTrue(isTrue);
    }
    @Test
    public void isValidPhoneNumber()
    {
        boolean res = validator.isValidNumber("0721300180");
        assertTrue(res);
    }
    @Test
    public void isNotValidPhoneNumber()
    {
        boolean res = validator.isValidNumber("721300189");
        assertTrue(res);
    }
    @Test
    public void isEqual()
    {
        int string1 = 10;
        int string2 = 10;

        assertNotEquals(string1,string2);
    }
}