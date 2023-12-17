package com.example.myquran.Sqlite_Database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myquran.AlQuranCategoryModel;
import com.example.myquran.AlQuranDataModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

    private static DatabaseAccess instance;
    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = this.openHelper.getWritableDatabase();
    }

    public void close() {
        SQLiteDatabase sQLiteDatabase = this.database;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    public List<AlQuranCategoryModel> getAlQuranCategory() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM  SuraNames", null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            AlQuranCategoryModel alQuranCategoryModel = new AlQuranCategoryModel();
            alQuranCategoryModel.setId(Integer.parseInt(rawQuery.getString(0)));
            alQuranCategoryModel.setArabicName(rawQuery.getString(2));
            alQuranCategoryModel.setArabicTranslation(rawQuery.getString(3));
            alQuranCategoryModel.setEnglishName(rawQuery.getString(4));
            arrayList.add(alQuranCategoryModel);
            rawQuery.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<AlQuranDataModel> getAlQuranData(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM Quran where SuraID='" + i + "'", null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            AlQuranDataModel alQuranDataModel = new AlQuranDataModel();
            alQuranDataModel.setId(Integer.parseInt(rawQuery.getString(0)));
            alQuranDataModel.setIndexNo(Integer.parseInt(rawQuery.getString(2)));
            alQuranDataModel.setAyahArabic(rawQuery.getString(3));
            alQuranDataModel.setAyahTranslation(rawQuery.getString(4));
            alQuranDataModel.setAyahEnglish(rawQuery.getString(5));
            try {
                if (rawQuery.getString(7).equals("1")) {
                    alQuranDataModel.setFavorite(true);
                } else {
                    alQuranDataModel.setFavorite(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                alQuranDataModel.setFavorite(false);
            }
            arrayList.add(alQuranDataModel);
            rawQuery.moveToNext();
        }
        return arrayList;
    }


}

