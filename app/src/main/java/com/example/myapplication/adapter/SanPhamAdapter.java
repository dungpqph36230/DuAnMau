package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.NhanVien;
import com.example.myapplication.model.SanPham;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class SanPhamAdapter extends BaseAdapter {
    private final Context context;
    private final List<SanPham> danhSachSanPham;
    private OnSanPhamClickListener onSanPhamClickListener;
    NumberFormat currencyFormat;

    public interface OnSanPhamClickListener {
        void onEditSanPham(SanPham sanPham);
        void onDeleteSanPham(SanPham sanPham);
    }

    public void setOnSanPhamClickListener(OnSanPhamClickListener onSanPhamClickListener) {
        this.onSanPhamClickListener = onSanPhamClickListener;
    }

    public SanPhamAdapter(Context context, List<SanPham> danhSachSanPham) {
        this.context = context;
        this.danhSachSanPham = danhSachSanPham;
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    }

    @Override
    public int getCount() {
        return danhSachSanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return danhSachSanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder hoder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_san_pham, parent, false);
            hoder = new ViewHolder();
            hoder.imgProduct = convertView.findViewById(R.id.imgSanPham);
            hoder.tvTenSanPham = convertView.findViewById(R.id.tvTenSanPham);
            hoder.tvGiaSanPham = convertView.findViewById(R.id.tvGiaSanPham);
            hoder.tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
            hoder.imgSuaSanPham = convertView.findViewById(R.id.imgSuaDanhMuc);
            hoder.imgXoaSanPham = convertView.findViewById(R.id.imgXoaDanhMuc);
            convertView.setTag(hoder);
        }
        else {
            hoder = (ViewHolder) convertView.getTag();
        }
        SanPham sanPham = danhSachSanPham.get(position);
        hoder.tvTenSanPham.setText(sanPham.getTenSanPham());
        hoder.tvGiaSanPham.setText(currencyFormat.format(sanPham.getGiaSanPham()));
        hoder.tvSoLuong.setText("Tồn kho: " + sanPham.getSoLuong());
        hoder.imgSuaSanPham.setOnClickListener(v -> onSanPhamClickListener.onEditSanPham(sanPham));
        hoder.imgXoaSanPham.setOnClickListener(v -> onSanPhamClickListener.onDeleteSanPham(sanPham));
        return convertView;
    }

    static class ViewHolder {
        ImageView imgProduct;
        TextView tvMaSanPham;
        TextView tvTenSanPham;
        TextView tvGiaSanPham;
        TextView tvSoLuong;
        ImageView imgSuaSanPham;
        ImageView imgXoaSanPham;
    }
}
