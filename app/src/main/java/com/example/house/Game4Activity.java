package com.example.house;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game4Activity extends AppCompatActivity {

    String TAG = "게임 4";
    Random ran = new Random();
    Handler handler = new Handler();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String input;
    String[] info;

    UserInfo user;
    int bet;
    EditText betText;

    Button betbutton;
    Button startbutton;
    Button backbutton;
    ImageView cat1;
    ImageView cat2;
    ImageView cat3;
    ImageView tiger1;
    ImageView tiger2;
    ImageView tiger3;
    ImageView whale1;
    ImageView whale2;
    ImageView whale3;
    Animation slotup1;
    Animation slotup2;
    Animation slotup3;
    Animation slotup4;
    Animation slotup5;
    Animation slotup6;
    Animation slotup7;
    Animation slotup8;
    Animation slotup9;
    TextView leveltext;
    TextView exptext;
    TextView moneytext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game4);
        Log.d(TAG, "onCreate() 호출됨");

        FrameLayout frameLayout = findViewById(R.id.game4_frameLayout);
        TextView frametext = findViewById(R.id.game4_frameText);
        Button frameclose = findViewById(R.id.game4_frameClose);
        TextView countrytext = findViewById(R.id.game4_countrytext);
        TextView nametext = findViewById(R.id.game4_nametext);
        leveltext = findViewById(R.id.game4_leveltext);
        exptext = findViewById(R.id.game4_expText);
        moneytext = findViewById(R.id.game4_moneytext);
        ImageView photo = findViewById(R.id.game4_pictureview);
        startbutton = findViewById(R.id.game4_start_button);
        backbutton = findViewById(R.id.game4_backbutton);
        cat1 = findViewById(R.id.game4_cat1);
        cat2 = findViewById(R.id.game4_cat2);
        cat3 = findViewById(R.id.game4_cat3);
        tiger1 = findViewById(R.id.game4_tiger1);
        tiger2 = findViewById(R.id.game4_tiger2);
        tiger3 = findViewById(R.id.game4_tiger3);
        whale1 = findViewById(R.id.game4_whale1);
        whale2 = findViewById(R.id.game4_whale2);
        whale3 = findViewById(R.id.game4_whale3);
        slotup1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slotup1);
        slotup2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slotup2);
        slotup3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slotup3);
        slotup4 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slotup4);
        slotup5 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slotup5);
        slotup6 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slotup6);
        slotup7 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slotup7);
        slotup8 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slotup8);
        slotup9 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slotup9);
        betbutton = findViewById(R.id.game4_bet_button);

        sharedPreferences = getSharedPreferences("사용자 정보",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        input = sharedPreferences.getString("정보","");

        Log.d(TAG, " input 사용자 정보: " + input);

        info = input.split(",");

        user = new UserInfo(Integer.parseInt(info[2]),Integer.parseInt(info[3]),Integer.parseInt(info[4]));

        user.fullexp = Integer.parseInt(info[5]);

        countrytext.setText(info[0]);
        nametext.setText(info[1]);
        leveltext.setText("Lv." + info[2]);
        exptext.setText("exp : " + info[3] + " / " + info[5]);
        moneytext.setText("골드 : " + info[4]);

        frametext.setText("【잭팟 게임】에 오신것을 환영합니다.\r\n\r\n" +
                "이곳에는 3마리의 동물이 있습니다.\r\n\r\n" +
                "3마리가 모두 일치하면 승리합니다.");

        betbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                betDlg();
            }
        });

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slot1();
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
                betbutton.setVisibility(View.VISIBLE);
            }
        });

    }

    public void betDlg() {

        betText = new EditText(Game4Activity.this);

        AlertDialog.Builder betting = new AlertDialog.Builder(Game4Activity.this);
        betting.setTitle("베팅 금액을 입력하세요");
        betting.setView(betText);
        betting.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                bet = Integer.parseInt(betText.getText().toString());
                Log.d(TAG, "소지 금액 : " + user.money);
                Log.d(TAG, "입력받은 베팅금액 : " + bet);

                if (bet > 0) {

                    if (user.money - bet >= 0) {

                        betbutton.setVisibility(View.INVISIBLE);
                        startbutton.setVisibility(View.VISIBLE);

                        user.money = user.money - bet;
                        moneytext.setText("골드 : " + Integer.toString(user.money));
                    }
                    else {
                        Toast t = Toast.makeText(Game4Activity.this,"보유한 골드보다 많이 베팅하실 수 없습니다.",Toast.LENGTH_SHORT);
                        t.show();
                    }

                }
                else {
                    Toast t = Toast.makeText(Game4Activity.this,"베팅 최소단위는 1골드 입니다.",Toast.LENGTH_SHORT);
                    t.show();
                }

            }
        });

        betting.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog betDlg = betting.create();
        betDlg.show();

    }

    public void slot1() {
        cat1.startAnimation(slotup1);
        tiger1.startAnimation(slotup2);
        whale1.startAnimation(slotup3);
        SlotThread1 thread1 = new SlotThread1();
        SlotThread2 thread2 = new SlotThread2();
        SlotThread3 thread3 = new SlotThread3();
        SlotThread4 thread4 = new SlotThread4();
        SlotThread5 thread5 = new SlotThread5();
        SlotThread6 thread6 = new SlotThread6();
        SlotThread7 thread7 = new SlotThread7();
        SlotThread8 thread8 = new SlotThread8();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
    }


    public class SlotThread1 extends Thread {
        public void run() {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int a = 1; a <= 2; a++) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        cat1.startAnimation(slotup4);
                        tiger1.startAnimation(slotup5);
                        whale1.startAnimation(slotup6);
                    }
                });
            }
        }
    }

    public class SlotThread2 extends Thread {
        public void run() {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    cat2.startAnimation(slotup1);
                    tiger2.startAnimation(slotup2);
                    whale2.startAnimation(slotup3);
                }
            });
        }
    }

    public class SlotThread3 extends Thread {
        public void run() {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int a = 1; a <= 50; a++) {
                try {
                    Thread.sleep(1050);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        cat1.startAnimation(slotup7);
                        tiger1.startAnimation(slotup8);
                        whale1.startAnimation(slotup9);
                    }
                });
            }
        }
    }

    public class SlotThread4 extends Thread {
        public void run() {

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    cat3.startAnimation(slotup1);
                    tiger3.startAnimation(slotup2);
                    whale3.startAnimation(slotup3);
                }
            });
        }
    }

    public class SlotThread5 extends Thread {
        public void run() {

            try {
                Thread.sleep(4500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int a = 1; a <= 2; a++) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        cat2.startAnimation(slotup4);
                        tiger2.startAnimation(slotup5);
                        whale2.startAnimation(slotup6);
                    }
                });
            }
        }
    }

    public class SlotThread6 extends Thread {
        public void run() {

            try {
                Thread.sleep(7950);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int a = 1; a <= 45; a++) {
                try {
                    Thread.sleep(1050);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        cat2.startAnimation(slotup7);
                        tiger2.startAnimation(slotup8);
                        whale2.startAnimation(slotup9);
                    }
                });
            }
        }
    }

    public class SlotThread7 extends Thread {
        public void run() {

            try {
                Thread.sleep(7500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int a = 1; a <= 2; a++) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        cat3.startAnimation(slotup4);
                        tiger3.startAnimation(slotup5);
                        whale3.startAnimation(slotup6);
                    }
                });
            }
        }
    }

    public class SlotThread8 extends Thread {
        public void run() {

            try {
                Thread.sleep(10950);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int a = 1; a <= 40; a++) {
                try {
                    Thread.sleep(1050);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        cat3.startAnimation(slotup7);
                        tiger3.startAnimation(slotup8);
                        whale3.startAnimation(slotup9);
                    }
                });
            }
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