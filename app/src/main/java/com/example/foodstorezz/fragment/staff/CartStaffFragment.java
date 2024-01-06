package com.example.foodstorezz.fragment.staff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodstorezz.R;
import com.example.foodstorezz.adapters.CartAdapter;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Product;
import com.example.foodstorezz.model.Cart;

import java.util.List;

public class CartStaffFragment extends Fragment implements CartAdapter.EventListener{
    private Cart cart = new Cart();
    private List<Product> mListProduct;
    private RecyclerView rcvCart;
    private CartAdapter cartAdapter;
    private Button btnBackToMenu;
    private TextView tvTotalBill;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart,container,false);

        initUi(view);

        cartAdapter = new CartAdapter(requireContext(), this);

        loadData();

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rcvCart.setLayoutManager(linearLayoutManager);
        rcvCart.setAdapter(cartAdapter);


        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMenu();
            }
        });

        loadTotalBill();
        return view;
    }

    private void loadData(){
        mListProduct = FoodStoreDatabase.getInstance(requireContext()).productDAO().getAllProduct();
        cartAdapter.setData(mListProduct);
    }

    public void loadTotalBill(){
        tvTotalBill.setText(String.valueOf(this.cart.getTotalBill()) + " VNĐ");
    }
    private void initUi(View view){
        rcvCart = view.findViewById(R.id.rcv_cart);
        btnBackToMenu = view.findViewById(R.id.btn_back_to_menu_staff);
        tvTotalBill = view.findViewById(R.id.tv_cart_total_bill2);
    }

    private void backToMenu(){
        Bundle bundle = new Bundle();

        MenuStaffFragment menuStaffFragment = new MenuStaffFragment();
        menuStaffFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame_staff, menuStaffFragment)
                .addToBackStack(null)
                .commit();
    }

}
