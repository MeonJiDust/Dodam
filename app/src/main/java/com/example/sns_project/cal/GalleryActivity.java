package com.example.sns_project.cal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sns_project.R;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Intent gt = getIntent();
        TouchImageView img = (TouchImageView) findViewById(R.id.tou);
        Uri imgUri = Uri.parse(gt.getStringExtra("img"));

        try {

            try {

                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),
                        imgUri);
                Bitmap resized = Bitmap.createScaledBitmap(bm, bm.getWidth()/4,
                        bm.getWidth()/4, true);
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


    }
}