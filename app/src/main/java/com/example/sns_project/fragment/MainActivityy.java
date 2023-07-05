package com.example.sns_project.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.sns_project.R;
import com.example.sns_project.activity.MainActivity;


import com.example.sns_project.cal.DBHelper2;
import com.example.sns_project.cal.FailList;
import com.example.sns_project.cal.InputMission;
import com.example.sns_project.cal.Memo;
import com.example.sns_project.cal.MissionUpdate;
import com.example.sns_project.cal.Polaroid;
import com.example.sns_project.cal.RbPreference;
import com.example.sns_project.cal.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MainActivityy extends Fragment {

    TabHost host;

    private MaterialCalendarView calendarView;
    RbPreference pref;
    Context mContext;
    ListView s_list;
    Button photo;
    ArrayList<Memo> sList = new ArrayList<Memo>();
    Button plus;
    Button more;
    String dateMessage;

    String missioncontent;
    @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.activity_mainn, container, false);




s_list = view.findViewById(R.id.s_list);


        host = view.findViewById(R.id.host);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("tap1");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.memo,null));
        spec.setContent(R.id.tab1);
        host.addTab(spec);
        plus = view.findViewById(R.id.plus);
        more = view.findViewById(R.id.more);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), InputMission.class);
                startActivity(intent);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FailList.class);
                startActivity(intent);
            }
        });



        addList();
        spec = host.newTabSpec("tap2");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.calendar,null));
        spec.setContent(R.id.tab2);
        host.addTab(spec);



















        calendarView = view.findViewById(R.id.calendarView);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                int year = date.getYear();;
                int month = date.getMonth();
                int datee = date.getDay();
                String year_string = Integer.toString(year);
                String month_string = Integer.toString(month+1);
                String datee_string = Integer.toString(datee);
                dateMessage = (year_string + "-" + 0+month_string + "-" + datee_string);

                dateCompare();

                Toast.makeText(getActivity(),dateMessage,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Schedule.class);
                intent.putExtra("missioncontent",missioncontent);
                startActivityForResult(intent,0);

            }
        });


        photo = (Button)view.findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Polaroid.class);
                startActivity(intent);

            }
        });



        return view;
    }
    private void dateCompare(){

        DBHelper2 helper = new DBHelper2(calendarView.getContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        String upSql = "SELECT  * from buckets ";

        Log.d("myLog", "sql" + upSql);

        Cursor monthCursor;
        monthCursor = db.rawQuery(upSql, null);

        while (monthCursor.moveToNext()) {
            String idx = monthCursor.getString(0);
            String title = monthCursor.getString(1);
            String memo = monthCursor.getString(2);

            //선택한 날짜 == 입력한 날짜 비교해서 같으면 스케줄에 미션 내용 뜨는거 하고있었어!!

            if (dateMessage == title){
                missioncontent = memo;
                break;
            }
        }

        helper.close();
        db.close();
    }
    private void addList() {
        sList.clear();

        DBHelper2 helper = new DBHelper2(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();


        String upSql = "SELECT  * from buckets ";

        Log.d("myLog", "sql " + upSql);

        try {
            Cursor monthCursor;
            monthCursor = db.rawQuery(upSql, null);

            while (monthCursor.moveToNext()) {
                String idx = monthCursor.getString(0);
                String title = monthCursor.getString(1);
                String memo = monthCursor.getString(2);

                sList.add(new Memo(idx, title, memo));
            }
        } catch (Exception e) {
            Log.e("Thread", "select Error", e);

        } finally {
            helper.close();
            db.close();
        }

        missionList();

    }

    private void missionList() {

        TextView nodata = getActivity().findViewById(R.id.nodata);
        //




        GoodsAdapter mAdapter = new GoodsAdapter(getActivity(), R.layout.mission_list_item, sList);
        //어댑터 객체 선언

        s_list.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


    }

    TextView date;
    TextView title;

    public class GoodsAdapter extends ArrayAdapter<Memo> {
        private ArrayList<Memo> items;
        //어레이리스트 아이템선언

        Memo fInfo;

        public GoodsAdapter(Context context, int textViewResourseId, ArrayList<Memo> items)
        //어레이 리스트
        {
            super(context, textViewResourseId, items);
            this.items = items;
        }
        //어댑터 객체 안에 아이템

        public View getView(int position, final View convertView, ViewGroup parent) {// listview

            View v = convertView;
            //뷰 안에 콘벌트뷰 선언
            fInfo = items.get(position);

            LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.mission_list_item, null);






            date = (TextView) v.findViewById(R.id.date);
            title = (TextView) v.findViewById(R.id.contents);

            date.setText(fInfo.getDates());
            title.setText(fInfo.getMission());

            v.setTag(position);

            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    pos = (Integer) v.getTag();
                    AlertDialog.Builder adialog = new AlertDialog.Builder(
                            convertView.getContext());
                    adialog.setIcon(R.drawable.user);

                    adialog.setPositiveButton("수정",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent gt = new Intent(mContext, MissionUpdate.class);
                                    gt.putExtra("idx" ,items.get(pos).getIdx() );
                                    gt.putExtra("dates" ,items.get(pos).getDates() );
                                    gt.putExtra("mission" ,items.get(pos).getMission() );
                                    startActivity(gt);


                                }
                            }).setNegativeButton("삭제",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.dismiss();
                                    deleteDB(items.get(pos).getIdx());
                                    //선택된 곳의 인덱스 값을 넘겨줘서 내장 DB에서 데이터 삭제
                                }
                            });

                    AlertDialog alert = adialog.create();
                    alert.setTitle("리스트를 수정/삭제하시겠습니까?");
                    alert.show();
                }
            });

            return v;
        }
    }
    int pos;




    private void deleteDB(String idx) {

        DBHelper2 helper = new DBHelper2(getActivity());
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.delete("buckets", "_id='" + idx + "'", null);
        } catch (Exception e) {
            Log.e("Thread", "Insert Error", e);

        } finally {
            helper.close();
            Toast.makeText(calendarView.getContext(), "삭제가 완료 되었습니다",
                    Toast.LENGTH_SHORT).show();
            addList();
        }
    }

}