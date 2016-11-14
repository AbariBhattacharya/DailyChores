package com.example.dell.dailychores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 06-11-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="chores.db";
    public static final String TABLE_NAME="choresTable";
    public static final String COL0="Title";
    public static final String COL1="Detail";

    private static DatabaseHelper dbInstance;
    public static DatabaseHelper getDbInstance(Context context){
        if (dbInstance==null){
            dbInstance=new DatabaseHelper(context);

        }
        return  dbInstance;
    }

    public static DatabaseHelper getDbInstance(){
        return dbInstance;
    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
//        SQLiteDatabase db = this.getReadableDatabase();
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
        contentValues.put(COL0,title);
        contentValues.put(COL1,detail);
        long result= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else return true;
    }
    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        // Cursor res=db.rawQuery("Select * from " + TABLE_NAME +  "", null );
        String query="Select * from " + TABLE_NAME ;
        Cursor res=db.rawQuery(query,null);

        return res;
    }
    public List<Chores> getChores(){
        List<Chores> data=new ArrayList<>();
        Log.v("DisplayListActivity","in getChores() function");


        Cursor res = getData();
        if(res.getCount()==0){
            return null;
        }
        while (res.moveToNext()) {
            Chores current=new Chores(res.getString(0),res.getString(1));
            data.add(current);
        }

        return data;
    }

    public Chores getItem(String string){

        SQLiteDatabase db=this.getWritableDatabase();

       Cursor cursor=db.rawQuery("Select * from "+TABLE_NAME+" where "+COL0+" = '"+string+"'",null);

        cursor.moveToNext();
        Chores chores=new Chores(cursor.getString(0),cursor.getString(1));
        return chores;
    }

    public int deleteData(String string){
        SQLiteDatabase db=this.getWritableDatabase();
        int i=db.delete(TABLE_NAME, "Title = ?",new String[] {string});
        return i;
    }



}
