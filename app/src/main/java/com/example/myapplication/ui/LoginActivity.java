package com.example.myapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.common.Common;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.NhanVien;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edtUsername, edtPassword;
    CheckBox chkRemember;

    private SharedPreferences sharedPreferences;
    private DatabaseHelper db;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edt_login_username);
        edtPassword = findViewById(R.id.edt_login_password);
        chkRemember = findViewById(R.id.chkRemember);
        btnLogin = findViewById(R.id.btnLogin);
        db = new DatabaseHelper(this);
        SharedPreferences pefs = getSharedPreferences("remember", MODE_PRIVATE);
        boolean remember = pefs.getBoolean("remember", false);
        if (remember) {
            taoDuLieuNhanVien();
        }

//        btnLogin.setOnClickListener(v -> {
//            String username = edtUsername.getText().toString().trim();
//            String password = edtPassword.getText().toString().trim();
//            if (username.equals("") || password.equals("")) {
//                Toast.makeText(this, "Vui long khong bo trong du lieu", Toast.LENGTH_SHORT).show();
//            } else {
//                if (username.equals("1") && password.equals("1")) {
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });
    }

    public void taoDuLieuNhanVien(){
        DatabaseHelper db = new DatabaseHelper(this);
        db.themNhanVien(new NhanVien("NV1", "Phung QuangDunxng",
                "12 Kieu Mai Bac Tu Liem",1,30000000,"admin"));
        db.themNhanVien(new NhanVien("NV2", "Phung QuangDunxng",
                "12 Kieu Mai Bac Tu Liem",0,30000000,"sales"));
    }

    private void ghiNhoThongTinNguoiDung(){
        String savedUsername = sharedPreferences.getString("maNhanVien", "");
        String savedPassword = sharedPreferences.getString("matKhau", "");
        boolean remember = sharedPreferences.getBoolean("remember", false);

        edtUsername.setText(savedUsername);
        edtPassword.setText(savedPassword);
        chkRemember.setChecked(remember);
    }

    private void checkDangNhap(){
        String maNhanVien = edtUsername.getText().toString().trim();
        String matKhau = edtPassword.getText().toString().trim();
        NhanVien nv = db.layNhanVienBangMaNV(maNhanVien);

        if(matKhau.equals(nv.getMatKhau())){
            if (chkRemember.isChecked()){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("maNhanVien", maNhanVien);
                editor.putString("matKhau", matKhau);
                editor.putBoolean("remember", true);
                editor.apply();
            }else{
                sharedPreferences.edit().clear().apply();
            }
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("CHUC_VU", nv.getChucVu());
            startActivity(intent);
            Common.maNhanVien = maNhanVien;
            finish();
        }else{
            Toast.makeText(this, "Sai tai khoan hoac mat khau", Toast.LENGTH_SHORT).show();
        }
    }
}
