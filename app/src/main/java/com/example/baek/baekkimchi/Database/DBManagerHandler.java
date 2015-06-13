package com.example.baek.baekkimchi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.StringTokenizer;

/**
 * Created by Baek on 2015-06-12.
 */
public class DBManagerHandler {
    private DBManager mDBManager;
    private SQLiteDatabase db;

    public DBManagerHandler (Context context){
        this.mDBManager =new DBManager(context);
    }
    //닫기
    public void close() {
        db.close();
    }
    // insert
    public void insertInform(int age, String gender, int cost){
        db = mDBManager.getWritableDatabase(); // db 객체를 얻어온다. 쓰기 가능
        ContentValues values = new ContentValues();
        values.put("age", age);
        values.put("gender", gender);
        values.put("cost", cost);
        db.insert("inform_table", null, values);
    }

    // update
    public void update (String query) {
        db = mDBManager.getWritableDatabase(); //db 객체를 얻어온다. 쓰기가능
        db.execSQL(query);
    }

    // select
    public Cursor selectInform(String query){
        db = mDBManager.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{});
        return cursor;
    }
}


/**  업데이트방법
 *     public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {}
 *
 * 삭제방법
 *     public int delete(String table, String whereClause, String[] whereArgs) {}
 */

