package com.example.foodstorezz.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.foodstorezz.database.FoodStoreDatabase;
import com.example.foodstorezz.database.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    public static Map<Integer, Integer> cartList = new HashMap<>();
    private Object keys[];

    private static int totalBill;

    public static int getTotalBill() {
        return totalBill;
    }

    public static void setTotalBill(int totalBill) {
        Cart.totalBill = totalBill;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addToCart (Product product, int numBuy){
        Integer quantity = cartList.getOrDefault(product.getId(), 0);
        if (quantity >= product.getQuantity()) return;
        cartList.put(product.getId(), numBuy);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void removeCart(Product p){
        Integer quantity = cartList.getOrDefault(p.getId(), 0);
        if (quantity <=0) return;
        cartList.put(p.getId(), quantity - 1);
    }

    public int getProductIdByPosition(Integer position){
        keys = cartList.keySet().toArray();
        return (int) keys[position];
    }

    public int getQuantityByPosition(Integer position){
        keys = cartList.keySet().toArray();
        return cartList.get(keys[position]);
    }

    public void removeProduct(int key, int totalPrice){
        totalBill -= totalPrice;
        cartList.remove(key);
    }

    public void plusCart(int productId, int price) {
        int numBuy = cartList.get(productId);
        totalBill += price;
        cartList.put(productId, numBuy + 1);
    }
    public void subtractCart(int productId, int price) {
        int numBuy = cartList.get(productId);
        totalBill -= price;
        cartList.put(productId, numBuy - 1);
    }
}
