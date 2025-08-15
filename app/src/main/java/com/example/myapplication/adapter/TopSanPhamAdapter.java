package com.example.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.myapplication.model.TopSanPham;

import java.util.List;

public class TopSanPhamAdapter extends BaseAdapter {

    private final Context context;
    private final List<TopSanPham> topSanPhamList;

    public TopSanPhamAdapter(Context context, List<TopSanPham> topSanPhamList) {
        this.context = context;
        this.topSanPhamList = topSanPhamList;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
