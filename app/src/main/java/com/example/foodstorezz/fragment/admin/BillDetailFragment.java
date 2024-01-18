package com.example.foodstorezz.fragment.admin;

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
import com.example.foodstorezz.adapters.BillDetailAdapter;
import com.example.foodstorezz.adapters.PayAdapter;
import com.example.foodstorezz.database.Bill;
import com.example.foodstorezz.database.BillProduct;
import com.example.foodstorezz.database.FoodStoreDatabase;

import java.util.List;

public class BillDetailFragment extends Fragment {
    private RecyclerView rcvPay;
    private TextView tvBillId, tvTime, tvTotalPrice;
    private List<BillProduct> mListBillProduct;
    private BillDetailAdapter billDetailAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill_detail, container, false);

        rcvPay = view.findViewById(R.id.rcv_pay);
        tvBillId = view.findViewById(R.id.tv_bill_id2);
        tvTime = view.findViewById(R.id.tv_time2);
        tvTotalPrice = view.findViewById(R.id.tv_all_price2);

        Bundle bundle = this.getArguments();
        int BillId = bundle.getInt("BillId");

        Bill bill = FoodStoreDatabase.getInstance(requireContext()).billDAO().getBillById(BillId);
        mListBillProduct = FoodStoreDatabase.getInstance(requireContext()).billProductDAO().getAllBillProductByBillId(BillId);

        tvBillId.setText(String.valueOf(bill.getId()));
        tvTime.setText(bill.getDate());
        tvTotalPrice.setText(String.valueOf(bill.getTotalBill()));

        billDetailAdapter = new BillDetailAdapter();
        billDetailAdapter.setData(mListBillProduct);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rcvPay.setLayoutManager(linearLayoutManager);
        rcvPay.setAdapter(billDetailAdapter);

        return view;
    }
}
