package com.example.foodstorezz.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;

@Dao
public interface BillDAO {
    @Insert
    void addBill(Bill bill);

    @Update
    void updateBill(Bill bill);

    @Query("SELECT * FROM bill ORDER BY id DESC")
    Bill getNewBill();

    @Query("SELECT * FROM bill WHERE id=:id")
    Bill getBillById(int id);
    @Query("SELECT * FROM bill WHERE staffName=:name")
    Bill getBillByStaffName(String name);

    @Query("SELECT * FROM bill WHERE staffId=:id")
    Bill getBillByStaffId(int id);

    @Query("SELECT * FROM bill WHERE date >= :startDate AND date <= :endDate")
    Bill getBillByStaffName(Date startDate, Date endDate);

}