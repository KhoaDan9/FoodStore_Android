package com.example.foodstorezz.adapters;


import android.content.Context;
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
import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Product;
import com.example.foodstorezz.model.Cart;

import java.util.EventListener;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Product> mListProduct;
    private Cart cart = new Cart();
    public Context mContext;
    EventListener listener;
    public interface EventListener {
        void loadTotalBill();
    }
    public CartAdapter(Context context, EventListener listener){
        this.mContext = context;
        this.listener = listener;
    }
    public void setData(List<Product> list){
        this.mListProduct = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        int productId = cart.getProductIdByPosition(position);
        int numBuy = cart.getQuantityByPosition(position);
        Product product = null;
        for (Product p : mListProduct){
            if (p.getId() == productId){
               product = p;
            }
        }

        int price = (int) product.getPrice();
        int totalPrice = numBuy * price;

        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.valueOf(price));

        byte[] byteArray = product.getImageData();
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        holder.imgCart.setImageBitmap(bmp);

        holder.edtQuantity.setText(String.valueOf(numBuy));
        holder.tvTotalPrice.setText(String.valueOf( price * numBuy));

        holder.imgSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numBuy == 1) {
                    deleteCart(productId, totalPrice);
                }
                else {
                    cart.subtractCart(productId, price);
                }
                notifyDataSetChanged();

            }
        });

        holder.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.plusCart(productId, price);
                notifyDataSetChanged();
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCart(productId, totalPrice);
                notifyDataSetChanged();
            }

        });

        listener.loadTotalBill();

    }

    @Override
    public int getItemCount() {
        return cart.cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCart, imgPlus, imgSubtract, imgDelete;

        private TextView tvName, tvPrice, tvTotalPrice;

        private EditText edtQuantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name_cart);
            tvPrice = itemView.findViewById(R.id.tv_price2_cart);
            tvTotalPrice = itemView.findViewById(R.id.tv_total_price2);
            imgCart = itemView.findViewById(R.id.iv_img_cart);
            imgSubtract = itemView.findViewById(R.id.iv_sub_quantity_cart);
            imgPlus = itemView.findViewById(R.id.iv_plus_quantity_cart);
            imgDelete = itemView.findViewById(R.id.iv_delete_cart);
            edtQuantity = itemView.findViewById(R.id.edt_quantity_cart);
        }
    }

    private void deleteCart(int productId, int totalPrice){
        cart.removeCart(productId, totalPrice);
        listener.loadTotalBill();
    }
}
