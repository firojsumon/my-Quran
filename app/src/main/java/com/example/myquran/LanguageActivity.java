package com.example.myquran;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    AppManiSharedPref appManiSharedPref;

    RadioGroup language_group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        language_group=findViewById(R.id.language_group);
        appManiSharedPref = new AppManiSharedPref(this);

        switch (appManiSharedPref.getLanguage()) {
            case "en":
                language_group.check(R.id.englishLang);
                break;
            case "bn":
                language_group.check(R.id.banglaLang);
                break;
        }

        language_group.setOnCheckedChangeListener((group, checkedId) -> {
            String language = checkedId == R.id.englishLang ? "en" :
                    checkedId == R.id.banglaLang ? "bn" : "en";
            appManiSharedPref.setLanguage(language);
            recreate();
        });

        setResult(Activity.RESULT_OK, new Intent());
    }


    @Override
    public void attachBaseContext(Context newBase) {
        Locale localeToSwitchTo = new Locale(new AppManiSharedPref(newBase).getLanguage());
        ContextWrapper localeUpdatedContext = ContextUtils.updateLocale(newBase, localeToSwitchTo);
        super.attachBaseContext(localeUpdatedContext);
    }

}