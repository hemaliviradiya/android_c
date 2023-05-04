package com.example.examtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "demo";
    private static final String TABLE_DEMO = "student";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String GENDER = "gender";
    private static final String STREAM = "stream";

    public DataBaseHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + TABLE_DEMO + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME + " TEXT," +
                GENDER + " TEXT," +
                STREAM + " TEXT" + ")";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_DEMO);
            onCreate(db);
    }

    public ArrayList<Student> getAll(){
        ArrayList<Student> students = new ArrayList<Student>();
        String select = "SELECT * FROM " + TABLE_DEMO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do{
                Student s = new Student();
                s.setRollno(cursor.getInt(0));
                s.setName(cursor.getString(1));
                s.setGender(cursor.getString(2));
                s.setStream(cursor.getString(3));
                students.add(s);
            }while (cursor.moveToNext());
        }
        return students;
    }
public void addStudent(Student student){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(NAME,student.getName());
    values.put(GENDER,student.getGender());
    values.put(STREAM,student.getStream());
    db.insert(TABLE_DEMO,null,values);
    db.close();
}
    public void deleteContact(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEMO,ID + "= ?", new String[]{String.valueOf(student.getRollno())});
        db.close();
    }
}
