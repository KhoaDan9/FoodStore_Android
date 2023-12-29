package com.example.foodstorezz.fragment.admin;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodstorezz.R;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Staff;

public class AddStaffFragment extends Fragment {
    private EditText edtFullname, edtDateOfBirth, edtCccd, edtAddress, edtPhoneNumber, edtUsername, edtPassword;
    private Button btnAddStaff;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_staff, container, false);
        initUi(view);

        btnAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStaff();
            }
        });

        return view;
    }

    public void addStaff() {
        String fullname = edtFullname.getText().toString().trim();
        String dateOfBirth = edtDateOfBirth.getText().toString().trim();
        String cccd = edtCccd.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String phonenumber = edtPhoneNumber.getText().toString().trim();

        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(fullname)
                || TextUtils.isEmpty(dateOfBirth) || TextUtils.isEmpty(cccd) || TextUtils.isEmpty(address)
                || TextUtils.isEmpty(phonenumber)) {
            showToast("Yêu cầu nhập đầy đủ thông tin");
        } else {
            Staff staff = new Staff(username, password, fullname, address, dateOfBirth, phonenumber, cccd);
            FoodStoreDatabase.getInstance(requireContext()).staffDAO().addStaff(staff);
            showToast("Thêm nhân viên thành công");
        }
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void initUi(View view) {
        edtFullname = view.findViewById(R.id.edt_staff_fullname);
        edtDateOfBirth = view.findViewById(R.id.edt_date_of_birth);
        edtCccd = view.findViewById(R.id.edt_staff_cccd);
        edtAddress = view.findViewById(R.id.edt_staff_address);
        edtPhoneNumber = view.findViewById(R.id.edt_staff_phone_number);
        edtUsername = view.findViewById(R.id.edt_staff_username);
        edtPassword = view.findViewById(R.id.edt_staff_password);
        btnAddStaff = view.findViewById(R.id.btn_add_staff);
    }
}
