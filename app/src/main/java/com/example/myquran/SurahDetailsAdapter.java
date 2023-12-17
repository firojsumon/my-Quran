package com.example.myquran;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myquran.databinding.LayoutAyatBinding;

import java.util.ArrayList;
import java.util.List;

public class SurahDetailsAdapter extends RecyclerView.Adapter<SurahDetailsAdapter.ViewHolder> {

    ArrayList<AlQuranDataModel> list;
    Context context;

    public SurahDetailsAdapter(ArrayList<AlQuranDataModel> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public SurahDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_ayat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SurahDetailsAdapter.ViewHolder holder, int position) {
        AlQuranDataModel model = list.get(position);

        holder.binding.arabic.setText(model.getAyahArabic());
        holder.binding.translation.setText(model.getAyahEnglish());
        holder.binding.surahName.setText(model.getAyahEnglish());


        holder.binding.share.setOnClickListener(v -> {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = model.getAyahArabic() + "\n\n\n" + model.getAyahEnglish();
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LayoutAyatBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = LayoutAyatBinding.bind(itemView);
        }
    }
}
