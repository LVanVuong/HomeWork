package com.example.homework.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.homework.Model.Donation;

import java.util.ArrayList;
import java.util.List;

public class DBManger {
    private SQLiteDatabase database ;
    private DBDesigner dbHelper;
    public DBManger(Context context){
        dbHelper = new DBDesigner(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void cloes() {
        database.close();
    }
    public  void add(Donation donations){
        ContentValues values = new ContentValues();
        values.put("amount",donations.amount);
        values.put("method",donations.method);
        database.insert("donations", null,values);
    }
    public List<Donation> getAll(){
        List<Donation> mDonations= new ArrayList<Donation>();
        Cursor cursor = database.rawQuery("SELECT * FROM dontions",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Donation mdonation = toDonation(cursor);
            mDonations.add(mdonation);
            cursor.moveToNext();
        }
        cursor.close();
        return mDonations;
    }
    private Donation toDonation(Cursor cursor){
        Donation pojo = new  Donation();
        pojo.id = cursor.getInt(0);
        pojo.amount = cursor.getInt(1);
        pojo.method = cursor.getString(2);
        return pojo;
    }
    public void  setTotalDonated(Base base){
        Cursor mCursor = database.rawQuery("SELECT SUM(amount) FROM donations",null );
        mCursor.moveToFirst();
        if(!mCursor.isAfterLast()){
            base.app.totalDonated = mCursor.getInt(0);
        }
    }
    public void reset(){
        database.delete("donations", null,null);
    }
}
