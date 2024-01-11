package com.example.foodstorezz.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert
    void addProduct(Product product);

    @Update
    void updateProduct(Product product);

    @Query("SELECT * FROM product")
    List<Product> getAllProduct();

}
