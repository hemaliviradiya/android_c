package com.example.spinner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper
{

    private static final String DB_Name= "mobile.db";
    private static final int version=1;
    public static final String Table_name= "mobile";

    public static final String key_id="mid";
    public static final String key_name="name";
    public static final String key_company="company";

    public DBHelper(@Nullable Context context) {
        super(context, DB_Name, null   , version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String Query = "Create  Table " +Table_name + " ( "
                    + key_id + " Integer primary key , "
                    + key_name + " Text , "
                    + key_company + " Text )";
        sqLiteDatabase.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String Query = "Drop Table if exists " + Table_name;
        sqLiteDatabase.execSQL(Query);
    }


    public int dlt(String id)
    {
        int i=0;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_name,key_id+"=?",new String[]{ id });
        i=1;
        return  i;
    }
    public int updatemobile(mobile mb)
    {
        int i = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(key_company,mb.getCompany());
        values.put(key_name,mb.getName());
        db.update(Table_name,values, key_id+ "=?",new String[]{String.valueOf(mb.getMid())});
        i=1;
        return  i;
    }
    public ArrayList<mobile> show()
    {
        ArrayList<mobile> arrayList = new ArrayList<mobile>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table_name,null);
        if(cursor.moveToFirst())
        {
            do {
                mobile mbn = new mobile();
                mbn.setMid(cursor.getInt(0));
                mbn.setName(cursor.getString(1));
                mbn.setCompany(cursor.getString(2));
                arrayList.add(mbn);
            }
            while(cursor.moveToNext());
        }
        return arrayList;
    }

    public int addmobile(mobile mb)
    {
        int i=0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(key_company,mb.getCompany());
        values.put(key_name,mb.getName());
        db.insert(Table_name,null,values);

        i =1 ;
        return  i;
    }


}
