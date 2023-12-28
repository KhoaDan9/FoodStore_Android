package com.example.foodstorezz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Staff;
import com.example.foodstorezz.database.User;
import com.example.foodstorezz.database.UserDAO;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edtUsername;
    private EditText edtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUi();
//        User user = new User("khoadang","123456");
//        FoodStoreDatabase.getInstance(this).userDAO().insertUser(user);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClick();
            }

        });
    }

    public void loginClick() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = FoodStoreDatabase.getInstance(this).userDAO().getUser(username);
        if(user != null) {
            if (!user.getPassword().equals(password) )
            {
                Toast.makeText(this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else {
            Staff staff = FoodStoreDatabase.getInstance(this).staffDAO().getStaffbyUsername(username);
            if (!staff.getPassword().equals(password) )
            {
                Toast.makeText(this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                Intent intent = new Intent(LoginActivity.this, StaffActivity.class);
                startActivity(intent);
                Bundle bundle = new Bundle();
                bundle.putParcelable("staff", staff);
                intent.putExtras(bundle);
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void hideSoftKeyBoard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    private void initUi() {
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
    }
}