package com.example.foodstorezz.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodstorezz.R;
import com.example.foodstorezz.database.BillProduct;
import com.example.foodstorezz.database.Product;

import java.util.List;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.PayViewHolder> {
    List<BillProduct> mListBillProduct;

    public void setData(List<BillProduct> billProducts){
        this.mListBillProduct = billProducts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill, parent, false);
        return new PayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayViewHolder holder, int position) {
        BillProduct product = mListBillProduct.get(position);
        if (product == null) {
            return;
        }

        holder.tvProductName.setText(product.getProductName());
        holder.tvPrice.setText(String.valueOf((int)product.getProductPrice()) + " VNĐ");
        holder.tvTotalPrice.setText(String.valueOf(product.getTotalPrice()));
        holder.edtQuantity.setText(String.valueOf(product.getProductNumBuy()));

        byte[] byteArray = product.getImageData();
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        holder.ivProduct.setImageBitmap(bmp);

    }

    @Override
    public int getItemCount() {
        if(mListBillProduct == null)
            return 0;
        return mListBillProduct.size();
    }

    public class PayViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivProduct;
        private TextView tvProductName, tvPrice, tvTotalPrice;
        private EditText edtQuantity;

        public PayViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.iv_img_bill);
            tvProductName = itemView.findViewById(R.id.tv_name_bill);
            tvPrice = itemView.findViewById(R.id.tv_price2_bill);
            tvTotalPrice = itemView.findViewById(R.id.tv_total_price2);
            edtQuantity = itemView.findViewById(R.id.edt_quantity2_bill);
        }
    }
}
