package com.example.sns_project.cal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sns_project.R;
import com.example.sns_project.activity.MainActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Schedule extends AppCompatActivity {

    RbPreference pref;
    Uri mImageCaptureUri;

    Button back;
    Button home;

    ImageView img;
    Button write;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        pref = new RbPreference(this);
        setMenu();



        back = (Button)findViewById(R.id.back);
        home = (Button)findViewById(R.id.home);

        img = (ImageView)findViewById(R.id.img);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Schedule.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    TextView missionContent;
    EditText title;
    EditText contents;

    String date;
    private void setMenu() {
        Intent gt = getIntent();

        missionContent = (TextView)findViewById(R.id.missioncontent);

        String content = gt.getExtras().getString("missioncontent");

        missionContent.setText(content);
        contents = (EditText) findViewById(R.id.contents);
        title = (EditText) findViewById(R.id.title);

        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, 0);

            }
        });

        write = (Button) findViewById(R.id.write);
        write.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(contents.getText().toString())) {
                    Toast.makeText(Schedule.this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(title.getText().toString())) {
                    Toast.makeText(Schedule.this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show();

                } else {
                    setInsert();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            if (data != null) {
                try {
                    mImageCaptureUri = data.getData();

                    Log.d("myLog", "mImageCaptureUri  " + mImageCaptureUri);

                    try {
                        Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), mImageCaptureUri);
                        img.setScaleType(ImageView.ScaleType.FIT_XY);
                        img.setImageBitmap(bm);

                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } catch (Exception ex) {
                }

            }
        }
    }


//할 수 이써..!! 그차나 맞자나..!!ㅜㅜㅜ

    private void setInsert() {
        DBHelper1 helper = new DBHelper1(Schedule.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd",
                    Locale.getDefault());
            Date currentTime = new Date();
            date = formatter.format(currentTime);

            ContentValues row;
            row = new ContentValues();
            row.put("img", mImageCaptureUri.toString());
            row.put("title", title.getText().toString());
            row.put("memo", contents.getText().toString());

            db.insert("bucket", null, row);

        } catch (Exception e) {
            Log.e("Thread", "Insert Error", e);
            Toast.makeText(Schedule.this, "등록 실패", Toast.LENGTH_SHORT).show();

        } finally {
            helper.close();
            db.close();
        }

        Toast.makeText(Schedule.this, "등록완료!", Toast.LENGTH_SHORT).show();

        Intent it = new Intent(Schedule.this, Polaroid.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
        finish();

    }
}