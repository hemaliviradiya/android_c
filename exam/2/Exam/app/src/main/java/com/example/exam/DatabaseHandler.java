package com.example.exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "demo";
    private static final String TABLE_DEMO = "demo";

    private static final String SID = "sid";
    private static final String SNAME = "sname";
    private static final String SEMAIL = "semail";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create ="CREATE TABLE " + TABLE_DEMO + "(" +
                        SID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        SNAME + "TEXT," +
                        SEMAIL + "TEXT" + ")";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +TABLE_DEMO);
            onCreate(db);
    }
    public void AddStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SNAME ,student.getName());
        values.put(SEMAIL,student.getEmail());
        db.insert(TABLE_DEMO,null,values);
        db.close();
    }
    public ArrayList<Student> getAll(){
        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        String sql ="SELECT * FROM " + TABLE_DEMO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                Student s = new Student();
                s.setId(cursor.getInt(0));
                s.setName(cursor.getString(1));
                s.setEmail(cursor.getString(2));
                studentArrayList.add(s);
            }while (cursor.moveToNext());
        }

        return studentArrayList;
    }
    public void deleteContact(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEMO,SID + "= ?", new String[]{String.valueOf(student.getId())});
        db.close();

    }
}
