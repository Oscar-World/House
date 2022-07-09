package com.example.house;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Random;

public class RandomBoxActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String input;
    String[] info;
    UserInfo user;

    String TAG = "게임 5";
    Random ran = new Random();
    ImageView randomBox;
    Animation shake;
    Animation boxDisappear;
    Animation boxAppear;
    Handler handler = new Handler();
    Button resetbutton;
    TextView getExp;
    TextView gold;
    ImageView bang;
    TextView leveltext;
    TextView exptext;
    TextView moneytext;
    int box;
    TextView betchecktext;
    Animation drop;
    int level3Dlg = 0;
    int level5Dlg = 0;
    int level10Dlg = 0;

    ImageView korea;
    ImageView usa;
    ImageView egypt;
    ImageView vietnam;
    ImageView germany;
    ImageView mongolia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.randombox);
        Log.d(TAG, "onCreate() 호출됨");


        FrameLayout frameLayout = findViewById(R.id.randomBox_frameLayout);
        TextView frametext = findViewById(R.id.randomBox_frameText);
        Button frameclose = findViewById(R.id.randomBox_frameClose);
        TextView countrytext = findViewById(R.id.randomBox_countrytext);
        TextView nametext = findViewById(R.id.randomBox_nametext);
        leveltext = findViewById(R.id.randomBox_leveltext);
        exptext = findViewById(R.id.randomBox_expText);
        moneytext = findViewById(R.id.randomBox_moneytext);
        ImageView imageView = findViewById(R.id.randomBox_pictureview);
        Button startbutton = findViewById(R.id.randomBox_start_button);
        Button backbutton = findViewById(R.id.randomBox_backbutton);
        resetbutton = findViewById(R.id.randomBox_reset_button);
        getExp = findViewById(R.id.randomBox_getExpText);
        gold = findViewById(R.id.randomBox_goldText);
        randomBox = findViewById(R.id.randomBox_randomBox);
        shake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.randombox);
        boxDisappear = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.boxscale);
        boxAppear = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.boxappear);
        bang = findViewById(R.id.randomBox_bang);
        betchecktext = findViewById(R.id.randomBox_betText);
        drop = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.golddrop);

        korea = findViewById(R.id.randomBox_koreaImage);
        usa = findViewById(R.id.randomBox_usaImage);
        egypt = findViewById(R.id.randomBox_egyptImage);
        vietnam = findViewById(R.id.randomBox_vietnamImage);
        germany = findViewById(R.id.randomBox_germanyImage);
        mongolia = findViewById(R.id.randomBox_mongoliaImage);

        sharedPreferences = getSharedPreferences("사용자 정보",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        input = sharedPreferences.getString("정보","");

        Log.d(TAG, " input 사용자 정보: " + input);

        info = input.split(",");

        user = new UserInfo(Integer.parseInt(info[2]),Integer.parseInt(info[3]),Integer.parseInt(info[4]));
        user.fullexp = Integer.parseInt(info[5]);

        countrytext.setText("       " + info[0]);
        nametext.setText(info[1]);
        leveltext.setText("Lv." + info[2]);
        exptext.setText("exp : " + info[3] + " / " + info[5]);
        moneytext.setText("골드 : " + info[4]);

        Glide.with(getApplicationContext())
                .load(Uri.parse(info[6]))
                .into(imageView);

        frametext.setText("【랜덤 박스 상점】방문을 환영합니다.\r\n\r\n" +
                "랜덤 박스는 아래의 상품을 포함합니다.\r\n" +
                "[골드]  [경험치]  [꽝]\r\n\r\n" +
                "랜덤 박스의 가격은 「50골드」 입니다.");


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


        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user.money >= 50) {

                    BoxshakeThread shakethread = new BoxshakeThread();
                    BoxdisappearThread disappearthread = new BoxdisappearThread();
                    shakethread.start();
                    disappearthread.start();
                    startbutton.setVisibility(View.INVISIBLE);

                    user.money = user.money - 50;
                    moneytext.setText("골드 : " + user.money);
                    betchecktext.setText("-50골드");
                    betchecktext.startAnimation(drop);

                }
                else {
                    Toast t = Toast.makeText(RandomBoxActivity.this,"소지한 골드가 부족합니다.",Toast.LENGTH_SHORT);
                    t.show();
                }

            }
        });

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetbutton.setVisibility(View.INVISIBLE);
                startbutton.setVisibility(View.VISIBLE);
                gold.setVisibility(View.INVISIBLE);
                getExp.setVisibility(View.INVISIBLE);
                bang.setVisibility(View.INVISIBLE);
                randomBox.startAnimation(boxAppear);
                randomBox.setVisibility(View.VISIBLE);
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        frameclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.INVISIBLE);
                startbutton.setVisibility(View.VISIBLE);
            }
        });

    } // onCreate

    public void resetDlg() {
        AlertDialog.Builder reset = new AlertDialog.Builder(RandomBoxActivity.this);
        reset.setTitle(info[1] + "님은 파산되었습니다.");
        reset.setMessage("게임이 초기화됩니다.");
        reset.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog resetDlg = reset.create();
        resetDlg.show();
    }

    public void level3Dlg() {
        AlertDialog.Builder level3 = new AlertDialog.Builder(RandomBoxActivity.this);
        level3.setTitle("3레벨 달성!");
        level3.setMessage("「300 게임」이 개방되었습니다.");
        level3.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                level3Dlg += 1;
                Log.d(TAG, "level3Dlg : " + level3Dlg);
            }
        });

        AlertDialog level3Dlg = level3.create();
        level3Dlg.show();
    }

    public void level5Dlg() {
        AlertDialog.Builder level5 = new AlertDialog.Builder(RandomBoxActivity.this);
        level5.setTitle("5레벨 달성!");
        level5.setMessage("「주사위 게임」이 개방되었습니다.\n" +
                "「사용자 설정 게임」이 개방되었습니다.");
        level5.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                level5Dlg += 1;
                Log.d(TAG, "level5Dlg : " + level5Dlg);
            }
        });

        AlertDialog level5Dlg = level5.create();
        level5Dlg.show();
    }

    public void level10Dlg() {
        AlertDialog.Builder level10 = new AlertDialog.Builder(RandomBoxActivity.this);
        level10.setTitle("10레벨 달성!");
        level10.setMessage("「제이든 잡기 게임」이 개방되었습니다.");
        level10.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                level10Dlg += 1;
                Log.d(TAG, "level10Dlg : " + level10Dlg);
            }
        });

        AlertDialog level10Dlg = level10.create();
        level10Dlg.show();
    }

    public class BoxshakeThread extends Thread {
        int a;
        public void run() {

        for(a = 0;a< 3;a++) {
            try {
                Thread.sleep(450);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "반복 : " + a);
                    randomBox.startAnimation(shake);

                }
            });
        }
    }

    }

    public class BoxdisappearThread extends Thread {

        public void run() {
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    randomBox.startAnimation(boxDisappear);
                    randomBox.setVisibility(View.INVISIBLE);
                }
            });

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    box = ran.nextInt(3);
                    if (box == 0) {
                        int randomGold = ran.nextInt(150);
                        gold.setText("+ " + randomGold + "골드");
                        gold.startAnimation(boxAppear);
                        gold.setVisibility(View.VISIBLE);

                        user.money = user.money + randomGold;
                        Log.d(TAG, "획득 : " + randomGold + "골드");

                        editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                        editor.commit();

                        leveltext.setText("Lv." + user.level);
                        exptext.setText("exp : " + user.exp + " / " + user.fullexp);
                        moneytext.setText("골드 : " + user.money);

                    }
                    else if (box == 1) {
                        int randomExp = ran.nextInt(80);
                        getExp.setText("+ " + randomExp + "exp");
                        getExp.startAnimation(boxAppear);
                        getExp.setVisibility(View.VISIBLE);

                        user.getexp(randomExp);
                        Log.d(TAG, "획득 : " + randomExp + "exp");

                        editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                        editor.commit();

                        leveltext.setText("Lv." + user.level);
                        exptext.setText("exp : " + user.exp + " / " + user.fullexp);
                        moneytext.setText("골드 : " + user.money);

                    }
                    else if (box == 2) {
                        bang.startAnimation(boxAppear);
                        bang.setVisibility(View.VISIBLE);

                        Log.d(TAG, "획득 : 꽝");

                        editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                        editor.commit();

                        leveltext.setText("Lv." + user.level);
                        exptext.setText("exp : " + user.exp + " / " + user.fullexp);
                        moneytext.setText("골드 : " + user.money);

                    }
                }
            });

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {

                    if (user.money <= 0) {
                        user.reset();
                        editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                        editor.commit();

                        resetDlg();
                    }
                    else {
                        resetbutton.startAnimation(boxAppear);
                        resetbutton.setVisibility(View.VISIBLE);

                        if (user.level == 3) {
                            if (level3Dlg == 0) {
                                level3Dlg();
                            }
                        }

                        if (user.level == 5) {
                            if (level5Dlg == 0) {
                                level5Dlg();
                            }

                        }

                        if (user.level == 10) {
                            if (level10Dlg == 0) {
                                level10Dlg();
                            }
                        }

                    }

                }
            });
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() 호출됨");
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