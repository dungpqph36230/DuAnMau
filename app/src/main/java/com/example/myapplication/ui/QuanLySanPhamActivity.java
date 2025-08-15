package com.example.myapplication.ui;

import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.adapter.SanPhamAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class QuanLySanPhamActivity extends AppCompatActivity implements SanPhamAdapter.OnSanPhamClickListener {
    public static final String SAN_PHAM = "SAN_PHAM";
    private SanPhamAdapter sanPhamAdapter;
    private List<SanPham> danhSachSanPham;
    private DatabaseHelper db;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView lvSanPham = findViewById(R.id.lvSanPham);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Quản lý sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        FloatingActionButton fabThemSanPham = findViewById(R.id.fabThemSanPham);

        danhSachSanPham = db.latTatCaSanPham();
        sanPhamAdapter = new SanPhamAdapter(this, danhSachSanPham);
        sanPhamAdapter.setOnSanPhamClickListener(this);
        lvSanPham.setAdapter(sanPhamAdapter);
        fabThemSanPham.setOnClickListener(v->{
            Intent intent = new Intent(this, EditSanPhamActivity.class);
            intent.putExtra("Type",1);
            this.startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        danhSachSanPham.clear();
        danhSachSanPham.addAll(db.latTatCaSanPham());
        sanPhamAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditSanPham(SanPham sanPham) {
        Intent intent = new Intent(this, EditSanPhamActivity.class);
        intent.putExtra("Type",0);
        intent.putExtra(SAN_PHAM, sanPham);
        startActivity(intent);
    }

    @Override
    public void onDeleteSanPham(SanPham sanPham) {
        DatabaseHelper db = new DatabaseHelper(this);
        boolean isDeleted = db.xoaSanPham(sanPham.getMaSanPham());
        if (isDeleted) {
            danhSachSanPham.remove(sanPham);
            sanPhamAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public void animateAddToCart(View startView, View cartIcon){
        int[] startLoc = new int[2];
        int[] endLoc = new int[2];
        startView.getLocationOnScreen(startLoc);
        cartIcon.getLocationOnScreen(endLoc);

        ImageView animView = new ImageView(this);
        animView.setImageResource(R.drawable.ic_launcher_background);
//        animView.setImageResource(((ImageView) startView).getDrawable());

        ViewGroup rootLayout = (ViewGroup) getWindow().getDecorView();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(startView.getWidth(), startView.getHeight());
        params.leftMargin = startLoc[0];
        params.topMargin = startLoc[1];
        rootLayout.addView(animView, params);

        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(animView, "scaleX", 1f, 5f);
        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(animView, "scaleY", 1f, 5f);
        AnimatorSet scaleUpSet = new AnimatorSet();
        scaleUpSet.playTogether(scaleUpX, scaleUpY);
        scaleUpSet.setDuration(300);

        ObjectAnimator tramslateX = ObjectAnimator.ofFloat(animView, "translationX", endLoc[0] - startLoc[0]);
        ObjectAnimator tramslateY = ObjectAnimator.ofFloat(animView, "translationY", endLoc[1] - startLoc[1]);
        ObjectAnimator scaleDonwX = ObjectAnimator.ofFloat(animView, "scaleX", 5f, 0.2f);
        ObjectAnimator scaleDonwY = ObjectAnimator.ofFloat(animView, "scaleY", 5f, 0.2f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(animView, "alpha", 1f, 0f);

        AnimatorSet moveToCartSet = new AnimatorSet();
        moveToCartSet.playTogether(tramslateX, tramslateY, scaleDonwX, scaleDonwY, alpha);
        moveToCartSet.setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(scaleUpSet, moveToCartSet);
//        set.addListener((AnimatorListenerAdapter) (animator) -> {
//            rootLayout.removeView(animView);
//        });
        set.start();
    }
}
