package com.example.foodstorezz.fragment.staff;

import static android.content.Intent.getIntent;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodstorezz.R;
import com.example.foodstorezz.adapters.CartAdapter;
import com.example.foodstorezz.database.Bill;
import com.example.foodstorezz.database.BillProduct;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Product;
import com.example.foodstorezz.database.Staff;
import com.example.foodstorezz.model.Cart;
import com.example.foodstorezz.model.StaffDetail;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CartStaffFragment extends Fragment implements CartAdapter.EventListener{
    private Cart cart = new Cart();
    private Staff staff = null;
    private List<Product> mListProduct;
    private RecyclerView rcvCart;
    private CartAdapter cartAdapter;
    private Button btnBackToMenu, btnPay;
    private TextView tvTotalBill;
    private int sum;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart,container,false);

        initUi(view);

        staff = StaffDetail.staff;

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

        btnPay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                payOnClick();
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
        btnPay = view.findViewById(R.id.btn_cart_pay);
    }

    private void backToMenu(){
        MenuStaffFragment menuStaffFragment = new MenuStaffFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame_staff, menuStaffFragment)
                .addToBackStack(null)
                .commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void payOnClick(){
        if (staff != null) {
            Bill newBill = new Bill();

            FoodStoreDatabase.getInstance(requireContext()).billDAO().addBill(newBill);
            Bill getNewBill = FoodStoreDatabase.getInstance(requireContext()).billDAO().getNewBill();
            int billId = getNewBill.getId();
            Cart.cartList.forEach((key, value) -> {
                Product product = null;
                for (Product p : mListProduct){
                    if (p.getId() == key){
                        product = p;
                        break;
                    }
                }

                int productId = product.getId();
                String productName = product.getName();
                int productPrice = (int) product.getPrice();
                int productNumBuy = value;
                int totalPrice = value * productPrice;
                byte[] imageData = product.getImageData();
                sum += totalPrice;
                BillProduct billProduct = new BillProduct(billId, productId, productName, productPrice, productNumBuy, totalPrice, imageData);
                FoodStoreDatabase.getInstance(requireContext()).billProductDAO().addBillProduct(billProduct);

            });

            String date = String.valueOf(LocalDateTime.now());

            getNewBill.setStaffId(staff.getId());
            getNewBill.setStaffName(staff.getFullname());
            getNewBill.setDate(date);
            getNewBill.setTotalBill(sum);
            FoodStoreDatabase.getInstance(requireContext()).billDAO().updateBill(getNewBill);
            Cart.cartList.clear();
            Cart.setTotalBill(0);

            Bundle bundle = new Bundle();
            bundle.putInt("BillId", getNewBill.getId());

            PayStaffFragment payStaffFragment = new PayStaffFragment();
            payStaffFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame_staff, payStaffFragment)
                    .addToBackStack(null)
                    .commit();

    }
        else
            Toast.makeText(requireContext(), "Lỗi về staff",Toast.LENGTH_SHORT).show();

    }
}
