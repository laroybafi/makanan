package com.bafi.makanan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private TextView tvTotal;
    private RecyclerView rvMakanan;
    private MakananDatabaseHelper dbHelper;

    private int totalHarga = 0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotal = findViewById(R.id.tv_total);
        rvMakanan = findViewById(R.id.rv_makanan);

        dbHelper = new MakananDatabaseHelper(this);

        initData();
        initRecyclerView();
        updateHarga();
    }

    private void initRecyclerView(){
        rvMakanan.setLayoutManager(new LinearLayoutManager(this));
        rvMakanan.setAdapter(new MakananAdapter(dbHelper.getMakananList(), this));
    }

    private void initData() {
        dbHelper.saveRecord("Soto", "15000");
        dbHelper.saveRecord("Nasi Goreng", "20000");
        dbHelper.saveRecord("Rujak", "10000");
        dbHelper.saveRecord("Es Teh", "5000");
    }

    private void updateHarga() {
        tvTotal.setText("Total Harga : Rp. " + Makanan.angkaKeString(totalHarga));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == Makanan.MAKANAN_INTENT) {
            totalHarga += data.getIntExtra("harga", 0);
            updateHarga();
        }
    }
}

