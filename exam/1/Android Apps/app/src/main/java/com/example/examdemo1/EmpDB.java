package com.example.examdemo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class EmpDB extends SQLiteOpenHelper {
    private  Context context;
    private static final int dbver=1;
    private static final String dbname="exam1.db";

    public EmpDB(@Nullable Context context) {
        super(context, dbname, null, dbver);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tbemp (eid integer primary key autoincrement,ename text,eposition text,esalary integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists tbemp ");
        onCreate(db);
    }

    void addemp(String empname,String empposition,int empsalay){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("ename",empname);
        cv.put("eposition",empposition);
        cv.put("esalary",empsalay);

        long res = db.insert("tbemp",null,cv);

        if(res == -1)
        {
            Toast.makeText(context, "Not Inserted...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Inserted...", Toast.LENGTH_SHORT).show();
        }
    }

    void update(String rowid,String empname,String empposition,int empsalay)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("ename",empname);
        cv.put("eposition",empposition);
        cv.put("esalary",empsalay);

       long res =  db.update("tbemp",cv,"eid=?",new String[]{rowid});

        if(res == -1)
        {
            Toast.makeText(context, "Not Updated...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Updated...", Toast.LENGTH_SHORT).show();
        }
    }

    void delete(String rowid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long res =  db.delete("tbemp","eid=?",new String[]{rowid});

        if(res == -1)
        {
            Toast.makeText(context, "Not Deleted...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Deleted...", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor empdisp()
    {
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = null;

        if (db !=null)
        {
            cursor = db.rawQuery("select * from tbemp",null);
        }
        return cursor;
    }
}
