package com.example.sns_project.cal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sns_project.R;
import com.example.sns_project.activity.MainActivity;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PolaroidIntent extends AppCompatActivity {

    RbPreference pref;
    Uri mImageCaptureUri;

    Button back;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polaroid_intent);

        pref = new RbPreference(this);

        setMenu();


    }

    NoticeModel mData;

    private void setMenu() {
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PolaroidIntent.this, Polaroid.class);
                startActivity(intent);
                finish();

            }
        });

        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent gt = new Intent(PolaroidIntent.this, MainActivity.class);
                gt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gt);
                finish();

            }
        });


        Intent gt = getIntent();
        mData = (NoticeModel) gt.getSerializableExtra("list");

        TextView contents = (TextView) findViewById(R.id.contents);
        TextView title = (TextView) findViewById(R.id.title);

        contents.setText(mData.getMemo());
        title.setText(mData.getTitle());

        ImageView img = (ImageView) findViewById(R.id.img);
        Uri imgUri = Uri.parse(mData.getImg());

        try {

            try {

                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),
                        imgUri);
                Bitmap resized = Bitmap.createScaledBitmap(bm, bm.getWidth() / 4,
                        bm.getWidth() / 4, true);
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

        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent gt = new Intent(PolaroidIntent.this, GalleryActivity.class);
                gt.putExtra("img", mData.getImg());
                startActivity(gt);
                finish();

            }
        });


    }
}