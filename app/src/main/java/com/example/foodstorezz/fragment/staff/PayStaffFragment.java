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
import com.example.foodstorezz.adapters.PayAdapter;
import com.example.foodstorezz.database.Bill;
import com.example.foodstorezz.database.BillProduct;
import com.example.foodstorezz.database.FoodStoreDatabase;

import java.util.List;

public class PayStaffFragment extends Fragment {
    private RecyclerView rcvPay;
    private TextView tvBillId, tvTime, tvTotalPrice;
    private Button btnBackToMenu;
    private List<BillProduct> mListBillProduct;
    private PayAdapter payAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay,container,false);

        rcvPay = view.findViewById(R.id.rcv_pay);
        tvBillId = view.findViewById(R.id.tv_bill_id);
        tvTime = view.findViewById(R.id.tv_time2);
        tvTotalPrice = view.findViewById(R.id.tv_all_price2);

        btnBackToMenu = view.findViewById(R.id.btn_back_to_menu_staff);

        Bundle bundle = this.getArguments();
        int BillId = bundle.getInt("BillId");

        Bill bill = FoodStoreDatabase.getInstance(requireContext()).billDAO().getBillById(BillId);
        mListBillProduct = FoodStoreDatabase.getInstance(requireContext()).billProductDAO().getAllBillProductByBillId(BillId);

        tvBillId.setText(String.valueOf(bill.getId()));
        tvTime.setText(bill.getDate());
        tvTotalPrice.setText(String.valueOf(bill.getTotalBill()));

        payAdapter = new PayAdapter();
        payAdapter.setData(mListBillProduct);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rcvPay.setLayoutManager(linearLayoutManager);
        rcvPay.setAdapter(payAdapter);

        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMenu();
            }
        });

        return view;
    }

    private void backToMenu(){
        MenuStaffFragment menuStaffFragment = new MenuStaffFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame_staff, menuStaffFragment)
                .addToBackStack(null)
                .commit();
    }
}
