package com.crave.food.csse_android_app.listners;

import com.crave.food.csse_android_app.models.Order;

public interface OnOrderClicked {
    public void orderClick(Order order);

    public void orderDeleteClick(Order order);

}
