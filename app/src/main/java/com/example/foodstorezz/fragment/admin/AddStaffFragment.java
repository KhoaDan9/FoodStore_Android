package com.example.foodstorezz.fragment.admin;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodstorezz.AddUserActivity;
import com.example.foodstorezz.MainActivity;
import com.example.foodstorezz.R;
import com.example.foodstorezz.StaffActivity;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Staff;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddStaffFragment extends Fragment {
    private EditText edtFullname, edtDateOfBirth, edtCccd, edtAddress, edtPhoneNumber, edtUsername, edtPassword;
    private Button btnAddStaff;
    private TextView dateTextView;

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

    private void validateAndRegister() {
        // Your existing validation logic here

        // Validate date of birth
        String dob = edtDateOfBirth.getText().toString().trim();
        if (dob.isEmpty() || !isValidDateOfBirth(dob)) {
            showToast("Vui lòng chọn ngày sinh");
            return;
        }

        // Continue with the registration logic
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
        }
        else if (!isValidDateOfBirth(dateOfBirth)) {
           showToast("Định dạng ngày sinh không hợp lệ");
        }

        else if (password.isEmpty() || !isValidPassword(password)) {
            showToast("Vui lòng nhập mật khẩu hợp lệ");
            return;
        }

        else {
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
        dateTextView = view.findViewById(R.id.tv_ngaytao);
        // Lấy ngày hiện tại
        Date currentDate = new Date();


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(currentDate);

        dateTextView.setText("Ngày hiện tại: " + formattedDate);
        
    }
    private boolean isValidDateOfBirth(String dateOfBirth) {
        return dateOfBirth.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    private boolean isValidPassword(String password) {
        // Define password criteria
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(passwordRegex);

        // Create matcher object
        Matcher matcher = pattern.matcher(password);

        // Return whether the password meets the criteria
        return matcher.matches();
    }
}
