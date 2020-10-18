package com.crave.food.csse_android_app.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.crave.food.csse_android_app.models.Manager;
import com.crave.food.csse_android_app.models.Supplier;
import com.crave.food.csse_android_app.models.User;

public class LoginState
{

    private static final String SHARED_PREF_KEY = "SHARED_PREF_KEY";

    private static final String  USER_TYPE_KEY = "USER_TYPE";

    private static final int SUPPLIER = 0;
    private static final int MANAGER = 1;
    private static final int NO_USER = -99;

    private static final String SUPPLIER_ID_KEY = "SUPPLIER_ID_KEY";
    private static final String SUPPLIER_NAME_KEY = "SUPPLIER_NAME_KEY";
    private static final String SUPPLIER_IMAGE_KEY = "SUPPLIER_IMAGE_KEY";
    private static final String SUPPLIER_TYPE_KEY = "SUPPLIER_TYPE_KEY";
    private static final String SUPPLIER_PHONE_KEY = "SUPPLIER_PHONE_KEY";
    private static final String SUPPLIER_EMAIL_KEY = "SUPPLIER_EMAIL_KEY";


    private static final String  MANAGER_ID_KEY = "MANAGER_ID_KEY";
    private static final String  MANAGER_NAME_KEY = "MANAGER_NAME_KEY";
    private static final String  MANAGER_EMAIL_KEY = "MANAGER_EMAIL_KEY";
    private static final String COMPANY_ID_KEY = "COMPANY_ID_KEY";

    private static LoginState loginState;

    private LoginState()
    {

    }
    public static LoginState getInstance()
    {
        if(loginState == null)
        {
            loginState = new LoginState();
        }
        return loginState;
    }

    public User getUser(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_KEY,Context.MODE_PRIVATE);
        User user = null;

        int type = preferences.getInt(USER_TYPE_KEY,NO_USER);

        if(type == SUPPLIER)
        {
            String supplierId = preferences.getString(SUPPLIER_ID_KEY,"");
            String supplierName = preferences.getString(SUPPLIER_NAME_KEY,"");
            String supplierImage = preferences.getString(SUPPLIER_IMAGE_KEY,"");
            String supplierType = preferences.getString(SUPPLIER_TYPE_KEY,"");
            String supplierPhone = preferences.getString(SUPPLIER_PHONE_KEY,"");
            String supplierEmail = preferences.getString(SUPPLIER_EMAIL_KEY,"");

            user = new Supplier(supplierId,supplierName,supplierEmail,"",supplierPhone,supplierType,supplierImage,"");
        }
        else if(type == MANAGER)
        {
            long managerId = preferences.getLong(MANAGER_ID_KEY,-99);
            String managerName = preferences.getString(MANAGER_NAME_KEY,"");
            String managerEmail = preferences.getString(MANAGER_EMAIL_KEY,"");
            String companyId = preferences.getString(COMPANY_ID_KEY,"");
            user = new Manager(companyId,managerEmail,managerName,"",managerId);
        }

        return user;

    }
    public boolean saveUser(Context context,User user)
    {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

       if(user instanceof Manager)
       {
           Manager manager = (Manager) user;

           editor.putInt(USER_TYPE_KEY,MANAGER);

           editor.putLong(MANAGER_ID_KEY,manager.getManagerId());
           editor.putString(MANAGER_NAME_KEY,manager.getManagerName());
           editor.putString(MANAGER_EMAIL_KEY,manager.getEmail());
           editor.putString(COMPANY_ID_KEY,manager.getCompanyId());
           editor.apply();

           return true;
       }
       else if(user instanceof  Supplier)
       {
           Supplier supplier = (Supplier) user;

           editor.putInt(USER_TYPE_KEY,SUPPLIER);

           editor.putString(SUPPLIER_ID_KEY,supplier.getSupplierId());
           editor.putString(SUPPLIER_NAME_KEY,supplier.getSupplierName());
           editor.putString(SUPPLIER_IMAGE_KEY,supplier.getSupplierImage());
           editor.putString(SUPPLIER_TYPE_KEY,supplier.getSupplierType());
           editor.putString(SUPPLIER_PHONE_KEY,supplier.getSupplierPhone());
           editor.putString(SUPPLIER_EMAIL_KEY,supplier.getSupplierEmail());
           editor.apply();

           return true;
       }
       else if(user == null)
       {
           editor.remove(USER_TYPE_KEY);
           editor.remove(MANAGER_ID_KEY);
           editor.remove(MANAGER_NAME_KEY);
           editor.remove(MANAGER_EMAIL_KEY);
           editor.remove(COMPANY_ID_KEY);
           editor.remove(SUPPLIER_ID_KEY);
           editor.remove(SUPPLIER_NAME_KEY);
           editor.remove(SUPPLIER_IMAGE_KEY);
           editor.remove(SUPPLIER_TYPE_KEY);
           editor.remove(SUPPLIER_PHONE_KEY);
           editor.remove(SUPPLIER_EMAIL_KEY);
           editor.apply();

           return false;
       }
       return false;

    }
    public int getUserType(Context context)
    {
       SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_KEY,Context.MODE_PRIVATE);
       int value = preferences.getInt(USER_TYPE_KEY,NO_USER);
        switch (value)
        {
            case SUPPLIER : return SUPPLIER;
            case MANAGER : return MANAGER;
            default:return NO_USER;
        }
    }
}
