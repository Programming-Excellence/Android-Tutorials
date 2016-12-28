package com.crud.db.crudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adeel Suleman on 11/1/2016.
 */

public class DbManager extends SQLiteOpenHelper {

    private static String dbName = "PracticeDb";
    private static Integer dbVersion = 1;

    private static String tableQuery = "Create table Users(_id INTEGER PRIMARY KEY AUTOINCREMENT , _username TEXT NOT NULL , _password TEXT NOT NULL , _email TEXT NOT NULL,_alternative_email TEXT NOT NULL)";


    public DbManager(Context context) {
        super(context, dbName , null, dbVersion);
        SQLiteDatabase helper = this.getWritableDatabase();
    }

    public int Insertion(String Username, String Password, String Email,String AlternativeEmail){

        SQLiteDatabase helper = this.getWritableDatabase();
        ContentValues data =new ContentValues();
        data.put("_username",Username);
        data.put("_password",Password);
        data.put("_email",Email);
        data.put("_alternative_email",AlternativeEmail);

        int id = (int) helper.insert("Users",null,data);

        return id;
    }
    public Cursor FetchData(String Username, String Password){
        SQLiteDatabase helper = this.getReadableDatabase();

        Cursor reader = helper.query("Users",new String[]{"_id","_username","_password","_email","_alternative_email"},"_username = ? AND _password = ? ",new String[]{Username,Password},null,null,null,null);
        if(reader!=null){
            reader.moveToFirst();
            //moveToFirst() means get data from 1st row , beginnning say data lay ga
            //moveToLast() means fetch data from last row , akhri data lay kar ayega
        }

        return reader;

    }

    public int Updation(String id, String Username, String Password, String Email,String AEmail){

        SQLiteDatabase helper = this.getWritableDatabase();
        ContentValues data =new ContentValues();
        data.put("_username",Username);
        data.put("_password",Password);
        data.put("_email",Email);
        data.put("_email",AEmail);


        int i = (int) helper.update("Users",data,"_id=?",new String[]{id});
        return i;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Users IF NOT EXISTS" + tableQuery);
    }
}
