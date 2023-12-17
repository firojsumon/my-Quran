package com.example.myquran.Sqlite_Database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {

    public static final String DBNAME = "dua.db";
    public static final int DBVERSION = 1;

    public DatabaseOpenHelper(Context context) {

        super(context,DBNAME,null,DBVERSION);
    }
}