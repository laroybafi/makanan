package com.bafi.makanan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailMakananActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_makanan);

        final Makanan makanan = (Makanan) getIntent().getSerializableExtra("makanan");

        TextView tvName = findViewById(R.id.tv_name);
        TextView tvHarga = findViewById(R.id.tv_harga);
        Button btnBeli = findViewById(R.id.btn_beli);

        tvName.setText(makanan.getNama());
        tvHarga.setText(makanan.getFormattedHarga());

        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("harga", makanan.getHarga());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
