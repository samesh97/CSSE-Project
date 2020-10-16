package com.crave.food.csse_android_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.crave.food.csse_android_app.R;
import com.crave.food.csse_android_app.listners.OnProductClicked;
import com.crave.food.csse_android_app.models.Product;

import java.util.ArrayList;

public class ProductSpinnerAdapter extends BaseAdapter implements AdapterView.OnItemSelectedListener
{

    private Context context;
    private ArrayList<Product> list;
    private OnProductClicked listener;

    public ProductSpinnerAdapter(Context context, ArrayList<Product> list, OnProductClicked listener)
    {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i)
    {
        return list.get(i);
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
        text1.setText(list.get(i).getProduct());

        return view1;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        listener.onChange(list.get(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
