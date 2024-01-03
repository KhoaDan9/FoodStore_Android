package com.example.foodstorezz.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodstorezz.R;
import com.example.foodstorezz.database.Product;
import com.example.foodstorezz.model.Cart;

import java.util.List;

public class ProductStaffAdapter extends RecyclerView.Adapter<ProductStaffAdapter.ProductStaffViewHolder>{
    private List<Product> mListProduct;
    private Cart cart = new Cart();

    public void setData(List<Product> list) {
        this.mListProduct = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductStaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_staff,parent,false);
        return new ProductStaffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductStaffViewHolder holder, int position) {
        Product product = mListProduct.get(position);
        if(product == null)
            return;

        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.valueOf((int)product.getPrice()) + " VNĐ");


        byte[] byteArray = product.getImageData();
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray,0, byteArray.length);
        holder.imgProduct.setImageBitmap(bmp);

        holder.btnAddMenu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                int numBuy = Integer.parseInt(holder.edtQuantity.getText().toString());
                addToCart(product, numBuy);
            }
        });

        holder.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.edtQuantity.setText(String.valueOf(Integer.parseInt(holder.edtQuantity.getText().toString())+1));
            }
        });

        holder.imgSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numBuy = Integer.parseInt(holder.edtQuantity.getText().toString());
                if(numBuy > 1)
                    holder.edtQuantity.setText(String.valueOf(numBuy - 1));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListProduct != null){
            return mListProduct.size();
        }
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addToCart(Product product, int numBuy) {
        cart.addToCart(product, numBuy);
    }


    public class ProductStaffViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvPrice;
        private ImageView imgProduct, imgSubtract, imgPlus;
        private EditText edtQuantity;
        private Button btnAddMenu;

        public ProductStaffViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_product_name);
            tvPrice = itemView.findViewById(R.id.tv_product_price);
            imgProduct = itemView.findViewById(R.id.iv_product_img);
            imgSubtract = itemView.findViewById(R.id.iv_sub_quantity);
            imgPlus = itemView.findViewById(R.id.iv_plus_quantity);
            edtQuantity = itemView.findViewById(R.id.edt_quantity);
            btnAddMenu = itemView.findViewById(R.id.btn_add_menu);
        }
    }
}
