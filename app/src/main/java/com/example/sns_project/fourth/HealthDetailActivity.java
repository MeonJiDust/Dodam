package com.example.sns_project.fourth;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sns_project.R;
import com.example.sns_project.activity.MainActivity;
import com.example.sns_project.cal.RbPreference;

import java.io.IOException;
import java.io.InputStream;


public class HealthDetailActivity extends Activity {


    RbPreference pref;
    Context mContext;
    ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        pref = new RbPreference(mContext);

        setContentView(R.layout.activity_health_detail);

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
                Intent gt = new Intent(mContext, MainActivity.class);
                gt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gt);
                finish();

            }
        });


        display();

    }


    HealthData pData;

    private void display() {
        Intent gt = getIntent();
        pData = (HealthData) gt.getSerializableExtra("list"); //HealthListActivity 로 부터 온값을 받아서 pData 객체에 저장

        TextView nameTxt = (TextView) findViewById(R.id.name);
        TextView cate = (TextView) findViewById(R.id.cate);
        TextView cons = (TextView) findViewById(R.id.cons);
        ImageView img = (ImageView) findViewById(R.id.img);


        nameTxt.setText(pData.getUse());


        cate.setText(pData.getHealth());


        cons.setText(pData.getCon());


        String filename = pData.getImg() + ".JPG";

        try {

            // get input stream
            InputStream ims = getAssets().open("park2.xls");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            img.setImageDrawable(d);
        } catch (IOException ex) {
        }

        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent gt = new Intent(mContext, GalleryActivity.class);
                gt.putExtra("img", pData.getImg());
                startActivity(gt);
                finish();

            }
        });


    }

    String sidx = "";
    String simg = "";
    String sname = "";
    String scate = "";
    String scon = "";


}
