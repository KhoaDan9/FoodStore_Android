package com.example.foodstorezz.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface ProductDAO {
    @Insert
    void addProduct(Product product);

    @Update
    void updateProduct(Product product);
}
