package com.example.foodstorezz.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodstorezz.R;

public class MenuFragment extends Fragment {

    Button btnAddProduct;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        initUi(view);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

        return view;
    }

    public void initUi(View view) {
        btnAddProduct = view.findViewById(R.id.btn_add_product);
    }

    public void addProduct() {
        Bundle bundle = new Bundle();

        AddProductFragment addProductFragment = new AddProductFragment();
        addProductFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, addProductFragment)
                .addToBackStack(null)  // (optional) cho phép quay lại FragmentA nếu cần
                .commit();
    }
}
