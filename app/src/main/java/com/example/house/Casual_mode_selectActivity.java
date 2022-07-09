package com.example.house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Casual_mode_selectActivity extends AppCompatActivity {

    String TAG = "게임 선택";

    TextView leveltext;
    TextView moneytext;
    TextView nametext;
    TextView countrytext;
    TextView exptext;
    Button game1button;
    Button game2button;
    Button game3button;
    Button randomBox_button;
    Button game6button;
    Button backbutton;
    Button game2blockbutton;
    Button game3blockbutton;
    Button game6blockbutton;
    ImageButton editbutton;
    ImageView imageView;

    ImageView korea;
    ImageView usa;
    ImageView egypt;
    ImageView vietnam;
    ImageView germany;
    ImageView mongolia;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String input;
    String[] info;
    UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.casual_mode_select);
        Log.d(TAG, "onCreate() 호출됨");

        leveltext = findViewById(R.id.casual_leveltext);
        moneytext = findViewById(R.id.casual_moneytext);
        nametext = findViewById(R.id.casual_nametext);
        countrytext = findViewById(R.id.casual_countrytext);
        exptext = findViewById(R.id.casual_expText);
        game1button = findViewById(R.id.game1_button);
        game2button = findViewById(R.id.game2_button);
        game3button = findViewById(R.id.game3_button);
        randomBox_button = findViewById(R.id.randomBox_button);
        game6button = findViewById(R.id.game6_button);
        backbutton = findViewById(R.id.select_backbutton);
        game2blockbutton = findViewById(R.id.game2_blockbutton);
        game3blockbutton = findViewById(R.id.game3_blockbutton);
        game6blockbutton = findViewById(R.id.game6_blockbutton);
        editbutton = findViewById(R.id.info_editBtn);
        imageView = findViewById(R.id.casual_imageview);

        korea = findViewById(R.id.casual_koreaImage);
        usa = findViewById(R.id.casual_usaImage);
        egypt = findViewById(R.id.casual_egyptImage);
        vietnam = findViewById(R.id.casual_vietnamImage);
        germany = findViewById(R.id.casual_germanyImage);
        mongolia = findViewById(R.id.casual_mongoliaImage);

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() 호출됨");

        sharedPreferences = getSharedPreferences("사용자 정보", MODE_PRIVATE);
        input = sharedPreferences.getString("정보", "");
        info = input.split(",");
        editor = sharedPreferences.edit();
        user = new UserInfo(Integer.parseInt(info[2]),Integer.parseInt(info[3]),Integer.parseInt(info[4]));
        user.fullexp = Integer.parseInt(info[5]);

        Log.d(TAG,"저장된 이미지 정보 : " + info[6]);

        countrytext.setText("       " + info[0]);
        nametext.setText(info[1]);
        leveltext.setText("Lv." + info[2]);
        exptext.setText("exp : " + info[3] + " / " + info[5]);
        moneytext.setText("골드 : " + info[4]);

        Glide.with(getApplicationContext())
                .load(Uri.parse(info[6]))
                .into(imageView);

        Log.d(TAG,"저장된 국가 정보 : " + info[0]);

        if (info[0].equals("대한민국")) {
            korea.setVisibility(View.VISIBLE);
            usa.setVisibility(View.INVISIBLE);
            egypt.setVisibility(View.INVISIBLE);
            vietnam.setVisibility(View.INVISIBLE);
            germany.setVisibility(View.INVISIBLE);
            mongolia.setVisibility(View.INVISIBLE);
        }
        else if (info[0].equals("미국")) {
            korea.setVisibility(View.INVISIBLE);
            usa.setVisibility(View.VISIBLE);
            egypt.setVisibility(View.INVISIBLE);
            vietnam.setVisibility(View.INVISIBLE);
            germany.setVisibility(View.INVISIBLE);
            mongolia.setVisibility(View.INVISIBLE);
        }
        else if (info[0].equals("이집트")) {
            korea.setVisibility(View.INVISIBLE);
            usa.setVisibility(View.INVISIBLE);
            egypt.setVisibility(View.VISIBLE);
            vietnam.setVisibility(View.INVISIBLE);
            germany.setVisibility(View.INVISIBLE);
            mongolia.setVisibility(View.INVISIBLE);
        }
        else if (info[0].equals("베트남")) {
            korea.setVisibility(View.INVISIBLE);
            usa.setVisibility(View.INVISIBLE);
            egypt.setVisibility(View.INVISIBLE);
            vietnam.setVisibility(View.VISIBLE);
            germany.setVisibility(View.INVISIBLE);
            mongolia.setVisibility(View.INVISIBLE);
        }
        else if (info[0].equals("독일")) {
            korea.setVisibility(View.INVISIBLE);
            usa.setVisibility(View.INVISIBLE);
            egypt.setVisibility(View.INVISIBLE);
            vietnam.setVisibility(View.INVISIBLE);
            germany.setVisibility(View.VISIBLE);
            mongolia.setVisibility(View.INVISIBLE);
        }
        else if (info[0].equals("몽골")) {
            korea.setVisibility(View.INVISIBLE);
            usa.setVisibility(View.INVISIBLE);
            egypt.setVisibility(View.INVISIBLE);
            vietnam.setVisibility(View.INVISIBLE);
            germany.setVisibility(View.INVISIBLE);
            mongolia.setVisibility(View.VISIBLE);
        }

        if (user.level < 3) {
            game2button.setVisibility(View.INVISIBLE);
            game3button.setVisibility(View.INVISIBLE);
            game6button.setVisibility(View.INVISIBLE);
            game2blockbutton.setVisibility(View.VISIBLE);
            game3blockbutton.setVisibility(View.VISIBLE);
            game6blockbutton.setVisibility(View.VISIBLE);
        }

        if (user.level >= 1) {
            game2blockbutton.setVisibility(View.INVISIBLE);
            game2button.setVisibility(View.VISIBLE);
        }

        if (user.level >= 1) {
            game3blockbutton.setVisibility(View.INVISIBLE);
            game3button.setVisibility(View.VISIBLE);
        }

        if (user.level >= 1) {
            game6blockbutton.setVisibility(View.INVISIBLE);
            game6button.setVisibility(View.VISIBLE);
        }

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Casual_mode_selectActivity.this, EditinfoActivity.class);
                startActivity(i);
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        randomBox_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Casual_mode_selectActivity.this, RandomBoxActivity.class);
                startActivity(i);
            }
        });

        game1button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Casual_mode_selectActivity.this, Game1Activity.class);
                startActivity(i);
            }
        });

        game2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Casual_mode_selectActivity.this, Game2Activity.class);
                startActivity(i);
            }
        });

        game3button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Casual_mode_selectActivity.this, Game3Activity.class);
                startActivity(i);
            }
        });

        game6button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Casual_mode_selectActivity.this, Game6Activity.class);
                startActivity(i);
            }
        });

        game2blockbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(Casual_mode_selectActivity.this,"아직 개방되지 않았습니다.\n개방 조건 : 3레벨 달성",Toast.LENGTH_SHORT);
                t.show();
            }
        });

        game3blockbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(Casual_mode_selectActivity.this,"아직 개방되지 않았습니다.\n개방 조건 : 5레벨 달성",Toast.LENGTH_SHORT);
                t.show();
            }
        });

        game6blockbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(Casual_mode_selectActivity.this,"아직 개방되지 않았습니다.\n개방 조건 : 10레벨 달성",Toast.LENGTH_SHORT);
                t.show();
            }
        });

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