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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sns_project.R;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ScheduleUpdate extends AppCompatActivity {
    RbPreference pref;
    Uri mImageCaptureUri;

    String idx="";
    ImageView img;
    EditText memo;
    EditText title;

    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_update);

        pref = new RbPreference(this);
        setMenu();

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleUpdate.this, Polaroid.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setMenu() {
        Intent gt = getIntent();

        memo = (EditText) findViewById(R.id.contents);
        title = (EditText) findViewById(R.id.title);

        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, 0);

            }
        });

        memo.setText(gt.getStringExtra("memo"));
        title.setText(gt.getStringExtra("title"));
        idx = gt.getStringExtra("idx");

        mImageCaptureUri = Uri.parse(gt.getStringExtra("img"));

        Uri imgUri = Uri.parse(gt.getStringExtra("img"));

        try {

            try {

                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),
                        imgUri);
                Bitmap resized = Bitmap.createScaledBitmap(bm, bm.getWidth()/4,
                        bm.getWidth()/4, true);
                img.setScaleType(ImageView.ScaleType.FIT_XY);
                img.setImageBitmap(resized);

                bm.recycle();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (Exception ex) {
        }


        Button write = (Button) findViewById(R.id.write);
        write.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(memo.getText().toString())) {
                    Toast.makeText(ScheduleUpdate.this, "내용을 입력해주세요",
                            Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(title.getText().toString())) {
                    Toast.makeText(ScheduleUpdate.this, "제목을 입력해주세요",
                            Toast.LENGTH_SHORT).show();

                } else {
                    setInsert();
                }

            }
        });
    }


    private void setInsert() {
        DBHelper1 helper = new DBHelper1(ScheduleUpdate.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        try {

            String strFilter = "_id=" + idx;

            ContentValues row;
            row = new ContentValues();
            row.put("img", mImageCaptureUri.toString());
            row.put("title", title.getText().toString());
            row.put("memo", memo.getText().toString());

            db.update("bucket", row, strFilter, null);

        } catch (Exception e) {
            Log.e("Thread", "Insert Error", e);
            Toast.makeText(ScheduleUpdate.this, "수정 실패", Toast.LENGTH_SHORT)
                    .show();

        } finally {
            helper.close();
            db.close();
        }

        Toast.makeText(ScheduleUpdate.this, "수정완료!", Toast.LENGTH_SHORT).show();

        Intent it = new Intent(ScheduleUpdate.this, Polaroid.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
        finish();

    }
}