package com.example.myapplication.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.R;

import java.util.Calendar;

public class ThongKeDoanhThuActivity extends AppCompatActivity {
    private EditText edtTuNgayBatDau, edtDenNgayKetThuc;
    private DatabaseHelper db;
    private TextView tvDoanhThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_doanh_thu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thống kê doanh thu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHelper(this);

        edtTuNgayBatDau = findViewById(R.id.edtNgayBatDau);
        edtDenNgayKetThuc = findViewById(R.id.edtNgayKetThuc);
        tvDoanhThu = findViewById(R.id.tvDoanhThus);
        edtTuNgayBatDau.setOnClickListener(v -> showDataPickerDialog(edtTuNgayBatDau));
        edtDenNgayKetThuc.setOnClickListener(v -> showDataPickerDialog(edtDenNgayKetThuc));
        findViewById(R.id.btnThongKeDT).setOnClickListener(v -> {
            String tuNgay = edtTuNgayBatDau.getText().toString();
            String denNgay = edtDenNgayKetThuc.getText().toString();
            if (tuNgay.isEmpty() || denNgay.isEmpty()){
                tvDoanhThu.setText("Vui lòng nhập đủ ngày bắt đầu và ngày kết thúc");
                return;
            }
            int doanhThu = db.layDoanhThu(tuNgay, denNgay);
            tvDoanhThu.setText("Doanh Thu: " + doanhThu + " VNĐ");
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void showDataPickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
//                    String selectedDate = selectedYear
//                            + "-" + String.format("%02d", selectedMonth + 1)
//                            + "-" + String.format("%02d", selectedDay);
//                    editText.setText(selectedDate);
                    String selectedDate = String.format("%02d-%02d-%04d", selectedYear, selectedMonth + 1, selectedDay);
                    editText.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}
