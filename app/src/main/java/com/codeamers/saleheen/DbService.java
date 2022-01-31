package com.codeamers.saleheen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

public class DbService extends SQLiteOpenHelper {
    private static final String DB_NAME = "SaleheenDb";
    private static final String Table_Name = "tasbeeh";
    private static final int Version = 1;
    private SQLiteDatabase db;

    public DbService(@Nullable Context context) {
        super(context, DB_NAME , null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String q = "CREATE TABLE IF NOT EXISTS " + Table_Name + "( id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR , count VARCHAR ) ";
        db.execSQL(q);
    }


    public boolean insertTasbeeh(String title, String count){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title" , title);
        cv.put("count" , count);
        long check  = db.insert(Table_Name,"",cv);
        if (check != -1) return true;
        else return false;
    }

    public ArrayList<String> getTasbeeh(){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table_Name , null );
        ArrayList<String> data = new ArrayList<>();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                data.add(cursor.getString(1));
                data.add(cursor.getString(2));
                cursor.moveToNext();
            }
        }
        Log.d("arrayList", Arrays.deepToString(new ArrayList[]{data}));
        return data;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
