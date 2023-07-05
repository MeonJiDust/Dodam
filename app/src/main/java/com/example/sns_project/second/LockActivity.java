package com.example.sns_project.second;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.sns_project.R;

public class LockActivity extends AppCompatActivity {

   private ImageButton btn_b;     // 파란 자물쇠 버튼
   private ImageButton btn_g;    // 초록 자물쇠 버튼
   private ImageButton btn_p;   // 보라 자물쇠 버튼
    private ImageButton btn_pp;
    private ImageButton btn_oo;
    private ImageButton btn_o;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        btn_b =(ImageButton)findViewById(R.id.btn_b);
        btn_g =(ImageButton) findViewById(R.id.btn_g);
        btn_p = (ImageButton)findViewById(R.id.btn_p);
        btn_pp = (ImageButton)findViewById(R.id.btn_r);
        btn_o = (ImageButton)findViewById(R.id.btn_o);




        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                HomeFragment2 home_frage = new HomeFragment2();
                transaction.replace(R.id.viv,home_frage);
                transaction.commit();
            }
        });

        btn_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                HomeFragment2 home_frage = new HomeFragment2();
                transaction.replace(R.id.viv,home_frage);
                transaction.commit();
            }
        });

        btn_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                HomeFragment2 home_frage = new HomeFragment2();
                transaction.replace(R.id.viv,home_frage);
                transaction.commit();
            }
        });
        btn_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                HomeFragment2 home_frage = new HomeFragment2();
                transaction.replace(R.id.viv,home_frage);
                transaction.commit();
            }
        });

        btn_pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                HomeFragment2 home_frage = new HomeFragment2();
                transaction.replace(R.id.viv,home_frage);
                transaction.commit();
            }
        });

    }
}