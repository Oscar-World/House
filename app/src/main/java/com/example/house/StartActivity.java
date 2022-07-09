package com.example.house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class StartActivity extends AppCompatActivity {

    String TAG = "초기화면";
    Handler handler = new Handler();
    Animation appear;
    Button startbutton;
    Button setbutton;
    Button resetbutton;
    ImageView houseimage;
    TextView textView;
    Animation diceanim;
    Animation translate;

    SharedPreferences sharedPreferences;
    String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start); // start.xml 파일을 (레이아웃) 세팅하겠다.
        Log.d(TAG, "onCreate() 호출됨"); // 로그를 사용할 수 있다. 해당 동작이 잘 작동하는지 확인을 도와준다.

        startbutton = findViewById(R.id.startbutton); // 레이아웃의 모든 요소에는 id를 부여할 수 있다. 해당 id를 호출하여 객체와 연결해준다.
        setbutton = findViewById(R.id.setaccountbutton);
        resetbutton = findViewById(R.id.resetbutton);
        houseimage = findViewById(R.id.houseimageview);
        textView = findViewById(R.id.housetext);
        diceanim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.dicetest);
        translate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
        appear = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.boxappear);



    }

    public class SleepThread extends Thread {
        public void run() {
            try {
                Thread.sleep(6500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (input.equals("")) {
                        setbutton.startAnimation(appear);
                        startbutton.startAnimation(appear);
                        setbutton.setVisibility(View.VISIBLE);
                        startbutton.setVisibility(View.VISIBLE);
                    }

                    else if (!input.equals("")) {
                        startbutton.startAnimation(appear);
                        resetbutton.startAnimation(appear);
                        startbutton.setVisibility(View.VISIBLE);
                        resetbutton.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
    }


    //onCreate 이외의 생명주기 메소드는 별도로 작성해주지 않아도 자동 호출됨으로 앱 실행에는 지장이 없다.
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() 호출됨");

        sharedPreferences = getSharedPreferences("사용자 정보",MODE_PRIVATE);
        input = sharedPreferences.getString("정보","");

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.equals("")){
                    Toast t = Toast.makeText(startbutton.getContext(),"계정을 먼저 생성해주세요",Toast.LENGTH_SHORT);
                    t.show();
                }
                else if (!input.equals("")){
                    Intent i = new Intent(StartActivity.this,Casual_mode_selectActivity.class);
                    startActivity(i);

                    setbutton.setVisibility(View.INVISIBLE);
                    startbutton.setVisibility(View.INVISIBLE);
                    resetbutton.setVisibility(View.INVISIBLE);

                }
            }
        });

        setbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setbutton.setVisibility(View.INVISIBLE);
                startbutton.setVisibility(View.INVISIBLE);
                Intent i = new Intent(StartActivity.this, SettingActivity.class);
                // 인텐트는 앱의 구성요소들을 통신해주는 역할을 한다. 위 괄호안의 내용은 액티비티의 전환이며 다음과 같다. (출발점, 도착점)
                startActivity(i); // 인텐트 객체를 시작한다.
            }
        });

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetbutton.setVisibility(View.INVISIBLE);
                startbutton.setVisibility(View.INVISIBLE);
                Intent i = new Intent(StartActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });

        houseimage.startAnimation(diceanim);
        textView.startAnimation(translate);

        SleepThread sleepThread = new SleepThread();
        sleepThread.start();

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() 호출됨");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() 호출됨");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() 호출됨");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart() 호출됨");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() 호출됨");
    }

}