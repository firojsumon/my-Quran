package com.example.myquran;

import android.content.Context;
import android.content.SharedPreferences;

public class AppManiSharedPref {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public AppManiSharedPref(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MainSharedPref", Context.MODE_PRIVATE);
        this.preferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }


    public void setLanguage(String str) {
        this.editor.putString("language", str);
        this.editor.commit();
    }

    public String getLanguage() {
        return this.preferences.getString("language", "en");
    }

}
