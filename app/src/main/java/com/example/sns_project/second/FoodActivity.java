package com.example.sns_project.second;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.sns_project.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;

public class FoodActivity extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        mapView = findViewById(R.id.map_view2);
        mapView.getMapAsync(this);
        //callback이벤트 제공
        FloatingActionButton saveBtn = (FloatingActionButton) findViewById(R.id.memo_save_btn2);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getBaseContext(),LockActivity.class);
               startActivity(intent);





            }
        });
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

        final InfoWindow infoWindow = new InfoWindow();

        CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(new LatLng(35.563984, 129.306087),10);

        naverMap.moveCamera(cameraUpdate);

        final Marker marker = new Marker();
        marker.setPosition(new LatLng(35.963454, 126.957905));
        marker.setMap(naverMap);
        marker.setIcon(OverlayImage.fromResource(R.drawable.marker2));
        marker.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                Intent intent = new Intent(getBaseContext(), HomeFragment2.class);
                startActivity(intent);
                return true;
            }

        });


        Marker marker1 = new Marker();
        marker1.setPosition(new LatLng(35.964217, 126.957058));
        marker1.setMap(naverMap);
        marker1.setIcon(OverlayImage.fromResource(R.drawable.marker2));


        final Marker marker2 = new Marker();
        marker2.setPosition(new LatLng(35.548206, 129.296202));
        marker2.setMap(naverMap);
        marker2.setIcon(OverlayImage.fromResource(R.drawable.marker2));
        marker2.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {

                ViewGroup rootView = (ViewGroup)findViewById(R.id.map_view2);
                PointAdapter adapter = new PointAdapter(FoodActivity.this, rootView);

                infoWindow.setAdapter(adapter);

                //인포창의 우선순위
                infoWindow.setZIndex(10);
                //투명도 조정
                infoWindow.setAlpha(0.9f);
                //인포창 표시
                infoWindow.open(marker2);
                return false;
            }
        });
        final Marker marker3 = new Marker();
        marker3.setPosition(new LatLng(35.531032, 129.293967));
        marker3.setMap(naverMap);
        marker3.setIcon(OverlayImage.fromResource(R.drawable.marker2));
        marker3.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {

                ViewGroup rootView = (ViewGroup)findViewById(R.id.map_view2);
                PointAdapter2 adapter = new PointAdapter2(FoodActivity.this, rootView);

                infoWindow.setAdapter(adapter);

                //인포창의 우선순위
                infoWindow.setZIndex(10);
                //투명도 조정
                infoWindow.setAlpha(0.9f);
                //인포창 표시
                infoWindow.open(marker3);
                return false;
            }
        });
        final Marker marker4 = new Marker();
        marker4.setPosition(new LatLng(35.502887, 129.381536));
        marker4.setMap(naverMap);
        marker4.setIcon(OverlayImage.fromResource(R.drawable.marker2));
        marker4.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {

                ViewGroup rootView = (ViewGroup)findViewById(R.id.map_view2);
                PointAdapter3 adapter = new PointAdapter3(FoodActivity.this, rootView);

                infoWindow.setAdapter(adapter);

                //인포창의 우선순위
                infoWindow.setZIndex(10);
                //투명도 조정
                infoWindow.setAlpha(0.9f);
                //인포창 표시
                infoWindow.open(marker4);
                return false;
            }
        });
        final Marker marker5 = new Marker();
        marker5.setPosition(new LatLng(35.282142, 129.015981));
        marker5.setMap(naverMap);
        marker5.setIcon(OverlayImage.fromResource(R.drawable.marker2));
        marker5.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {

                ViewGroup rootView = (ViewGroup)findViewById(R.id.map_view2);
                PointAdapter4 adapter = new PointAdapter4(FoodActivity.this, rootView);

                infoWindow.setAdapter(adapter);

                //인포창의 우선순위
                infoWindow.setZIndex(10);
                //투명도 조정
                infoWindow.setAlpha(0.9f);
                //인포창 표시
                infoWindow.open(marker5);
                return false;
            }
        });
        final Marker marker6 = new Marker();
        marker6.setPosition(new LatLng(35.350532, 129.329213));
        marker6.setMap(naverMap);
        marker6.setIcon(OverlayImage.fromResource(R.drawable.marker2));
        marker6.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {

                ViewGroup rootView = (ViewGroup)findViewById(R.id.map_view2);
                PointAdapter5 adapter = new PointAdapter5(FoodActivity.this, rootView);

                infoWindow.setAdapter(adapter);

                //인포창의 우선순위
                infoWindow.setZIndex(10);
                //투명도 조정
                infoWindow.setAlpha(0.9f);
                //인포창 표시
                infoWindow.open(marker6);
                return false;
            }
        });
        final Marker marker7 = new Marker();
        marker7.setPosition(new LatLng(35.581714, 129.339584));
        marker7.setMap(naverMap);
        marker7.setIcon(OverlayImage.fromResource(R.drawable.marker2));
        marker7.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {

                ViewGroup rootView = (ViewGroup)findViewById(R.id.map_view2);
                PointAdapter6 adapter = new PointAdapter6(FoodActivity.this, rootView);

                infoWindow.setAdapter(adapter);

                //인포창의 우선순위
                infoWindow.setZIndex(10);
                //투명도 조정
                infoWindow.setAlpha(0.9f);
                //인포창 표시
                infoWindow.open(marker7);
                return false;
            }
        });
        final Marker marker8 = new Marker();
        marker8.setPosition(new LatLng(35.537774, 129.339278));
        marker8.setMap(naverMap);
        marker8.setIcon(OverlayImage.fromResource(R.drawable.marker2));
        marker8.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {

                ViewGroup rootView = (ViewGroup)findViewById(R.id.map_view2);
                PointAdapter7 adapter = new PointAdapter7(FoodActivity.this, rootView);

                infoWindow.setAdapter(adapter);

                //인포창의 우선순위
                infoWindow.setZIndex(10);
                //투명도 조정
                infoWindow.setAlpha(0.9f);
                //인포창 표시
                infoWindow.open(marker8);
                return false;
            }
        });


    }
}