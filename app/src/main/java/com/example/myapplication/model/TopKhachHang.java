package com.example.myapplication.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TopKhachHang implements Parcelable {
    private String maKhachHang;
    private String tenKhachHang;
    private int soLanMua;
    private double tongChiTieu;

    public TopKhachHang(String maKhachHang, String tenKhachHang, int soLanMua, double tongChiTieu) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soLanMua = soLanMua;
        this.tongChiTieu = tongChiTieu;
    }

    protected TopKhachHang(Parcel in) {
        maKhachHang = in.readString();
        tenKhachHang = in.readString();
        soLanMua = in.readInt();
        tongChiTieu = in.readDouble();
    }

    public static final Creator<TopKhachHang> CREATOR = new Creator<TopKhachHang>() {
        @Override
        public TopKhachHang createFromParcel(Parcel in) {
            return new TopKhachHang(in);
        }

        @Override
        public TopKhachHang[] newArray(int size) {
            return new TopKhachHang[size];
        }
    };

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getSoLanMua() {
        return soLanMua;
    }

    public void setSoLanMua(int soLanMua) {
        this.soLanMua = soLanMua;
    }

    public double getTongChiTieu() {
        return tongChiTieu;
    }

    public void setTongChiTieu(double tongChiTieu) {
        this.tongChiTieu = tongChiTieu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(maKhachHang);
        dest.writeString(tenKhachHang);
        dest.writeInt(soLanMua);
        dest.writeDouble(tongChiTieu);
    }
}
