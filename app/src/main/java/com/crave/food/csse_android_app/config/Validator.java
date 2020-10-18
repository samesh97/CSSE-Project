package com.crave.food.csse_android_app.config;

import android.util.Patterns;

public class Validator
{
    private static Validator validator;

    private Validator(){}

    public static Validator getInstance()
    {
        if(validator == null)
        {
            validator = new Validator();
        }
        return validator;
    }
    public boolean isValidEmail(String email)
    {
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return true;
        }
        return false;
    }
    public boolean isValidNumber(String number)
    {
        if(number != null && number.length() == 10)
        {
            try
            {
                int nmb = Integer.parseInt(number);
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }

        return false;
    }
}
