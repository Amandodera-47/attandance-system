package com.example.attandancesystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {
    public SQLite(@Nullable Context context) {
        super(context, "Dbase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table login(USERNAME TEXT,PASSWORD TEXT)");
        db.execSQL("create table stud_info(ENROLLMENT_NO TEXT,NAME TEXT,STREAM TEXT,SEMESTER TEXT,MOBILE_NO TEXT,MAIL TEXT,ADDRESS TEXT)");
        db.execSQL("create table attandance(DATE TEXT,NAME TEXT,ATTAND TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists login");
        db.execSQL("drop table if exists stud_info");
        db.execSQL("drop table if exists attandance");
        onCreate(db);
    }

    public boolean insertData(String USERNAME, String PASSWORD) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", USERNAME);
        contentValues.put("PASSWORD", PASSWORD);
        long res = db.insert("login", null, contentValues);
        if (res == -1)
            return false;
        else
            return true;
    }

    public boolean readData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from login where username=? And password=?", new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public Cursor enrolstud() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select ENROLLMENT_NO from stud_info",null);
        return cursor;
    }
    public Cursor namestud(String item) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor2 = db.rawQuery("select NAME,STREAM,SEMESTER,MOBILE_NO,MAIL,ADDRESS from stud_info where ENROLLMENT_NO='"+item+"'",null);
        //Toast.makeText(show_attand.class, cursor2.toString(), Toast.LENGTH_SHORT).show();
        return cursor2;
    }

    public boolean studinsertData(String ENROLLMENT_NO, String NAME, String STREAM, String SEMESTER, String MOBILE_NO, String MAIL, String ADDRESS) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ENROLLMENT_NO", String.valueOf(ENROLLMENT_NO));
        contentValues.put("NAME", String.valueOf(NAME));
        contentValues.put("STREAM", String.valueOf(STREAM));
        contentValues.put("SEMESTER", String.valueOf(SEMESTER));
        contentValues.put("MOBILE_NO", String.valueOf(MOBILE_NO));
        contentValues.put("MAIL", String.valueOf(MAIL));
        contentValues.put("ADDRESS", String.valueOf(ADDRESS));
        long res = db.insert("stud_info", null, contentValues);
        if (res == -1)
            return false;
        else
            return true;
    }


    public boolean delData(String ENROLLMENT_NO) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("stud_info", "ENROLLMENT_NO=" + ENROLLMENT_NO, null);
        return true;
    }

    public boolean updateData(String ENROLLMENT_NO, String NAME, String STREAM, String SEMESTER, String MOBILE_NO, String MAIL, String ADDRESS) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ENROLLMENT_NO", String.valueOf(ENROLLMENT_NO));
        contentValues.put("NAME", String.valueOf(NAME));
        contentValues.put("STREAM", String.valueOf(STREAM));
        contentValues.put("SEMESTER", String.valueOf(SEMESTER));
        contentValues.put("MOBILE_NO", String.valueOf(MOBILE_NO));
        contentValues.put("MAIL", String.valueOf(MAIL));
        contentValues.put("ADDRESS", String.valueOf(ADDRESS));
        long res =db.update("stud_info", contentValues, "ENROLLMENT_NO="+ENROLLMENT_NO ,null);
        if (res == -1)
            return false;
        else
            return true;
    }

    public Cursor readattandData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select name from stud_info",null);
        return cursor;
    }

    public boolean insertattandData(String DATE, String NAME, String ATTANDANCE) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", String.valueOf(DATE));
        contentValues.put("NAME", NAME);
        contentValues.put("ATTAND", ATTANDANCE);
        long res = db.insert("attandance", null, contentValues);
        if (res == -1)
            return false;
        else
            return true;
    }
}
