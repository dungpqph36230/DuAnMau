package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.adapter.TopSanPhamAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.TopSanPham;

import java.util.List;
import java.util.Objects;

public class ThongKeSanPhamActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private ListView lstViewSanPham;
    private EditText edtNgayBatDau, edtNgayKetThuc, edtSoLuong;
    private TextView tvTopSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_san_pham);
        db = new DatabaseHelper(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Thống kê sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lstViewSanPham = findViewById(R.id.lvSanPham);
        edtNgayBatDau = findViewById(R.id.edtNgayBatDau);
        edtNgayKetThuc = findViewById(R.id.edtNgayKetThuc);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        tvTopSanPham = findViewById(R.id.tvTopSanPham);
        edtNgayBatDau.setOnClickListener(v -> {showDatePickerDialog(edtNgayBatDau);});
        edtNgayKetThuc.setOnClickListener(v -> {showDatePickerDialog(edtNgayKetThuc);});

        findViewById(R.id.btnThongKeDT).setOnClickListener(v -> {
            if (edtNgayBatDau.getText().toString().isEmpty()
                    || edtNgayKetThuc.getText().toString().isEmpty()
                    || edtSoLuong.getText().toString().isEmpty()) {
                tvTopSanPham.setText("Vui lòng nhập đầy đủ thông tin");
                tvTopSanPham.setVisibility(View.VISIBLE);
                lstViewSanPham.setVisibility(View.GONE);
                return;
            } else {
                tvTopSanPham.setVisibility(View.GONE);
                lstViewSanPham.setVisibility(View.VISIBLE);
            }
            hienThiTopSanPham(Integer.parseInt(edtSoLuong.getText().toString()));
        });
    }

    private void hienThiTopSanPham(int i) {
        List<TopSanPham> topSanPhamList = db.thongKeTopSanPham(i);
        if (topSanPhamList != null && !topSanPhamList.isEmpty()) {
            TopSanPhamAdapter adapter = new TopSanPhamAdapter(this, topSanPhamList);
            lstViewSanPham.setAdapter(adapter);
        }else {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDatePickerDialog(EditText edtNgayBatDau) {
    }
}
