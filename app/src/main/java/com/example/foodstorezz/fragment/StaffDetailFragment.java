package com.example.foodstorezz.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodstorezz.R;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Staff;

public class StaffDetailFragment extends Fragment {

    private EditText edtFullname, edtDateOfBirth, edtCccd, edtAddress, edtPhoneNumber, edtUsername, edtPassword;
    private Button btnEditStaff, btnDeleteStaff;
    private Staff staff;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_detail, container, false);
        Bundle bundle = getArguments();
        staff = bundle.getParcelable("staff");
        initUi(view);

        btnEditStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editStaff();
            }
        });

        btnDeleteStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStaff();
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
        btnDeleteStaff = view.findViewById(R.id.btn_delete_staff);

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
        String phonenumber = edtPhoneNumber.getText().toString().trim();
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();


        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(address) || TextUtils.isEmpty(phonenumber)) {
            showToast("Yêu cầu nhập đầy đủ thông tin");
            hideSoftKeyboard();
        } else {
            staff.setAddress(address);
            staff.setPhonenumber(phonenumber);
            staff.setUsername(username);
            staff.setPassword(password);
            FoodStoreDatabase.getInstance(requireContext()).staffDAO().updateStaff(staff);
            showToast("Sửa thông tin nhân viên thành công");
            hideSoftKeyboard();

            StaffManagementFragment staffManagementFragment = new StaffManagementFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, staffManagementFragment)
                    .addToBackStack(null)  // (optional) cho phép quay lại FragmentA nếu cần
                    .commit();

        }
    }

    public void deleteStaff(){
        FoodStoreDatabase.getInstance(requireContext()).staffDAO().deleteStaff(staff);
        showToast("Xóa nhân viên thành công");
        StaffManagementFragment staffManagementFragment = new StaffManagementFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, staffManagementFragment);
        transaction.commit();
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void hideSoftKeyboard() {
        // Check if no view has focus
        View currentFocus = getActivity().getCurrentFocus();
        if (currentFocus != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        }
    }
}
