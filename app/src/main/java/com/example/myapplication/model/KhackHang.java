package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class KhackHang implements Parcelable {
    private String maKhachHang;
    private String tenKhachHang;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private double tongChiTieu;
    private int avatar;

    public KhackHang() {
    }

    public KhackHang(String maKhachHang, String tenKhachHang, String diaChi, String soDienThoai, String email, double tongChiTieu, int avatar) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.tongChiTieu = tongChiTieu;
        this.avatar = avatar;
    }

    protected KhackHang(Parcel in) {
        maKhachHang = in.readString();
        tenKhachHang = in.readString();
        diaChi = in.readString();
        soDienThoai = in.readString();
        email = in.readString();
        tongChiTieu = in.readDouble();
        avatar = in.readInt();
    }

    public static final Creator<KhackHang> CREATOR = new Creator<KhackHang>() {
        @Override
        public KhackHang createFromParcel(Parcel in) {
            return new KhackHang(in);
        }

        @Override
        public KhackHang[] newArray(int size) {
            return new KhackHang[size];
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTongChiTieu() {
        return tongChiTieu;
    }

    public void setTongChiTieu(double tongChiTieu) {
        this.tongChiTieu = tongChiTieu;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(maKhachHang);
        dest.writeString(tenKhachHang);
        dest.writeString(diaChi);
        dest.writeString(soDienThoai);
        dest.writeString(email);
        dest.writeDouble(tongChiTieu);
        dest.writeInt(avatar);
    }
}
