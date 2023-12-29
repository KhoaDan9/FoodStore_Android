package com.example.foodstorezz.fragment.admin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodstorezz.R;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Product;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddProductFragment extends Fragment {
    private EditText edtName, edtType, edtQuantity, edtPrice, edtImgSrc;
    private ImageView ivShowImg;
    private Button btnAddProduct, btnSelectImg;

    private byte[] imageBytes;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        initUi(view);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImg();
            }
        });


        return view;
    }

    public void selectImg() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauncher.launch(intent);
    }

    private ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        ivShowImg.setImageURI(selectedImageUri);
                        try {
                            imageBytes = convertUriToByteArray(selectedImageUri);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
    );
    private byte[] convertUriToByteArray(Uri uri) throws IOException {
        InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);

        Bitmap bitmap = ((BitmapDrawable) ivShowImg.getDrawable()).getBitmap();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        int bytesRead;

        while ((bytesRead = inputStream.read(imageInByte)) != -1) {
            byteArrayOutputStream.write(imageInByte, 0, bytesRead);
        }

        return byteArrayOutputStream.toByteArray();
    }

    private void initUi(View view) {
        edtName = view.findViewById(R.id.edt_product_name);
        edtType = view.findViewById(R.id.edt_product_type);
        edtQuantity = view.findViewById(R.id.edt_product_quantity);
        edtPrice = view.findViewById(R.id.edt_product_price);

        ivShowImg = view.findViewById(R.id.iv_show_img);

        btnAddProduct = view.findViewById(R.id.btn_add_product);
        btnSelectImg = view.findViewById(R.id.btn_select_img);
    }

    public void addProduct() {
        String name = edtName.getText().toString().trim();
        String type = edtType.getText().toString().trim();
        String quantityStr = edtQuantity.getText().toString();
        String priceStr = edtPrice.getText().toString();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(type) || TextUtils.isEmpty(quantityStr) || TextUtils.isEmpty(priceStr) || imageBytes == null) {
            Toast.makeText(requireContext(), "Yêu cầu nhập đầy đủ dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(quantityStr);
        float price = Float.parseFloat(priceStr);

        Product product = new Product(name, type, quantity, price, imageBytes);

        FoodStoreDatabase.getInstance(requireContext()).productDAO().addProduct(product);
        Toast.makeText(requireContext(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
    }
}
