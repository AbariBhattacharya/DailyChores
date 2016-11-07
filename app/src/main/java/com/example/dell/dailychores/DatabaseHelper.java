package com.example.dell.dailychores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 06-11-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="chores.db";
    public static final String TABLE_NAME="choresTable";
    public static final String COL1="Title";
    public static final String COL2="Detail";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){

            db.execSQL("create table " +TABLE_NAME+ "(Title TEXT PRIMARY KEY,Detail TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop Table if exists"+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title,String detail){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,title);
        contentValues.put(COL2,detail);
        long result= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else return true;
    }
    public Cursor getData(String title){
        SQLiteDatabase db=this.getWritableDatabase();
        // Cursor res=db.rawQuery("Select * from " + TABLE_NAME +  "", null );
        String query="Select * from " + TABLE_NAME + " where Title = '" +
                title +"'";
        Cursor res=db.rawQuery(query,null);

        return res;
    }
}
