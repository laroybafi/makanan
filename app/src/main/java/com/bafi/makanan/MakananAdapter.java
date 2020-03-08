package com.bafi.makanan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.ViewHolder> {

    private List<Makanan> makananList;
    private Activity context;

    public MakananAdapter(List<Makanan> makananList, Activity context) {
        this.makananList = makananList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.makanan_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(makananList.get(position));
    }

    @Override
    public int getItemCount() {
        return makananList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindView(final Makanan makanan) {
            // impelemntasi di sini
            TextView tvName = itemView.findViewById(R.id.tv_name);

            tvName.setText(makanan.getNama());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailMakananActivity.class);
                    intent.putExtra("makanan", makanan);
                    context.startActivityForResult(intent, Makanan.MAKANAN_INTENT);
                }
            });
        }
    }
}
