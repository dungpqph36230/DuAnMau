package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.myapplication.model.KhackHang;
import com.example.myapplication.model.NhanVien;
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

    public List<SanPham> latTatCaSanPham(){
        List<SanPham> danhSachSanPham = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + BANG_SANPHAM;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                SanPham sp = new SanPham();
                sp.setMaSanPham(String.valueOf(cursor.getColumnIndexOrThrow(COT_MA_SP)));
                sp.setTenSanPham(String.valueOf(cursor.getColumnIndexOrThrow(COT_TEN_SP)));
                sp.setGiaSanPham(cursor.getInt(cursor.getColumnIndexOrThrow(COT_GIA_SP)));
                sp.setDonViTinh(String.valueOf(cursor.getColumnIndexOrThrow(COT_DON_VI_TINH)));
                sp.setNgayNhap(String.valueOf(cursor.getColumnIndexOrThrow(COT_NGAY_NHAP)));
                danhSachSanPham.add(sp);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return danhSachSanPham;
    }

    public List<KhackHang> latTatCaKhachHang(){
        List<KhackHang> danhSachKhachHang = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + BANG_KHACHHANG;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                KhackHang kh = new KhackHang();
                kh.setMaKhachHang(String.valueOf(cursor.getColumnIndexOrThrow(COT_MA_KHACHHANG)));
                kh.setTenKhachHang(String.valueOf(cursor.getColumnIndexOrThrow(COT_TEN_KHACHHANG)));
                kh.setDiaChi(String.valueOf(cursor.getColumnIndexOrThrow(COT_DIA_CHI_KH)));
                kh.setSoDienThoai(String.valueOf(cursor.getColumnIndexOrThrow(COT_SDT)));
                kh.setEmail(String.valueOf(cursor.getColumnIndexOrThrow(COT_EMAIL)));
                danhSachKhachHang.add(kh);
                }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return danhSachKhachHang;
    }

    public List<NhanVien> latTatCaNhanVien(){
        List<NhanVien> danhSachNhanViens = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + BANG_NHANVIEN;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(String.valueOf(cursor.getColumnIndexOrThrow(COT_MA_NHANVIEN)));
                nv.setTenNhanVien(String.valueOf(cursor.getColumnIndexOrThrow(COT_TEN_NHANVIEN)));
                nv.setDiaChi(String.valueOf(cursor.getColumnIndexOrThrow(COT_DIA_CHI)));
                nv.setChucVu(cursor.getInt(cursor.getColumnIndexOrThrow(COT_CHUC_VU)));
                nv.setLuong(cursor.getDouble(cursor.getColumnIndexOrThrow(COT_LUONG)));
                nv.setMatKhau(String.valueOf(cursor.getColumnIndexOrThrow(COT_MAT_KHAU)));
                danhSachNhanViens.add(nv);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return danhSachNhanViens;
    }
    public NhanVien layNhanVienBangMaNV(String maNhanVien){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+ BANG_NHANVIEN +" WHERE "+ COT_MA_NHANVIEN +" = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{maNhanVien});
        NhanVien nv = new NhanVien();
        if (cursor.moveToFirst()){
            nv.setMaNhanVien(String.valueOf(cursor.getColumnIndexOrThrow(COT_MA_NHANVIEN)));
            nv.setTenNhanVien(String.valueOf(cursor.getColumnIndexOrThrow(COT_TEN_NHANVIEN)));
            nv.setDiaChi(String.valueOf(cursor.getColumnIndexOrThrow(COT_DIA_CHI)));
            nv.setChucVu(cursor.getInt(cursor.getColumnIndexOrThrow(COT_CHUC_VU)));
            nv.setLuong(cursor.getDouble(cursor.getColumnIndexOrThrow(COT_LUONG)));
            nv.setMatKhau(String.valueOf(cursor.getColumnIndexOrThrow(COT_MAT_KHAU)));
        }
        cursor.close();
        db.close();
        return nv;
    }
    public double layDonGiaSanPham(String maSanPham){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT giaSanPham FROM SanPham WHERE maSanPham = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{maSanPham});
        double donGia = 0;
        if (cursor.moveToFirst()){
            donGia = cursor.getDouble(0);
        }
        cursor.close();
        db.close();
        return donGia;
    }
    public void capNhatTongTienHoaDon(String maHoaDon, double tongTien){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE HoaDon SET tongTien = ? WHERE maHoaDon = ?";
        db.execSQL(sql, new Object[]{tongTien, maHoaDon});
        db.close();
    }
    public String taoMaDanhMucMoi(){
        SQLiteDatabase db = this.getReadableDatabase();
        String maDanhMucMoi = "DM1";
        String sql = "SELECT "+COT_MA_DANHMUC+" FROM " + BANG_DANHMUC + " ORDER BY " + COT_MA_DANHMUC + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            String lastMaDanhMuc = cursor.getString(0);
            int lastNumber = Integer.parseInt(lastMaDanhMuc.replace("DM",""));
            maDanhMucMoi = "DM" + (lastNumber + 1);
        }
        cursor.close();
        db.close();
        return maDanhMucMoi;
    }
    public String taoMaSanPhamMoi(){
        SQLiteDatabase db = this.getReadableDatabase();
        String maSanPhamMoi = "SP1";
        String sql = "SELECT "+COT_MA_SP+" FROM " + BANG_SANPHAM + " ORDER BY " + COT_MA_SP + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            String lastMaSanPham = cursor.getString(0);
            int lastNumber = Integer.parseInt(lastMaSanPham.replace("SP",""));
            maSanPhamMoi = "SP" + (lastNumber + 1);
        }
        cursor.close();
        db.close();
        return maSanPhamMoi;
    }
    public String taoMaNhanVienMoi(){
        SQLiteDatabase db = this.getReadableDatabase();
        String maNhanVienMoi = "NV1";
        String sql = "SELECT "+COT_MA_NHANVIEN+" FROM " + BANG_NHANVIEN + " ORDER BY " + COT_MA_NHANVIEN + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            String lastMaNhanVien = cursor.getString(0);
            int lastNumber = Integer.parseInt(lastMaNhanVien.replace("NV",""));
            maNhanVienMoi = "NV" + (lastNumber + 1);
        }
        cursor.close();
        db.close();
        return maNhanVienMoi;
    }
    public String taoMaKhachHangMoi(){
        SQLiteDatabase db = this.getReadableDatabase();
        String maKhachHangMoi = "KH1";
        String sql = "SELECT "+COT_MA_KHACHHANG+" FROM " + BANG_KHACHHANG + " ORDER BY " + COT_MA_KHACHHANG + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            String lastMaKhachHang = cursor.getString(0);
            int lastNumber = Integer.parseInt(lastMaKhachHang.replace("KH",""));
            maKhachHangMoi = "KH" + (lastNumber + 1);
        }
        cursor.close();
        db.close();
        return maKhachHangMoi;
    }

    public boolean suaNhanVien(NhanVien nv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COT_TEN_NHANVIEN, nv.getTenNhanVien());
        values.put(COT_DIA_CHI, nv.getDiaChi());
        values.put(COT_CHUC_VU, nv.getChucVu());
        values.put(COT_LUONG, nv.getLuong());
        values.put(COT_MAT_KHAU, nv.getMatKhau());
        int ketqua = db.update(BANG_NHANVIEN, values, COT_MA_NHANVIEN + " = ?", new String[]{nv.getMaNhanVien()});
        db.close();
        return ketqua > 0;
    }
    public boolean themNhanVien (NhanVien nv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COT_MA_NHANVIEN, nv.getMaNhanVien());
        values.put(COT_TEN_NHANVIEN, nv.getTenNhanVien());
        values.put(COT_DIA_CHI, nv.getDiaChi());
        values.put(COT_CHUC_VU, nv.getChucVu());
        values.put(COT_LUONG, nv.getLuong());
        values.put(COT_MAT_KHAU, nv.getMatKhau());
        long ketqua = db.insert(BANG_NHANVIEN, null, values);
        db.close();
        return ketqua > 0;
    }
    public boolean xoaNhanVien(String maNhanVien){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM NhanVien WHERE maNhanVien = ?";
        db.execSQL(sql, new Object[]{maNhanVien});
        db.close();
        return true;
    }
    public boolean xoaHDCT(String maHDCT){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM HoaDonChiTiet WHERE maHDCT = ?";
        db.execSQL(sql, new Object[]{maHDCT});
        db.close();
        return true;
    }
    public boolean suaHDCT(String maHDCT,String maHoaDon, String maSanPham, int soLuong, double donGia){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE HoaDonChiTiet SET maHoaDon = ?, maSanPham = ?, soLuong = ?, donGia = ? WHERE maHDCT = ?";
        db.execSQL(sql, new Object[]{maHoaDon, maSanPham, soLuong, donGia, maHDCT});
        db.close();
        return true;
    }
    public boolean kiemTraMatKhauCu(String maNhanVien, String matKhauCu){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT matKhau FROM NhanVien WHERE maNhanVien = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{maNhanVien, matKhauCu});
        if (cursor.moveToFirst()){
            String matKhauHienTai = cursor.getString(0);
            cursor.close();
            db.close();
            return matKhauHienTai.equals(matKhauCu);
        }
        cursor.close();
        db.close();
        return false;
    }
    public boolean capNhatMatKhauMoi(String maNhanVien, String matKhauMoi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matKhau", matKhauMoi);
        int ketqua = db.update("NhanVien", values, "maNhanVien" + " = ?", new String[]{maNhanVien});
        db.close();
        return ketqua > 0;
    }
    public List<SanPham> layDanhSachSanPham() {
        List<SanPham> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM SanPham";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String maSanPham = cursor.getString(cursor.getColumnIndexOrThrow("maSanPham"));
            String tenSanPham = cursor.getString(cursor.getColumnIndexOrThrow("tenSanPham"));
            int giaSanPham = cursor.getInt(cursor.getColumnIndexOrThrow("giaSanPham"));
            int soLuong = cursor.getInt(cursor.getColumnIndexOrThrow("soLuong"));
            String donViTinh = cursor.getString(cursor.getColumnIndexOrThrow("donViTinh"));
            String ngayNhap = cursor.getString(cursor.getColumnIndexOrThrow("ngayNhap"));
            String maDanhMuc = cursor.getString(cursor.getColumnIndexOrThrow("maDanhMuc"));
            SanPham sanPham = new SanPham(maSanPham, tenSanPham, giaSanPham, soLuong, donViTinh, ngayNhap, maDanhMuc);
            list.add(sanPham);
        }
        cursor.close();
        db.close();
        return list;
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
