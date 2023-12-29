package com.example.foodstorezz.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodstorezz.R;
import com.example.foodstorezz.adapters.ProductAdapter;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Product;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private Button btnAddProduct;
    private RecyclerView rcvProduct;
    private ProductAdapter productAdapter;
    private List<Product> mListProduct;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        initUi(view);

        productAdapter = new ProductAdapter();

        loadData();

        RecyclerView.LayoutManager linearLayoutManager = new GridLayoutManager(requireContext(),2);
        rcvProduct.setLayoutManager(linearLayoutManager);
        rcvProduct.setAdapter(productAdapter);

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
        rcvProduct = view.findViewById(R.id.rcv_menu);
    }

    public void loadData() {
        mListProduct = FoodStoreDatabase.getInstance(requireContext()).productDAO().getAllProduct();
        productAdapter.setData(mListProduct);
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
