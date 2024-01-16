package com.example.foodstorezz.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "billproduct")
public class BillProduct {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int billId;
    private int productId;

    private String productName;
    private int productPrice;
    private int productNumBuy;
    private int totalPrice;
    private byte[] imageData;

    public BillProduct(int billId, int productId, String productName, int productPrice, int productNumBuy, int totalPrice, byte[] imageData) {
        this.billId = billId;
        this.productName = productName;
        this.productId = productId;
        this.productPrice = productPrice;
        this.productNumBuy = productNumBuy;
        this.totalPrice = totalPrice;
        this.imageData = imageData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductNumBuy() {
        return productNumBuy;
    }

    public void setProductNumBuy(int productNumBuy) {
        this.productNumBuy = productNumBuy;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }


}
