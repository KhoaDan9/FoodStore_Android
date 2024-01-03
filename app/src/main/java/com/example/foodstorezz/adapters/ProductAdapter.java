package com.example.foodstorezz.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodstorezz.R;
import com.example.foodstorezz.database.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> mListProduct;
    public void setData(List<Product> products) {
        this.mListProduct = products;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = mListProduct.get(position);
        if (product == null) {
            return;
        }
        String name = holder.tvName.getText().toString().trim();
        if (name.length() > 15)
            holder.tvName.setTextSize(10);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.valueOf((int)product.getPrice()) + " VNĐ");

        byte[] byteArray = product.getImageData();

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);

        holder.imgProduct.setImageBitmap(bmp);

    }

    @Override
    public int getItemCount() {
        if(mListProduct != null)
            return mListProduct.size();
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgProduct;
        private TextView tvName;
        private TextView tvPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.iv_product_img);
            tvName = itemView.findViewById(R.id.tv_product_name);
            tvPrice = itemView.findViewById(R.id.tv_product_price);
        }
    }

    public void convertByteArrayToBitmap(byte[] byteArray) {

    }
}
