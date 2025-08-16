package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.ui.DoiMatKhauActivity;
import com.example.myapplication.ui.LoginActivity;
import com.example.myapplication.ui.QuanLyDanhMucActivity;
import com.example.myapplication.ui.QuanLyHoaDonActivity;
import com.example.myapplication.ui.QuanLyKhachHangActivity;
import com.example.myapplication.ui.QuanLyNhanVienActivity;
import com.example.myapplication.ui.QuanLySanPhamActivity;
import com.example.myapplication.ui.ThongKeDoanhThuActivity;
import com.example.myapplication.ui.ThongKeKhachHangActivity;
import com.example.myapplication.ui.ThongKeSanPhamActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    int chucVu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setPopupTheme(R.style.DrakPopupMenu);

        findViewById(R.id.lnDoanhThu).setOnClickListener(this);
        findViewById(R.id.lnTopSanPham).setOnClickListener(this);
        findViewById(R.id.lnTopKhachHang).setOnClickListener(this);
        findViewById(R.id.lnSanPham).setOnClickListener(this);
        findViewById(R.id.lnDoiMatKhau).setOnClickListener(this);
        findViewById(R.id.lnKhachHang).setOnClickListener(this);
        findViewById(R.id.lnHoaDon).setOnClickListener(this);
        findViewById(R.id.lnDanhMuc).setOnClickListener(this);
        findViewById(R.id.lnNhanVien).setOnClickListener(this);
        findViewById(R.id.lnDangXuat).setOnClickListener(this);

        chucVu = getIntent().getIntExtra("chucVu", 0);
        if(chucVu == 0){
            findViewById(R.id.lnThongKe).setVisibility(View.GONE);
            findViewById(R.id.lnNhanVien).setVisibility(View.GONE);
        }
    }

//   @Override
//   public boolean onCreareOptionsMenu(Menu menu) {
//       getMenuInflater().inflate(R.menu.menu_main, menu);
//       if (chucVu == 0){
//           MenuItem doanhsThuItem = menu.findItem(R.id.menu_doanhThu);
//           if(doanhsThuItem != null){
//               doanhsThuItem.setVisible(false);
//           }
//           MenuItem doiMatKhauItem = menu.findItem(R.id.menu_top_san_pham);
//           if(doiMatKhauItem != null){
//               doiMatKhauItem.setVisible(false);
//           }
//           MenuItem topSanPhamItem = menu.findItem(R.id.menu_top_khach_hang);
//           if(topSanPhamItem != null){
//               topSanPhamItem.setVisible(false);
//           }
//       }
//       return true;
//   }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.menu_doanh_thu) {
//            startActivity(new Intent(MainActivity.this, ThongKeDoanhThuActivity.class));
//            return true;
//        }else if (id == R.id.menu_top_san_pham) {
//            startActivity(new Intent(MainActivity.this, ThongKeSanPhamActivity.class));
//            return true;
//        }else if (id == R.id.menu_top_khach_hang) {
//            startActivity(new Intent(MainActivity.this, ThongKeSanPhamActivity.class));
//            return true;
//        }else if (id == R.id.menu_doi_mat_khau) {
//            startActivity(new Intent(MainActivity.this, DoiMatKhauActivity.class));
//            return true;
//        }else if (id == R.id.menu_dang_xuat) {
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.lnDoanhThu) {
            startActivity(new Intent(MainActivity.this, ThongKeDoanhThuActivity.class));
        }else if (id == R.id.lnTopSanPham) {
            startActivity(new Intent(MainActivity.this, ThongKeSanPhamActivity.class));
        }else if (id == R.id.lnTopKhachHang) {
            startActivity(new Intent(MainActivity.this, ThongKeKhachHangActivity.class));
        }else if (id == R.id.lnSanPham) {
            startActivity(new Intent(MainActivity.this, QuanLySanPhamActivity.class));
        }else if (id == R.id.lnKhachHang) {
            startActivity(new Intent(MainActivity.this, QuanLyKhachHangActivity.class));
        }else if (id == R.id.lnHoaDon) {
            startActivity(new Intent(MainActivity.this, QuanLyHoaDonActivity.class));
        }else if (id == R.id.lnDanhMuc) {
            startActivity(new Intent(MainActivity.this, QuanLyDanhMucActivity.class));
        }else if (id == R.id.lnNhanVien) {
            startActivity(new Intent(MainActivity.this, QuanLyNhanVienActivity.class));
        }else if (id == R.id.lnDoiMatKhau) {
            startActivity(new Intent(MainActivity.this, DoiMatKhauActivity.class));
        }else if (id == R.id.lnDangXuat) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}