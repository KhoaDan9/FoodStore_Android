package com.example.foodstorezz.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.foodstorezz.database.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    public static Map<Integer, Integer> cartList = new HashMap<>();
    private Object keys[];

    private static float totalPrice;

    public static float getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(float totalPrice) {
        Cart.totalPrice = totalPrice;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addCart (Product product){
        Integer quantity = cartList.getOrDefault(product.getId(), 0);
        if (quantity >= product.getQuantity()) return;
        cartList.put(product.getId(), quantity + 1);
        totalPrice += product.getPrice();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void removeCart(Product p){
        Integer quantity = cartList.getOrDefault(p.getId(), 0);
        if (quantity <=0) return;
        cartList.put(p.getId(), quantity - 1);
        totalPrice -= p.getPrice();
    }
}
