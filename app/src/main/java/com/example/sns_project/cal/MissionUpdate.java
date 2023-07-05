package com.example.sns_project.cal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sns_project.R;
import com.example.sns_project.activity.MainActivity;

public class MissionUpdate extends AppCompatActivity {

    RbPreference pref;

    String idx;
    TextView mMemo;
    EditText editmis;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_update);

        pref = new RbPreference(this);

        setMenu();
    }

    //아직 날짜 sql에 넘기는거 안 했어..!!!

    private void setMenu() {
        Intent gt = getIntent();

        mMemo = (TextView) findViewById(R.id.mMemo);
        editmis= (EditText) findViewById(R.id.editmis);

        idx = gt.getStringExtra("idx");
        mMemo.setText(gt.getStringExtra("dates"));
        editmis.setText(gt.getStringExtra("mission"));




        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mMemo.getText().toString())) {
                    Toast.makeText(MissionUpdate.this, "내용을 입력해주세요",
                            Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(editmis.getText().toString())) {
                    Toast.makeText(MissionUpdate.this, "제목을 입력해주세요",
                            Toast.LENGTH_SHORT).show();

                } else {
                    setInsert();
                }

            }
        });
    }


    private void setInsert() {
        DBHelper2 helper = new DBHelper2(MissionUpdate.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        try {

            String strFilter = "_id=" + idx;

            ContentValues row;
            row = new ContentValues();
            row.put("dates", mMemo.getText().toString());
            row.put("mission", editmis.getText().toString());

            db.update("buckets", row, strFilter, null);

        } catch (Exception e) {
            Log.e("Thread", "Insert Error", e);
            Toast.makeText(MissionUpdate.this, "수정 실패", Toast.LENGTH_SHORT)
                    .show();

        } finally {
            helper.close();
            db.close();
        }

        Toast.makeText(MissionUpdate.this, "수정완료!", Toast.LENGTH_SHORT).show();

        Intent it = new Intent(MissionUpdate.this, MainActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
        finish();

    }
}