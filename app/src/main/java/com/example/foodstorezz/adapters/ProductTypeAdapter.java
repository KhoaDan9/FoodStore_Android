package com.example.foodstorezz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodstorezz.R;
import com.example.foodstorezz.model.ProductType;

import java.util.List;

public class ProductTypeAdapter extends ArrayAdapter<ProductType> {

    public ProductTypeAdapter(@NonNull Context context, int resource, @NonNull List<ProductType> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_type_selected, parent,false);
        TextView tvSelected = convertView.findViewById(R.id.tv_selected);

        ProductType productType = this.getItem(position);
        if(productType != null)
            tvSelected.setText(productType.getType());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_type, parent,false);
        TextView tvType = convertView.findViewById(R.id.tv_type);

        ProductType productType = this.getItem(position);
        if(productType != null)
            tvType.setText(productType.getType());

        return convertView;
    }
}
