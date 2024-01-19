package com.example.foodstorezz.database;

import androidx.room.Dao;
import androidx.room.Delete;
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

    @Delete
    void deleteProduct(Product product);

    @Query("SELECT * FROM product")
    List<Product> getAllProduct();

    @Query("SELECT DISTINCT type FROM product ORDER BY type ASC")
    List<String> getAllType();

    @Query("SELECT * FROM product WHERE type LIKE :type")
    List<Product> getProductByType(String type);

    @Query("SELECT * FROM product WHERE name LIKE '%'||:name||'%' ORDER BY name ASC")
    List<Product> searchProduct(String name);

    @Query("SELECT * FROM product WHERE name LIKE '%'||:name||'%' AND type LIKE :type ORDER BY name ASC")
    List<Product> searchProductWithType(String name, String type);

}
