package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NhanVienAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class QuanLyNhanVienActivity extends AppCompatActivity implements NhanVienAdapter.OnNhanVienClickListener {

   public static final String NHAN_VIEN = "NHAN_VIEN";
   private NhanVienAdapter nhanVienAdapter;
   private List<NhanVien> danhSachNhanViens;
   private DatabaseHelper db;

   @Override
   public boolean onSupportNavigateUp() {
       finish();
       return true;
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_quan_ly_nhan_vien);

       Toolbar toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       ListView lvNhanVien = findViewById(R.id.lvNhanVien);

       Objects.requireNonNull(getSupportActionBar()).setTitle("Quản lý nhân viên");
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       db = new DatabaseHelper(this);
       FloatingActionButton fabThemNhanVien = findViewById(R.id.fabThemNhanVien);

       danhSachNhanViens = db.latTatCaNhanVien();
       nhanVienAdapter = new NhanVienAdapter(this, danhSachNhanViens);
       nhanVienAdapter.setOnNhanVienClickListener(this);
       lvNhanVien.setAdapter(nhanVienAdapter);
       fabThemNhanVien.setOnClickListener(v->{
           Intent intent = new Intent(this, EditNhanVienActivity.class);
           intent.putExtra("Type",1);
           this.startActivity(intent);
       });
   }

   @Override
   protected void onResume() {
       super.onResume();
       danhSachNhanViens.clear();
       danhSachNhanViens.addAll(db.latTatCaNhanVien());
       nhanVienAdapter.notifyDataSetChanged();

   }

    @Override
    public void onEditNhanVien(NhanVien nhanVien) {
        Intent intent = new Intent(this, EditNhanVienActivity.class);
        intent.putExtra("Type",0);
        intent.putExtra(NHAN_VIEN, nhanVien);
        startActivity(intent);
    }

    @Override
    public void onDeleteNhanVien(NhanVien nhanVien) {
        DatabaseHelper db = new DatabaseHelper(this);
        boolean isDeleted = db.xoaNhanVien(nhanVien.getMaNhanVien());
        if (isDeleted){
            danhSachNhanViens.remove(nhanVien);
            nhanVienAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
