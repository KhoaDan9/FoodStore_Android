package com.example.foodstorezz.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);
    @Query("Select * from user")
    List<User> getAllUser();

    @Query("Select * from user WHERE username= :username")
    User getUser(String username);
}
