package com.example.sns_project.fourth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sns_project.R;
import com.example.sns_project.activity.MainActivity;

public class HealthActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });

        Button home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent gt = new Intent(HealthActivity.this, MainActivity.class);
                gt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gt);
                finish();

            }
        });

        Button aged = (Button)findViewById(R.id.aged);
        Button man = (Button)findViewById(R.id.man);
        Button woman = (Button)findViewById(R.id.woman);

        aged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gt = new Intent(HealthActivity.this , HealthListActivity.class);

                startActivity(gt);
            }
        });//노년층 추천 운동

        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gt = new Intent(HealthActivity.this , HealthListActivity.class);

                startActivity(gt);
            }
        });//남자 추천 운동

        woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gt = new Intent(HealthActivity.this , HealthListActivity.class);

                startActivity(gt);
            }
        });//여자 추천 운동





    }


}
