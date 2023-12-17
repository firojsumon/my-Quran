package com.example.myquran;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myquran.databinding.FragmentTafseerBinding;


public class TafseerFragment extends Fragment {


    public TafseerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    FragmentTafseerBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTafseerBinding.inflate(inflater);


        String taf = getActivity().getIntent().getStringExtra("tafseer");


        binding.noTafseer.setVisibility(View.GONE);
        binding.tafseer.setVisibility(View.VISIBLE);
        binding.tafseer.setText(taf);


        return binding.getRoot();


    }
}