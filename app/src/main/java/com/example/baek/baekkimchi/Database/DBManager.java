package com.example.baek.baekkimchi.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Baek on 2015-06-12.
 */

public class DBManager extends SQLiteOpenHelper {
    private final static String TB_NAME1 = "inform_table";

    public static final String DB_NAME = "chacha.db";
    public static final int DB_VERSION = 1;

    private String query_inform;

    //constructor
    public DBManager(Context context){
        super(context, DB_NAME, null, DB_VERSION);

        //테이블 그룹 ROW (id, 그룹이름)
        query_inform = "CREATE TABLE " + TB_NAME1 + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "age INTEGER, "
                + "gender TEXT, "
                + "cost INTEGER "
                + ");";
    }

    //테이블 생성
    public void onCreate(SQLiteDatabase db){
        db.execSQL(query_inform);
    }

    // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME1);
        onCreate(db);
    }

}

