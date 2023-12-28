package com.example.foodstorezz.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "bill")
public class Bill {
    @PrimaryKey
    private int id;
    private int staffId;
    private String staffName;
    private Date date;

    public Bill(int id, int staffId, String staffName, Date date) {
        this.id = id;
        this.staffId = staffId;
        this.staffName = staffName;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
