package com.crave.food.csse_android_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.models.Product;
import com.crave.food.csse_android_app.models.Supplier;

import java.util.ArrayList;

public class SupplierSpinnerAdapter extends BaseAdapter {

    private Context context;
    private Product list;

    public SupplierSpinnerAdapter(Context context, Product list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.getSuppliers().size();
    }

    @Override
    public Object getItem(int i)
    {
        return list.getSuppliers().get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup)
    {
        View view1 = LayoutInflater.from(context).inflate(R.layout.layout_spinner_order,viewGroup,false);
        TextView text1 = view1.findViewById(R.id.text1);
        text1.setText(list.getSuppliers().get(i).getSupplierName());

        return view1;
    }
}
