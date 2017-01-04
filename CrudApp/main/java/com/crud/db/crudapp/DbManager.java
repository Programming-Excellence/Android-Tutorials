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
    //database name
    private static String dbName = "PracticeDb";
    //database version
    private static Integer dbVersion = 1;
    //table create query
    private static String tableQuery = "Create table Users(_id INTEGER PRIMARY KEY AUTOINCREMENT , _username TEXT NOT NULL , _password TEXT NOT NULL , _email TEXT NOT NULL,_alternative_email TEXT NOT NULL)";

    //constructor for running create database when application runs
    public DbManager(Context context) {
        super(context, dbName , null, dbVersion);
        //this lines create database and open table for write purpose (insert data )
        SQLiteDatabase helper = this.getWritableDatabase();
    }
    
    //insert method
    public void Insertion(String Username, String Password, String Email,String AlternativeEmail){
        //open table for writing purpose edit purpose
        SQLiteDatabase helper = this.getWritableDatabase();
        //save all values by getting from form
        ContentValues data =new ContentValues();
        data.put("_username",Username); //here _username is column name of table 
        data.put("_password",Password);
        data.put("_email",Email);
        data.put("_alternative_email",AlternativeEmail);
        
        //run insert builtin method and passed tablename , and values 
        helper.insert("Users",null,data);

    }
   
    //fetch record from table method
    public Cursor GetData(String Username, String Password){
        //open table for reading purpose and fetch data
        SQLiteDatabase helper = this.getReadableDatabase();
        //cursor is used to check data exists in table or not 
        //and all data that is fetched will be stored in cursor variable "reader" 
        //query is the builtin method for fetch data from table
        String where = "_username=? AND _password =?";
        Cursor reader = helper.query("Users",new String[]{"_id","_username","_password","_email","_alternative_email"},where,new String[]{Username,Password},null,null,null,null);
        
        if(reader!=null){
        
            reader.moveToFirst();
            
            //moveToFirst() means get data from 1st row , beginnning say data lay ga
            // but here begin aur last ki baat nahi data wohi aye ga jis ka username aur password matched hoga 
            //moveToLast() means fetch data from last row , akhri data lay kar ayega
        }

        return reader;

    }
 
    //update Method 
    public void Updation(String id, String Username, String Password, String Email,String AEmail){
        //open table for writable
        SQLiteDatabase helper = this.getWritableDatabase();
        ContentValues data =new ContentValues();
        data.put("_username",Username);
        data.put("_password",Password);
        data.put("_email",Email);
        data.put("_email",AEmail);
    
        //update builtn method for database update data , no need to write query 
        helper.update("Users",data,"_id=?",new String[]{id}); // tablename , data changed value,where argument, where column name
        
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
