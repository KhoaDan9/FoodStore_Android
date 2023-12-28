package com.example.foodstorezz.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import  androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class, Staff.class, Bill.class, Product.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class FoodStoreDatabase extends RoomDatabase{
    public static final String DATABASE_NAME = "FoodStore.db";

    private static FoodStoreDatabase instance;

    public static synchronized FoodStoreDatabase getInstance(Context context){
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), FoodStoreDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        return instance;
    }
    public abstract UserDAO userDAO();
    public abstract StaffDAO staffDAO();
    public abstract ProductDAO productDAO();

}
