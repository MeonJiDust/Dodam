

package com.example.sns_project.fourth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {

    DBHelper(Context context) {
        super(context, "Favor.db", null, 1);  // Kids.db ��
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table park(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "num TEXT,  name TEXT,  cate TEXT , post TEXT , addr1 TEXT,  addr2 TEXT , lat TEXT ,  lng TEXT ,  width TEXT ,helath TEXT , enjoy TEXT , play TEXT , gyoyang TEXT ,  etc TEXT , date1 TEXT , admin TEXT , phone TEXT )";

        //park 테이블 생성 : 관리번호 , 이름 , 졸류 , 우편번호 , 지번주소 , 도로주소 , 위도 , 경도 , 면적 , 운동시설 , 유희시설 , 편의시설 , 교양시설 , 기타시설 , 지정고시일 , 관리기관 , 연락처


        db.execSQL(sql);

        String sql1 = "create table health(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "num TEXT,  name TEXT,  addr TEXT,  count1 TEXT , con1 TEXT ,  count2 TEXT ,  con2 TEXT  )";

        db.execSQL(sql1);

        //공원별 기구 이름 개수


        String sql2 = "create table use(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "img TEXT,  name TEXT,  cate TEXT,  con TEXT )";


        db.execSQL(sql2);

        //기구이름및 사용법


        String sql3 = "create table loc(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT,  park TEXT,  use TEXT,  lat TEXT , lng TEXT )";


        db.execSQL(sql3);

        //체육시설위치



        String sql4 = "create table age(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "sex TEXT,  health TEXT,  img TEXT,  use TEXT , con  TEXT)";

        db.execSQL(sql4);

        //성별 연령별 운동법
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST member");
        onCreate(db);

    }
}
