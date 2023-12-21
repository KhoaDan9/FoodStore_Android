package com.example.foodstorezz.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StaffDAO {
    @Insert
    void addStaff(Staff staff);
    @Update
    void updateStaff(Staff staff);
    @Delete
    void deleteStaff(Staff staff);

    @Query("SELECT * FROM staff WHERE username =:username")
    Staff getStaffbyUsername(String username);
    @Query("SELECT * FROM staff")
    List<Staff> getAll();


}
