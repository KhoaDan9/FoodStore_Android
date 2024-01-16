package com.example.foodstorezz.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BillProductDAO {
    @Insert
    void addBillProduct(BillProduct billProduct);

    @Query("SELECT * FROM billproduct WHERE billId =:id")
    List<BillProduct> getAllBillProductByBillId(int id);

}

