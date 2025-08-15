package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.dto.ChucVu;
import com.example.myapplication.model.NhanVien;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EditNhanVienActivity extends AppCompatActivity{
    private EditText edtMaNhanVien, edtTenNhanVien, edtDiaChi, edtLuong, edtMatKhau;
    private DatabaseHelper db;
    private int type;
    private List<ChucVu> chucVuList;
    private Spinner spChucVu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nhan_vien);

        db = new DatabaseHelper(this);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        edtMaNhanVien = findViewById(R.id.edtMaNhanVien);
        edtTenNhanVien = findViewById(R.id.edtTenNV);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtLuong = findViewById(R.id.edtLuong);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        findViewById(R.id.btnLuu).setOnClickListener(v->luuNhanVien());
        findViewById(R.id.btnHuy).setOnClickListener(v->finish());
        LinearLayout layoutMaNhanVien = findViewById(R.id.layoutMaNhanVien);
        spChucVu = findViewById(R.id.spChucVu);

        chucVuList = new ArrayList<>();
        chucVuList.add(new ChucVu(0,"Nhân viên"));
        chucVuList.add(new ChucVu(1,"Quản lý"));
        ArrayAdapter<ChucVu> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, chucVuList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spChucVu.setAdapter(adapter);

        type = getIntent().getIntExtra("Type", -1);
        if (type == 0){
            edtMaNhanVien.setEnabled(false);
            NhanVien nhanVien = getIntent().getParcelableExtra(QuanLyNhanVienActivity.NHAN_VIEN);
            edtMaNhanVien.setText(nhanVien.getMaNhanVien());
            edtTenNhanVien.setText(nhanVien.getTenNhanVien());
            edtDiaChi.setText(nhanVien.getDiaChi());
            edtLuong.setText(currencyFormat.format(nhanVien.getLuong()));
            edtMatKhau.setText(nhanVien.getMatKhau());
            setSelectedChucVu(nhanVien.getChucVu());
        }else if (type == 1){
            layoutMaNhanVien.setVisibility(View.GONE);
        }
    }

    private void setSelectedChucVu(int chucVu){
        for (int i = 0; i < chucVuList.size(); i++){
            if (chucVuList.get(i).getChucVuCode() == chucVu){
                spChucVu.setSelection(i);
                break;
            }
        }
    }

    private void luuNhanVien(){
        String maNhanVien = edtMaNhanVien.getText().toString().trim();
        String tenNhanVien = edtTenNhanVien.getText().toString().trim();
        String diaChi = edtDiaChi.getText().toString().trim();
        String matKhau = edtMatKhau.getText().toString().trim();
        int chucVu = spChucVu.getSelectedItemPosition();
        double luong = Double.parseDouble(edtLuong.getText().toString().replaceAll("[^\\d]", ""));

        boolean isOK;
        if (type == 0){
            NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien, diaChi, chucVu, luong, matKhau);
            isOK = db.suaNhanVien(nhanVien);
        }else {
            maNhanVien = db.taoMaNhanVienMoi();
            NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien, diaChi, chucVu, luong, matKhau);
            isOK = db.themNhanVien(nhanVien);
        }
        if (isOK){
            Toast.makeText(this, (type == 0) ? "Câp nhật: " : "Thêm" + " Thêm thành công", Toast.LENGTH_SHORT).show();}
        else {
            Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
