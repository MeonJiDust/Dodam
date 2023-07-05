package com.example.sns_project.fourth;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.sns_project.R;
import com.example.sns_project.cal.TouchImageView;

import java.io.IOException;
import java.io.InputStream;

public class GalleryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Intent gt = getIntent();
        com.example.sns_project.cal.TouchImageView tt = (TouchImageView)findViewById(R.id.tou);

        String filename = gt.getStringExtra("img")+".JPG";

        try {

            // get input stream
            InputStream ims =getBaseContext().getResources().getAssets().open("park2.xls");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            tt.setImageDrawable(d);
        }
        catch(IOException ex) {
        }


    }






}
