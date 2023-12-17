package com.example.myquran;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class QuranCategoryAdapter extends RecyclerView.Adapter<QuranCategoryAdapter.AlbumViewHolder> {
    private Context context;
    private List<AlQuranCategoryModel> list;



    public QuranCategoryAdapter(Context context, List<AlQuranCategoryModel> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AlbumViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.surah_list_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder albumViewHolder, final int i) {
        String valueOf;
        int id = this.list.get(i).getId();
        if (id < 10) {
            valueOf = "0" + id;
        } else {
            valueOf = String.valueOf(id);
        }
        albumViewHolder.tvId.setText(valueOf);
        albumViewHolder.tvArabicTranslation.setText(this.list.get(i).getArabicTranslation());
        albumViewHolder.tvEnglishName.setText(this.list.get(i).getEnglishName());
        albumViewHolder.tvArabicName.setText(this.list.get(i).getArabicName());

        albumViewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SurahDetailsActivity.class);
            intent.putExtra("idNum", list.get(i).getId());
            context.startActivity(intent);

        });




    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }


    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView tvArabicName;
        TextView tvArabicTranslation;
        TextView tvEnglishName;
        TextView tvId;

        AlbumViewHolder(View view) {
            super(view);
            this.tvArabicTranslation = (TextView) view.findViewById(R.id.tvArabicTranslation);
            this.tvEnglishName = (TextView) view.findViewById(R.id.tvEnglishName);
            this.tvArabicName = (TextView) view.findViewById(R.id.tvArabicName);
            this.tvId = (TextView) view.findViewById(R.id.tvId);
        }
    }
}
