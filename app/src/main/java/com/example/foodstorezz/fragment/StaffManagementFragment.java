package com.example.foodstorezz.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodstorezz.R;
import com.example.foodstorezz.adapters.StaffAdapter;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffManagementFragment extends Fragment {
    private RecyclerView rcvStaff;
    private List<Staff> mListStaff;
    private StaffAdapter staffAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff, container, false);
        initUi(view);
        staffAdapter = new StaffAdapter(new StaffAdapter.IClickItemStaff() {
            @Override
            public void getDetailStaff(Staff staff) {
                clickDetailStaff(staff);
            }

        });
        mListStaff = new ArrayList<>();

        loadData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rcvStaff.setLayoutManager(linearLayoutManager);

        staffAdapter.setData(mListStaff);
        rcvStaff.setAdapter(staffAdapter);

        return view;
    }

    private void initUi(View view) {
        rcvStaff = view.findViewById(R.id.rcv_staff);
    }
    public void loadData(){
        mListStaff = FoodStoreDatabase.getInstance(requireContext()).staffDAO().getAll();
        staffAdapter.setData(mListStaff);
    }
    private void clickDetailStaff(Staff staff) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("staff", staff);

        StaffDetailFragment staffDetailFragment = new StaffDetailFragment();
        staffDetailFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, staffDetailFragment)
                .addToBackStack(null)  // (optional) cho phép quay lại FragmentA nếu cần
                .commit();
    }
}
