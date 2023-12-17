package com.example.myquran;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myquran.Sqlite_Database.DatabaseAccess;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    QuranCategoryAdapter alQuranCategoryAdapter;
    List<AlQuranCategoryModel> alQuranCategoryModelList = new ArrayList();
    DatabaseAccess databaseAccess;
    RelativeLayout layout;
    RecyclerView rvAlQuranCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.databaseAccess = DatabaseAccess.getInstance(this);
        this.rvAlQuranCategory = (RecyclerView) findViewById(R.id.rv_surah_list);
        this.databaseAccess.open();
        this.alQuranCategoryModelList = this.databaseAccess.getAlQuranCategory();
        this.databaseAccess.close();
        this.rvAlQuranCategory.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.rvAlQuranCategory.setItemAnimator(new DefaultItemAnimator());
        QuranCategoryAdapter quranCategoryAdapter = new QuranCategoryAdapter(this, this.alQuranCategoryModelList);
        this.alQuranCategoryAdapter = quranCategoryAdapter;
        this.rvAlQuranCategory.setAdapter(quranCategoryAdapter);

findViewById(R.id.floatingActionButton).setOnClickListener(v -> {
    startActivity(new Intent(MainActivity.this, LanguageActivity.class));
});

    }

    private String loadStream() {
        String json = null;
        try {
            InputStream stream = this.getAssets().open("quran_list.json");
            int size = stream.available();

            byte[] buffer = new byte[size];

            stream.read(buffer);
            stream.close();
            json = new String(buffer, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return json;

    }



    @Override
    protected void attachBaseContext(Context newBase) {
        String language = new AppManiSharedPref(newBase).getLanguage();
        Locale localeToSwitchTo = new Locale(language);
        ContextWrapper localeUpdatedContext = ContextUtils.updateLocale(newBase, localeToSwitchTo);
        super.attachBaseContext(localeUpdatedContext);
    }
}