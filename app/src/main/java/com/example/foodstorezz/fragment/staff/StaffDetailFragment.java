package com.example.foodstorezz.fragment.staff;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodstorezz.R;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Staff;
import com.example.foodstorezz.fragment.admin.StaffManagementFragment;
import com.example.foodstorezz.model.StaffDetail;

public class StaffDetailFragment extends Fragment {
    private EditText edtFullname, edtDateOfBirth, edtCccd, edtAddress, edtPhoneNumber, edtUsername, edtPassword;
    private Button btnEditStaff;
    private Staff staff;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_detail2, container, false);
        staff = StaffDetail.staff;
        initUi(view);
        btnEditStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editStaff();
            }
        });

        return view;
    }


    public void initUi(View view){
        edtFullname = view.findViewById(R.id.edt_fullname);
        edtDateOfBirth = view.findViewById(R.id.edt_date_of_birth);
        edtCccd = view.findViewById(R.id.edt_staff_cccd);
        edtAddress = view.findViewById(R.id.edt_staff_address);
        edtPhoneNumber = view.findViewById(R.id.edt_staff_phone_number);
        edtUsername = view.findViewById(R.id.edt_staff_username);
        edtPassword = view.findViewById(R.id.edt_staff_password);
        btnEditStaff = view.findViewById(R.id.btn_update_staff);

        edtFullname.setText(staff.getFullname());
        edtDateOfBirth.setText(staff.getDateofbirth());
        edtCccd.setText(staff.getCccd());
        edtAddress.setText(staff.getAddress());
        edtPhoneNumber.setText(staff.getPhonenumber());
        edtUsername.setText(staff.getUsername());
        edtPassword.setText(staff.getPassword());
    }

    public void editStaff() {
        String address = edtAddress.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(address)) {
            Toast.makeText(requireContext(), "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else {
            staff.setAddress(address);
            staff.setPassword(password);
            FoodStoreDatabase.getInstance(requireContext()).staffDAO().updateStaff(staff);
            Toast.makeText(requireContext(), "Sửa thông tin thành công", Toast.LENGTH_SHORT).show();
        }
    }
}
