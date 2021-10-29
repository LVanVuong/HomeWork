package com.example.homework.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBDesigner extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dotations.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE_TABLE_DONATION = "create table donations"
            + "(id integer primary key autoincrement,"
            + "amount  integer not null,"
            + "method text not null)";

    public DBDesigner(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_TABLE_DONATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS donations");
        onCreate(database);
    }
}
