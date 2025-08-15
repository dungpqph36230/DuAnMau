package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TopSanPham implements Parcelable {
    private String maSanPham;
    private String tenSanPham;
    private int tongSoLuong;

    public TopSanPham(String maSanPham, String tenSanPham, int tongSoLuong) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tongSoLuong = tongSoLuong;
    }

    protected TopSanPham(Parcel in) {
        maSanPham = in.readString();
        tenSanPham = in.readString();
        tongSoLuong = in.readInt();
    }

    public static final Creator<TopSanPham> CREATOR = new Creator<TopSanPham>() {
        @Override
        public TopSanPham createFromParcel(Parcel in) {
            return new TopSanPham(in);
        }

        @Override
        public TopSanPham[] newArray(int size) {
            return new TopSanPham[size];
        }
    };

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }

    public void setTongSoLuong(int tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(maSanPham);
        dest.writeString(tenSanPham);
        dest.writeInt(tongSoLuong);
    }
}
