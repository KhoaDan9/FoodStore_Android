package com.example.foodstorezz.fragment.staff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodstorezz.R;
import com.example.foodstorezz.adapters.ProductStaffAdapter;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Product;
import com.example.foodstorezz.model.Cart;

import java.util.List;

public class MenuStaffFragment extends Fragment {
    private RecyclerView rcvMenuStaff;
    private ProductStaffAdapter productStaffAdapter;
    private List<Product> mListProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_staff, container, false);
        initUi(view);

        productStaffAdapter = new ProductStaffAdapter();

        loadData();

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),2);
        rcvMenuStaff.setLayoutManager(gridLayoutManager);
        rcvMenuStaff.setAdapter(productStaffAdapter);

        return view;
    }

    public void loadData(){
        mListProduct = FoodStoreDatabase.getInstance(requireContext()).productDAO().getAllProduct();
        productStaffAdapter.setData(mListProduct);
    }
    private void initUi(View view){
        rcvMenuStaff = view.findViewById(R.id.rcv_menu_staff);
    }

}
