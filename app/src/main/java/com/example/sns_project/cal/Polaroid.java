package com.example.sns_project.cal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sns_project.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Polaroid extends AppCompatActivity {

    RbPreference pref;
    Context mContext;


    RecyclerView recyclerView;

    RecyclerAdapter recyclerAdapter;

    ArrayList<NoticeModel> sList = new ArrayList<NoticeModel>();


    Button home;
    Button back;

    TextView nodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        pref = new RbPreference(mContext);

        setContentView(R.layout.activity_polaroid);


        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getFac();

    }

    private void getFac(){

        sList.clear();
        DBHelper1 helper = new DBHelper1(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String upSql = "SELECT * FROM bucket ";

        Log.d("myLog", "sql " + upSql);

        try {
            Cursor monthCursor;
            monthCursor = db.rawQuery(upSql, null);

            while (monthCursor.moveToNext()) {
                String idx = monthCursor.getString(0);
                String img = monthCursor.getString(1);
                String memo = monthCursor.getString(2);
                String title = monthCursor.getString(3);

                sList.add(new NoticeModel(idx, img, memo, title));
            }
        } catch (Exception e) {
            Log.e("Thread", "select Error", e);

        } finally {
            helper.close();
            db.close();
        }
        displayList();
    }

    private void displayList(){
        recyclerView = findViewById(R.id.viv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addItemDecoration(new RecyclerDecoration(this));

        nodata = (TextView) findViewById(R.id.nodata);


        if (sList.size() > 0) {
            nodata.setVisibility(View.GONE);
        } else {
            nodata.setVisibility(View.VISIBLE);
        }


        recyclerAdapter = new RecyclerAdapter(sList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();


    }



//시혀나 넌 할 수 이써,,

    //리사이클러 어댑터 생성
    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

        private ArrayList<NoticeModel> listdata;

        public RecyclerAdapter(ArrayList<NoticeModel> listdata) {
            this.listdata = listdata;
        }

        View view;

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);



            return new ItemViewHolder(view);

        }

        @Override
        public int getItemCount() {
            return listdata.size();
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
            NoticeModel noticeModel = listdata.get(position);

            holder.image.setTag(noticeModel.getImg());
            holder.edittitle.setText(noticeModel.getTitle());
            holder.editmemo.setText(noticeModel.getMemo());

            Uri imgUri = Uri.parse(noticeModel.getImg());

            try {

                try {

                    Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);
                    Bitmap resized = Bitmap.createScaledBitmap(bm, bm.getWidth() / 4,
                            bm.getWidth() / 4, true);
                    holder.image.setScaleType(ImageView.ScaleType.FIT_XY);
                    holder.image.setImageBitmap(resized);

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

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Polaroid.this,PolaroidIntent.class);
                    Bundle ex = new Bundle();
                    ex.putSerializable("list", listdata.get(position));
                    intent.putExtras(ex);
                    startActivity(intent);


                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder adialog = new AlertDialog.Builder(
                            Polaroid.this);
                    adialog.setIcon(R.drawable.user);

                    adialog.setPositiveButton("수정",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Intent gt = new Intent(mContext, ScheduleUpdate.class);
                                    gt.putExtra("idx" ,listdata.get(position).getIdx() );
                                    gt.putExtra("title" ,listdata.get(position).getTitle() );
                                    gt.putExtra("memo" ,listdata.get(position).getMemo() );
                                    gt.putExtra("img" ,listdata.get(position).getImg() );
                                    startActivity(gt);


                                }
                            }).setNegativeButton("삭제",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.dismiss();
                                    deleteDB(listdata.get(position).getIdx());
                                    //선택된 곳의 인덱스 값을 넘겨줘서 내장 DB에서 데이터 삭제
                                }
                            });
                    AlertDialog alert = adialog.create();
                    alert.setTitle("리스트를 수정/삭제 하시겠습니까?");
                    alert.show();
                    return false;
                }
            });

        }






        public class ItemViewHolder extends RecyclerView.ViewHolder {


            private ImageView image;
            private TextView editmemo;
            private TextView edittitle;



            public ItemViewHolder(@NonNull View itemView) {
                super(itemView);

                editmemo = itemView.findViewById(R.id.editmemo);
                edittitle = itemView.findViewById(R.id.edittitle);
                image = itemView.findViewById(R.id.image);





            }
        }


        private void deleteDB(String idx) { // 내장DB에서 삭제
            DBHelper1 helper = new DBHelper1(getBaseContext());
            SQLiteDatabase db = helper.getWritableDatabase(); // DB를 오픈한다
            try {
                db.delete("bucket", "_id='" + idx + "'", null); // 삭제
            } catch (Exception e) {
                Log.e("Thread", "Insert Error", e);
            } finally {
                helper.close();
                Toast.makeText(Polaroid.this, "삭제가 완료 되었습니다",
                        Toast.LENGTH_SHORT).show();

                getFac(); //참여마당 정보 가져온다
            }
        }
    }
}