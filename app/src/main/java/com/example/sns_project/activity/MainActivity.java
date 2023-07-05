package com.example.sns_project.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sns_project.MyService;
import com.example.sns_project.R;

import com.example.sns_project.fragment.HomeFragment;
import com.example.sns_project.fragment.MainActivityy;
import com.example.sns_project.fragment.UserInfoFragment;
import com.example.sns_project.fragment.UserListFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton saveBtn = (FloatingActionButton) findViewById(R.id.logButton);

saveBtn.setOnClickListener(new View.OnClickListener() {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getBaseContext(), MyService.class);
        intent.setAction("startForeground");
        if((Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)){
            startForegroundService(intent);

        }
        else{
            startService(intent);
        }
    }
});
        init();
        //init메소드
    }

    // 주변 카테고리 리스트
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showCategoryList() {
       Intent intent = new Intent(getBaseContext(), MyService.class);
       intent.setAction("startForeground");
       if(Build.VERSION.SDK_INT>Build.VERSION_CODES.ECLAIR_0_1){
           startForegroundService(intent);

        }
       else{
           startService(intent);
        }

    }
        private void startSignUpActivity() {
            Intent intent = new Intent(getBaseContext(), SignUpActivity.class);
            startActivity(intent);
        }


    @Override
    protected void onResume() {
        super.onResume();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                init();
                break;
                //스위치 케이스문으로 init메소드 선언

        }
    }

    private void init(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            myStartActivity(SignUpActivity.class);
        } else {
            //사용자 정보 가져오기
            DocumentReference documentReference = FirebaseFirestore.getInstance().collection("users").document(firebaseUser.getUid());
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                                myStartActivity(MemberInitActivity.class);
                            }
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.viv, homeFragment)
                    .commit();
            //홈프레그먼트라는 객체 안에서 서포트 프레그먼트 메너지로 인해 트랜색션을 시작한다
            //새로운 컨테이너로 대체한다


            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.home:
                            HomeFragment homeFragment = new HomeFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.viv, homeFragment)
                                    .commit();
                            return true;
                        case R.id.myInfo:
                            UserInfoFragment userInfoFragment = new UserInfoFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.viv, userInfoFragment)
                                    .commit();
                            return true;
                        case R.id.userList:
                            UserListFragment userListFragment = new UserListFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.viv, userListFragment)
                                    .commit();
                            return true;
                        case R.id.Calander:
                            MainActivityy mainActivityy = new MainActivityy();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.viv, mainActivityy)
                                    .commit();
                    }
                    return false;
                }
            });
        }
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivityForResult(intent, 1);
    }
}