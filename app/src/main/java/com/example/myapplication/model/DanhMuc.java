package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DanhMuc implements Parcelable {
    private String MaDanhMuc;
    private String TenDanhMuc;

    public DanhMuc(){
    }

    public DanhMuc(String maDanhMuc, String tenDanhMuc) {
        MaDanhMuc = maDanhMuc;
        TenDanhMuc = tenDanhMuc;
    }

    protected DanhMuc(Parcel in) {
        MaDanhMuc = in.readString();
        TenDanhMuc = in.readString();
    }

    public static final Creator<DanhMuc> CREATOR = new Creator<DanhMuc>() {
        @Override
        public DanhMuc createFromParcel(Parcel in) {
            return new DanhMuc(in);
        }

        @Override
        public DanhMuc[] newArray(int size) {
            return new DanhMuc[size];
        }
    };

    public String getMaDanhMuc() {
        return MaDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        MaDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        TenDanhMuc = tenDanhMuc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(MaDanhMuc);
        dest.writeString(TenDanhMuc);
    }
}
