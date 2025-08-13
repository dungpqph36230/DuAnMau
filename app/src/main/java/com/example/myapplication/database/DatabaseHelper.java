package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.myapplication.model.SanPham;
import com.example.myapplication.model.TopKhachHang;
import com.example.myapplication.model.TopSanPham;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TEN_CSDL = "JMart.db";
    public static final int PHIEN_BAN_CSDL = 23;
    public static final String BANG_DANHMUC = "DanhMuc";
    public static final String COT_TEN_DANHMUC = "Ten_danh_muc";
    public static final String COT_MA_DANHMUC = "Ma_danh_muc";
    public static final String BANG_SANPHAM = "SanPham";
    public static final String BANG_HOADON = "HoaDon";
    public static final String COLUMNN_MA_HOADON = "maHoaDon";
    public static final String COLUMNN_MA_NHANVIEN = "maNhanVien";
    public static final String COLUMNN_MA_KHACHHANG = "maKhachHang";
    public static final String COLUMNN_NGAYLAP = "ngayLap";
    public static final String COLUMNN_TONG_TIEN = "tongTien";
    public static final String COT_ID = "id";
    public static final String COT_MA_SP = "maSanPham";
    public static final String COT_TEN_SP = "tenSanPham";
    public static final String COT_GIA_SP = "giaSanPham";
    public static final String COT_DON_VI_TINH = "donViTinh";
    public static final String COT_NGAY_NHAP = "ngayNhap";
    public static final String BANG_NHANVIEN = "NhanVien";
    public static final String COT_MA_NHANVIEN = "maNhanVien";
    public static final String COT_TEN_NHANVIEN = "tenNhanVien";
    public static final String COT_DIA_CHI = "diaChi";
    public static final String COT_CHUC_VU = "chucVu";
    public static final String COT_LUONG = "luong";
    public static final String COT_MAT_KHAU = "matKhau";

    public static final String BANG_KHACHHANG = "KhachHang";
    public static final String COT_MA_KHACHHANG = "maKhachHang";
    public static final String COT_TEN_KHACHHANG = "tenKhachHang";
    public static final String COT_DIA_CHI_KH = "diaChi";
    public static final String COT_SDT = "soDienThoai";
    public static final String COT_EMAIL = "email";

    public static final String BANG_HOADONCHITIET = "HoaDonChiTiet";
    public static final String COT_MA_HDCT = "maHDCT";
    public static final String COT_MA_HOADON = "maHoaDon";
    public static final String COT_SO_LUONG = "soLuong";
    public static final String COT_DON_GIA = "donGia";





    public DatabaseHelper(Context context) {
        super(context, TEN_CSDL, null, PHIEN_BAN_CSDL);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TAO_BANG_DANHMUC = "CREATE TABLE IF NOT EXISTS " + BANG_DANHMUC + "("
                + COT_MA_DANHMUC + " TEXT PRIMARY KEY, "
                + COT_TEN_DANHMUC + " TEXT)";

        String TAO_BANG_SANPHAM = "CREATE TABLE IF NOT EXISTS " + BANG_SANPHAM + "("
                + COT_MA_SP + " TEXT PRIMARY KEY, "
                + COT_TEN_SP + " TEXT, "
                + COT_GIA_SP + " REAL, "
                + COT_DON_VI_TINH + " TEXT, "
                + COT_NGAY_NHAP + " TEXT,"
                + "FOREIGN KEY (" + COT_MA_DANHMUC + ") REFERENCES " + BANG_DANHMUC + "(" + COT_MA_DANHMUC +")"
                + ")";

        String TAO_BANG_NHANVIEN =
                "CREATE TABLE " + BANG_NHANVIEN + "("
                + COT_MA_NHANVIEN + " TEXT PRIMARY KEY, "
                + COT_TEN_NHANVIEN + " TEXT NOT NULL, "
                + COT_DIA_CHI + " TEXT, "
                + COT_CHUC_VU + " INT, "
                + COT_LUONG + " REAL NOT NULL, "
                + COT_MAT_KHAU + " TEXT NOT NULL)";

        String TAO_BANG_KHACHHANG =
                "CREATE TABLE " + BANG_KHACHHANG + "("
                + COT_MA_KHACHHANG + " TEXT PRIMARY KEY, "
                + COT_TEN_KHACHHANG + " TEXT NOT NULL, "
                + COT_DIA_CHI_KH + " TEXT, "
                + COT_SDT + " TEXT NOT NULL, "
                + COT_EMAIL + " TEXT NOT NULL)";

        String TAO_BANG_HOADON =
                "CREATE TABLE IF NOT EXISTS " + BANG_HOADON + "("
                + COLUMNN_MA_HOADON + " TEXT PRIMARY KEY, "
                + COLUMNN_MA_NHANVIEN + " TEXT, "
                + COLUMNN_MA_KHACHHANG + " TEXT, "
                + COLUMNN_NGAYLAP + " TEXT, "
                + COLUMNN_TONG_TIEN + "INT,"
                + "FOREIGN KEY(" + COLUMNN_MA_NHANVIEN + ") REFERENCES " + BANG_NHANVIEN + "(" + COLUMNN_MA_NHANVIEN +"),"
                + "FOREIGN KEY(" + COLUMNN_MA_KHACHHANG + ") REFERENCES " + BANG_KHACHHANG + "(" + COLUMNN_MA_KHACHHANG +")"
                + ")";

        String TAO_BANG_HOADONCHITIET =
                "CREATE TABLE " + BANG_HOADONCHITIET + "("
                + COT_MA_HDCT + " TEXT PRIMARY KEY, "
                + COT_MA_HOADON + " TEXT NOT NULL, "
                + COT_MA_SP + " TEXT NOT NULL, "
                + COT_SO_LUONG + " INT NOT NULL, "
                + COT_DON_GIA + " REAL NOT NULL,"
                + "FOREIGN KEY(" + COT_MA_HOADON + ") REFERENCES " + BANG_HOADON + "(" + COLUMNN_MA_HOADON +"),"
                + "FOREIGN KEY(" + COT_MA_SP + ") REFERENCES " + BANG_SANPHAM + "(" + COT_MA_SP +")"
                + ")";

        db.execSQL(TAO_BANG_DANHMUC);
        db.execSQL(TAO_BANG_SANPHAM);
        db.execSQL(TAO_BANG_NHANVIEN);
        db.execSQL(TAO_BANG_KHACHHANG);
        db.execSQL(TAO_BANG_HOADON);
        db.execSQL(TAO_BANG_HOADONCHITIET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + BANG_DANHMUC);
        db.execSQL("DROP TABLE IF EXISTS " + BANG_SANPHAM);
        db.execSQL("DROP TABLE IF EXISTS " + BANG_NHANVIEN);
        db.execSQL("DROP TABLE IF EXISTS " + BANG_KHACHHANG);
        db.execSQL("DROP TABLE IF EXISTS " + BANG_HOADON);
        db.execSQL("DROP TABLE IF EXISTS " + BANG_HOADONCHITIET);
        onCreate(db);

    }

    public boolean themSanPham(SanPham sanPham) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COT_MA_SP, sanPham.getMaSanPham());
        values.put(COT_TEN_SP, sanPham.getTenSanPham());
        values.put(COT_GIA_SP, sanPham.getGiaSanPham());
        values.put(COT_DON_VI_TINH, sanPham.getDonViTinh());
        values.put(COT_NGAY_NHAP, sanPham.getNgayNhap());
        values.put(COT_MA_DANHMUC, sanPham.getMaDanhMuc());
        long ketqua = db.insert(BANG_SANPHAM, null, values);
        db.close();
        return ketqua > 0;
    }

    public boolean xoaSanPham(String maSanPham) {
        SQLiteDatabase db = getWritableDatabase();
        int ketqua = db.delete(BANG_SANPHAM, COT_MA_SP + " = ?", new String[]{maSanPham});
        db.close();
        return ketqua > 0;
    }

    public boolean suaSanPham(SanPham sanPham) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COT_TEN_SP, sanPham.getTenSanPham());
        values.put(COT_GIA_SP, sanPham.getGiaSanPham());
        values.put(COT_DON_VI_TINH, sanPham.getDonViTinh());
        values.put(COT_NGAY_NHAP, sanPham.getNgayNhap());
        values.put(COT_MA_DANHMUC, sanPham.getMaDanhMuc());
        int ketqua = db.update(BANG_SANPHAM, values, COT_MA_SP + " = ?", new String[]{sanPham.getMaSanPham()});
        db.close();
        return ketqua > 0;
    }
    public List<SanPham> timKiemSanPham(String tenSP){
        List<SanPham> danhSachSP = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SanPham WHERE tenSanPham LIKE ?", new String[]{"%" + tenSP + "%"});
        if (cursor.moveToFirst()){
            do {
                SanPham sanPham = new SanPham(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
                danhSachSP.add(sanPham);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return danhSachSP;
    }

    public int layDoanhThu(String ngayBatDau, String ngayKetThuc){
        int doanhThu = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        String lenhTruyVan = "SELECT SUM(tongTien) FROM HoaDon WHERE ngayLap BETWEEN ? AND ?";
        try {
            SQLiteStatement statement = db.compileStatement(lenhTruyVan);
            statement.bindString(1, ngayBatDau);
            statement.bindString(2, ngayKetThuc);
            doanhThu = (int) statement.simpleQueryForLong();
        } catch (Exception e) {
//            e.printStackTrace();
        }finally {
            db.close();
        }
        return doanhThu;
    }

    public List<TopSanPham> thongKeTopSanPham(int e){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT sp.maSanPham, sp.tenSanPham, SUM(hdct.soLuong) AS tongSoLuong " + "FROM HoaDonChiTiet hdct " +
                "JOIN SanPham sp ON hdct.maSanPham = sp.maSanPham " +
                "GROUP BY sp.maSanPham, sp.tenSanPham " +
                "ORDER BY tongSoLuong DESC " +
                "LIMIT ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(e)});
        List<TopSanPham> list = new ArrayList<>();

        while (cursor.moveToNext()){
            String maSanPham = cursor.getString(cursor.getColumnIndexOrThrow("maSanPham"));
            String tenSanPham = cursor.getString(cursor.getColumnIndexOrThrow("tenSanPham"));
            int tongSoLuong = cursor.getInt(cursor.getColumnIndexOrThrow("tongSoLuong"));
            TopSanPham topSanPham = new TopSanPham(maSanPham, tenSanPham, tongSoLuong);
            list.add(topSanPham);
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<TopKhachHang> thongKeTopKhachHang(int e){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT kh.maKhachHang, kh.tenKhachHang, COUNT(hd.maHoaDon) AS soLanMua, SUM(hd.tongTien) AS tongChiTieu " +
                "FROM KhachHang kh " +
                "JOIN HoaDon hd ON kh.maKhachHang = hd.maKhachHang " +
                "GROUP BY kh.maKhachHang, kh.tenKhachHang " +
                "ORDER BY soLanMua DESC, tongChiTieu DESC " +
                "LIMIT ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(e)});
        List<TopKhachHang> list = new ArrayList<>();
        while (cursor.moveToNext()){
            String maKhachHang = cursor.getString(cursor.getColumnIndexOrThrow("maKhachHang"));
            String tenKhachHang = cursor.getString(cursor.getColumnIndexOrThrow("tenKhachHang"));
            int soLanMua = cursor.getInt(cursor.getColumnIndexOrThrow("soLanMua"));
            double tongChiTieu = cursor.getDouble(cursor.getColumnIndexOrThrow("tongChiTieu"));
            TopKhachHang topKhachHang = new TopKhachHang(maKhachHang, tenKhachHang, soLanMua, tongChiTieu);
            list.add(topKhachHang);
        }
        cursor.close();
        db.close();
        return list;
    }
}
