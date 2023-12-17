package com.example.myquran;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myquran.Sqlite_Database.DatabaseAccess;
import com.example.myquran.databinding.ActivitySurahDetailsBinding;

import java.util.ArrayList;
import java.util.Locale;

public class SurahDetailsActivity extends AppCompatActivity {
    ActivitySurahDetailsBinding binding;

    ArrayList<AlQuranDataModel> alQuranDataModelList;

    int id;
    SurahDetailsAdapter adapter;
    DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySurahDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        alQuranDataModelList = new ArrayList<>();

        id=getIntent().getIntExtra("idNum",0);



        databaseAccess = DatabaseAccess.getInstance(this);
        this.databaseAccess.open();
        this.alQuranDataModelList = this.databaseAccess.getAlQuranData(id);
        this.databaseAccess.close();

        SurahDetailsAdapter quranAdapter = new SurahDetailsAdapter(this.alQuranDataModelList, this);
        this.adapter = quranAdapter;
        binding.rvDetailsSurah.setHasFixedSize(true);
        binding.rvDetailsSurah.setLayoutManager(new LinearLayoutManager(this));
        binding.rvDetailsSurah.setAdapter(quranAdapter);


    }

/*    private void surahDetailsLoad(String surahNum) {
        String url = "https://quranenc.com/api/v1/translation/sura/english_rwwad/" + surahNum;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray array = response.getJSONArray("result");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    list.add(new SurahDetailsModel(object.getString("aya"), object.getString("arabic_text"), object.getString("translation"), object.getString("footnotes")));
                }
                if (list.size() != 0) {
                    binding.rvDetailsSurah.setHasFixedSize(true);
                    adapter = new SurahDetailsAdapter(list, this);
                    binding.rvDetailsSurah.setAdapter(adapter);
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


        }, error -> {
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();

        });
        queue.add(request);

    }*/

    @Override
    protected void attachBaseContext(Context newBase) {
        String language = new AppManiSharedPref(newBase).getLanguage();
        Locale localeToSwitchTo = new Locale(language);
        ContextWrapper localeUpdatedContext = ContextUtils.updateLocale(newBase, localeToSwitchTo);
        super.attachBaseContext(localeUpdatedContext);
    }
}