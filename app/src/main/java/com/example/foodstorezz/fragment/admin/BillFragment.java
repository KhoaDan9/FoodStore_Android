package com.example.foodstorezz.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodstorezz.R;
import com.example.foodstorezz.adapters.BillAdapter;
import com.example.foodstorezz.database.Bill;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.fragment.staff.PayStaffFragment;

import java.util.List;

public class BillFragment extends Fragment {
    private RecyclerView rcvBill;
    private BillAdapter billAdapter;
    private List<Bill> mListBill;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill, container, false);

        rcvBill = view.findViewById(R.id.rcv_bill);

        billAdapter = new BillAdapter(new BillAdapter.IClickItemBill() {
            @Override
            public void getDetailBill(Bill bill) {
                clickDetailBill(bill);
            }
        });
        loadData();

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rcvBill.setLayoutManager(linearLayoutManager);
        rcvBill.setAdapter(billAdapter);

        return view;
    }
    private void loadData(){
        mListBill = FoodStoreDatabase.getInstance(requireContext()).billDAO().getAllBill();
        billAdapter.setData(mListBill);
    }

    private void clickDetailBill(Bill bill) {
        Bundle bundle = new Bundle();
        bundle.putInt("BillId", bill.getId());

        BillDetailFragment billDetailFragment = new BillDetailFragment();
        billDetailFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, billDetailFragment)
                .addToBackStack(null)
                .commit();
    }
}
