package com.example.foodstorezz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Staff;
import com.example.foodstorezz.database.StaffDAO;

public class AddUserActivity extends AppCompatActivity {
    private EditText edtFullname, edtDateOfBirth, edtCccd, edtAddress, edtPhoneNumber, edtUsername, edtPassword;
    private Button btnAddStaff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initUi();

        btnAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStaff();
            }
        });
    }

    public void addStaff(){
        String fullname = edtFullname.getText().toString().trim();
        String dateOfBirth = edtDateOfBirth.getText().toString().trim();
        String cccd = edtCccd.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String phonenumber = edtPhoneNumber.getText().toString().trim();

        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(fullname)
                || TextUtils.isEmpty(dateOfBirth) || TextUtils.isEmpty(cccd) || TextUtils.isEmpty(address)
                || TextUtils.isEmpty(phonenumber))  {
            Toast.makeText(this, "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Staff staff = new Staff(username, password, fullname, address, dateOfBirth, phonenumber, cccd);
            FoodStoreDatabase.getInstance(this).staffDAO().addStaff(staff);
            Toast.makeText(this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();

        }

//        Staff user = FoodStoreDatabase.getInstance(this).userDAO().getUser(username);

//        Intent intent = new Intent(AddUserActivity.this, HomeActivity.class);
//        startActivity(intent);
//        Toast.makeText(this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
        return;

    }
    private void initUi() {
        edtFullname = findViewById(R.id.edt_staff_fullname);
        edtDateOfBirth = findViewById(R.id.edt_date_of_birth);
        edtCccd = findViewById(R.id.edt_staff_cccd);
        edtAddress = findViewById(R.id.edt_staff_address);
        edtPhoneNumber = findViewById(R.id.edt_staff_phone_number);
        edtUsername = findViewById(R.id.edt_staff_username);
        edtPassword = findViewById(R.id.edt_staff_password);
        btnAddStaff = findViewById(R.id.btn_add_staff);
    }
}