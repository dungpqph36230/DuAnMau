package com.example.myapplication.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.common.Common;
import com.example.myapplication.database.DatabaseHelper;

import java.util.Objects;

public class DoiMatKhauActivity extends AppCompatActivity {
    private EditText edtOldPassword, edtNewPassword, edtConfirmPassword;
    private Button btnSave, btnCancel;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        edtOldPassword = findViewById(R.id.edtOldPasswrod);
        edtNewPassword = findViewById(R.id.edtNewPasswrod);
        edtConfirmPassword = findViewById(R.id.edtConfirmPasswrod);
        btnSave = findViewById(R.id.btnLuu);
        btnCancel = findViewById(R.id.btnHuy);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Đổi mật khẩu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        btnSave.setOnClickListener(v -> doiMatKhau());
        btnCancel.setOnClickListener(v->{
            edtOldPassword.setText("");
            edtNewPassword.setText("");
            edtConfirmPassword.setText("");
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {

    }

    private void doiMatKhau() {
        String matKhauCu = edtOldPassword.getText().toString().trim();
        String matKhauMoi = edtNewPassword.getText().toString().trim();
        String xacNhanMatKhau = edtConfirmPassword.getText().toString().trim();

        if (matKhauCu.isEmpty()|| matKhauMoi.isEmpty() || xacNhanMatKhau.isEmpty()){
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!matKhauMoi.equals(xacNhanMatKhau)){
            Toast.makeText(this, "Mật khẩu mới và xác nhận mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!db.kiemTraMatKhauCu(Common.maNhanVien, matKhauCu)){
            Toast.makeText(this, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
            return;
        }
        if (db.capNhatMatKhauMoi(Common.maNhanVien, matKhauMoi)){
            Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
