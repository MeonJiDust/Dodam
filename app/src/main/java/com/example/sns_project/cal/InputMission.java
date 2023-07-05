package com.example.sns_project.cal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.datepicker.rell.datapickerlib.RellDatePicker;
import com.example.sns_project.R;
import com.example.sns_project.activity.MainActivity;
import com.example.sns_project.fragment.MainActivityy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.Inflater;

public class InputMission extends AppCompatActivity {

    RbPreference pref;

    TextView mMemo;
    EditText editmis;


    Button save;
    Button selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_mission);
        pref = new RbPreference(this);

        setMenu();


    }


   public final View.OnClickListener onSelectDateListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RellDatePicker picker = createDatePicker();
            picker.show(getSupportFragmentManager(), onDatePickListener);
        }

        public RellDatePicker.OnDatePickListener onDatePickListener = new RellDatePicker.OnDatePickListener() {
            @Override
            public void onDatePick(Calendar calendar) {


                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                mMemo.setText(format.format(calendar.getTime()));

            }
        };
    };

    private RellDatePicker createDatePicker(){//캘린더 다이얼로그 불러오는 메소드..???
        RellDatePicker picker = new RellDatePicker.Builder(InputMission.this)
                .setDate(Calendar.getInstance()).setMinDate(Calendar.getInstance().getTimeInMillis()).create();
        return picker;
    }
    //Calender.getInstance()는 현재 시간 정보를 불러온다.

    private void setMenu() {
        Intent gt = getIntent();

        mMemo = (TextView)findViewById(R.id.mMemo);
        editmis = (EditText) findViewById(R.id.editmis);


        selectDate = (Button)findViewById(R.id.selectDate);
        selectDate.setOnClickListener(onSelectDateListener);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mMemo.getText().toString())) {
                    Toast.makeText(InputMission.this, "날짜를 선택해주세요!",
                            Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(editmis.getText().toString())) {
                    Toast.makeText(InputMission.this, "미션 내용을 입력해주세요!",
                            Toast.LENGTH_SHORT).show();

                } else {
                    setInsert();

                }

            }
        });
    }

    public void replaceFragment(MainActivityy mainActivityy){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.linlin, mainActivityy);
        fragmentTransaction.commit();
    }
    private void setInsert() {
        DBHelper2 helper = new DBHelper2(InputMission.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        try {


            ContentValues row;
            row = new ContentValues();
            row.put("dates", mMemo.getText().toString());
            row.put("mission", editmis.getText().toString());

            db.insert("buckets", null, row);

        } catch (Exception e) {
            Log.e("Thread", "Insert Error", e);
            Toast.makeText(InputMission.this, "등록 실패", Toast.LENGTH_SHORT).show();

        } finally {
            helper.close();
            db.close();
        }
//카린안에서 토스트메세지 입력한 상태에서 토스트 메세지 입력 ㄷㄹ=

        Toast.makeText(InputMission.this, "등록완료!", Toast.LENGTH_SHORT).show();
       MainActivityy mainActivityy = new MainActivityy();
       Bundle bundle = new Bundle();

        bundle.putString("dates",mMemo.getText().toString());
        bundle.putString("mission",editmis.getText().toString());
       mainActivityy.setArguments(bundle);




        replaceFragment(mainActivityy);




    }


}