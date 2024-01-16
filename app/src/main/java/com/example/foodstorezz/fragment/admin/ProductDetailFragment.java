package com.example.foodstorezz.fragment.admin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodstorezz.R;
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Product;
import com.example.foodstorezz.model.ProductDetail;

public class ProductDetailFragment extends Fragment {
    private EditText edtName, edtType, edtQuantity, edtPrice, edtImgSrc;
    private ImageView ivShowImg;
    private Button btnEditProduct, btnDeleteProduct;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        initUi(view);
        Product editProduct = ProductDetail.product;
        ProductDetail.product = null;

        setData(editProduct);

        btnEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditOnClick(editProduct);
            }
        });

        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDeleteProductOnClick(editProduct);
            }
        });

        return view;
    }

    private void initUi(View view){
        edtName = view.findViewById(R.id.edt_product_name_detail);
        edtType = view.findViewById(R.id.edt_product_type_detail);
        edtQuantity = view.findViewById(R.id.edt_product_quantity_detail);
        edtPrice = view.findViewById(R.id.edt_product_price_detail);

        ivShowImg = view.findViewById(R.id.iv_show_img_detail);

        btnEditProduct = view.findViewById(R.id.btn_edit_product);
        btnDeleteProduct = view.findViewById(R.id.btn_delete_product);
    }

    private void setData(Product product){
        edtName.setText(product.getName());
        edtType.setText(product.getType());
        edtQuantity.setText(String.valueOf(product.getQuantity()));
        edtPrice.setText(String.valueOf(product.getPrice()));
        byte[] byteArray = product.getImageData();

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);

        ivShowImg.setImageBitmap(bmp);
    }

    private void btnEditOnClick(Product product){
        product.setQuantity(Integer.parseInt(edtQuantity.getText().toString().trim()));
        product.setPrice(Float.parseFloat(edtPrice.getText().toString().trim()));

        FoodStoreDatabase.getInstance(requireContext()).productDAO().updateProduct(product);
        Toast.makeText(requireContext(), "Thay đổi thông tin sản phẩm thành công!",Toast.LENGTH_SHORT).show();
    }

    private void btnDeleteProductOnClick(Product product){
        FoodStoreDatabase.getInstance(requireContext()).productDAO().deleteProduct(product);
        Toast.makeText(requireContext(),"Xóa sản phẩm thành công!", Toast.LENGTH_SHORT).show();

        MenuFragment menuFragment = new MenuFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, menuFragment)
                .addToBackStack(null)
                .commit();
    }
}
