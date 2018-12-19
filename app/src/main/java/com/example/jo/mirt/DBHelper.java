package com.example.jo.mirt;

/**
 * Created by jo on 8/12/2018.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Mirt.db";
    public static final String CATEGORIES_TABLE_NAME = "categories";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table categories " +
                        "(id integer primary key, name text,type text)"
        );

        db.execSQL(
                "create table campanies " +
                "(id integer primary key, name text,category_id int,status text,about text)"
                );

}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS categories");
        db.execSQL("DROP TABLE IF EXISTS campanies");

        onCreate(db);
    }

    public boolean insert_category (int id,String name,String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("type", type);
        db.insert("categories", null, contentValues);
        return true;
    }

    public boolean insert_profile (int id,String name,int category_id,String status,String about) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("status", status);
        contentValues.put("about", about);
        contentValues.put("category_id", category_id);
        db.insert("campanies", null, contentValues);
        return true;
    }
    public Cursor getAllcategories() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from categories", null );
        res.moveToFirst();
        return res;
    }
    public int getProfilesCount(String TABLE_NAME) {
        String countQuery = "SELECT  * FROM " +TABLE_NAME ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public int getCompaniesCount(String CATEGORY_ID) {
        String countQuery = "SELECT  * FROM campanies where category_id=" +CATEGORY_ID ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public Cursor getAllComapnies(String id) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from campanies where category_id="+id, null );
        res.moveToFirst();
        return res;
    }
    public String getCompanyCategory(String id) {
        String category_name="";

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from categories where id="+id, null );
      //  String category_name=res.getString(res.getColumnIndex("name" ));
            res.moveToFirst();
            category_name = res.getString(res.getColumnIndex("name"));
        return  category_name;
    }
    public String[] getCompanyProfile(String id) {
        String[] categories= new String[5];

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from campanies where id="+id, null );
        res.moveToFirst();
        categories[0] = res.getString(res.getColumnIndex("name"));
        categories[1] = res.getString(res.getColumnIndex("status"));
        categories[2] = res.getString(res.getColumnIndex("about"));
        return  categories;
    }


}