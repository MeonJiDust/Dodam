package com.example.sns_project.second;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.sns_project.R;
import com.naver.maps.map.overlay.InfoWindow;

public class PointAdapter extends InfoWindow.DefaultViewAdapter{
    private final Context mContext;
    private final ViewGroup mParent;



    public PointAdapter(@NonNull Context context, ViewGroup parent) {
        super(context);
        mContext = context;
        mParent = parent;
    }


    @NonNull
    @Override
    protected View getContentView(@NonNull InfoWindow infoWindow)
    {

        View view = (View) LayoutInflater.from(mContext).inflate(R.layout.item_point, mParent, false);

        TextView txtTitle = (TextView) view.findViewById(R.id.txttitle);
        ImageView imagePoint = (ImageView) view.findViewById(R.id.imagepoint);
        TextView txtAddr = (TextView) view.findViewById(R.id.txtaddr);
        TextView txtTel = (TextView) view.findViewById(R.id.txttel);

        txtTitle.setText("건나블리 산책");
        imagePoint.setImageResource(R.drawable.po1);
        txtAddr.setText("울산 중구 태화강국가정원길 154(태화강 국가 정원)");
        txtTel.setText("064-710-2114");

        return view;
    }
}
