package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.NhanVien;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class NhanVienAdapter extends BaseAdapter {

    private final Context context;
    private final List<NhanVien> danhSachNhanViens;
    private  OnNhanVienClickListener onNhanVienClickListener;
    NumberFormat currencyFormat;

    public interface OnNhanVienClickListener {
        void onEditNhanVien(NhanVien nhanVien);
        void onDeleteNhanVien(NhanVien nhanVien);
    }

    public NhanVienAdapter(Context context, List<NhanVien> danhSachNhanViens) {
        this.context = context;
        this.danhSachNhanViens = danhSachNhanViens;
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    }

    public void setOnNhanVienClickListener(OnNhanVienClickListener onNhanVienClickListener) {
        this.onNhanVienClickListener = onNhanVienClickListener;
    }

    @Override
    public int getCount() {
        return danhSachNhanViens.size();
    }

    @Override
    public Object getItem(int position) {
        return danhSachNhanViens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_nhan_vien, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgProduct = convertView.findViewById(R.id.imgNhanVien);
            viewHolder.tvMaNhanVien = convertView.findViewById(R.id.edtMaNhanVien);
            viewHolder.tvTenNhanVien = convertView.findViewById(R.id.tvTenNhanVien);
            viewHolder.tvDiaChi = convertView.findViewById(R.id.tvDiaChi);
            viewHolder.tvLuong = convertView.findViewById(R.id.tvLuong);
            viewHolder.tvChucVu = convertView.findViewById(R.id.tvChucVu);
            viewHolder.imgSuaNhanVien = convertView.findViewById(R.id.imgSuaDanhMuc);
            viewHolder.imgXoaNhanVien = convertView.findViewById(R.id.imgXoaDanhMuc);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NhanVien nhanVien = danhSachNhanViens.get(position);
        viewHolder.tvMaNhanVien.setText(nhanVien.getMaNhanVien());
        viewHolder.tvTenNhanVien.setText(nhanVien.getTenNhanVien());
        viewHolder.tvDiaChi.setText(nhanVien.getDiaChi());
        viewHolder.tvLuong.setText(currencyFormat.format(nhanVien.getLuong()));
        viewHolder.tvChucVu.setText(nhanVien.getChucVu()== 0? "Nhân viên": "Quản lý");
        viewHolder.imgSuaNhanVien.setOnClickListener(v-> onNhanVienClickListener.onEditNhanVien(nhanVien));
        viewHolder.imgXoaNhanVien.setOnClickListener(v-> onNhanVienClickListener.onDeleteNhanVien(nhanVien));

        return convertView;
    }
    static class ViewHolder {
        ImageView imgProduct;
        TextView tvMaNhanVien;
        TextView tvTenNhanVien;
        TextView tvDiaChi;
        TextView tvLuong;
        TextView tvChucVu;
        ImageView imgSuaNhanVien;
        ImageView imgXoaNhanVien;
    }
}
