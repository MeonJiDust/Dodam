package com.example.sns_project.cal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper2 extends SQLiteOpenHelper {

    public DBHelper2(Context context) {
        super(context, "mission.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String sql3 = "create table buckets (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dates TEXT, mission TEXT )";
        db.execSQL(sql3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sc");
        onCreate(db);

    }
}

