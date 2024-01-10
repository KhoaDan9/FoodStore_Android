package com.example.foodstorezz.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "staff")
public class Staff implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private String username;
    private String password;
    private String fullname;
    private String address;
    private String dateofbirth;
    private String phonenumber;
    private String cccd;
    private String ngaytao;


    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    protected Staff(Parcel in) {
        id = in.readInt();
        username = in.readString();
        password = in.readString();
        fullname = in.readString();
        address = in.readString();
        dateofbirth = in.readString();
        phonenumber = in.readString();
        cccd = in.readString();
    }

    public static final Creator<Staff> CREATOR = new Creator<Staff>() {
        @Override
        public Staff createFromParcel(Parcel in) {
            return new Staff(in);
        }

        @Override
        public Staff[] newArray(int size) {
            return new Staff[size];
        }
    };

    public Staff(String username, String password, String fullname, String address, String dateofbirth, String phonenumber, String cccd) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.dateofbirth = dateofbirth;
        this.phonenumber = phonenumber;
        this.cccd = cccd;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(fullname);
        dest.writeString(address);
        dest.writeString(dateofbirth);
        dest.writeString(phonenumber);
        dest.writeString(cccd);
    }
}
