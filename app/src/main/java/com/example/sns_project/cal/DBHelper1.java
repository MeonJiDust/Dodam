package com.example.sns_project.cal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper1 extends SQLiteOpenHelper {

    public DBHelper1(Context context) {
        super(context, "sc.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String sql2 = "create table bucket(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "img  TEXT,  memo TEXT , title TEXT)";
        db.execSQL(sql2);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST sc");
        onCreate(db);

    }
}
