package com.example.foodstorezz.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "bill")
public class Bill {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int staffId;
    private String staffName;
    private String date;
    private int totalBill;

    public Bill() {
    }

    public Bill(int staffId, String staffName, String date, int totalBill) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.date = date;
        this.totalBill = totalBill;
    }

    public int getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
