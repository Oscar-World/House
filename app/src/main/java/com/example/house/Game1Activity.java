package com.example.house;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Random;

public class Game1Activity extends AppCompatActivity {

    String TAG = "게임 1";

    Random ran = new Random();
    Handler handler = new Handler();
    int card = ran.nextInt(2);
    int clicknum = 0;
    int level3Dlg = 0;
    int level5Dlg = 0;
    int level10Dlg = 0;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String input;
    String[] info;

    UserInfo user;

    Button resetbutton;
    ImageButton leftcardbtn;
    ImageButton rightcardbtn;
    ImageView leftdoublecard;
    ImageView leftbangcard;
    ImageView rightdoublecard;
    ImageView rightbangcard;
    ImageView leftcard;
    ImageView rightcard;
    Animation disappear;
    Animation appear;
    Animation boxappear;
    TextView countrytext;
    TextView nametext;
    TextView leveltext;
    TextView exptext;
    TextView moneytext;
    Button betbutton;
    EditText betText;
    int bet;
    TextView betchecktext;
    Animation drop;
    TextView getgoldText;
    TextView getexpText;

    ImageView korea;
    ImageView usa;
    ImageView egypt;
    ImageView vietnam;
    ImageView germany;
    ImageView mongolia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game1);
        Log.d(TAG, "onCreate() 호출됨");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() 호출됨");


        Log.d(TAG, "onStart: ");

        FrameLayout frameLayout = findViewById(R.id.game1_frameLayout);
        TextView frametext = findViewById(R.id.game1_frameText);
        Button frameclose = findViewById(R.id.game1_frameClose);
        countrytext = findViewById(R.id.game1_countrytext);
        nametext = findViewById(R.id.game1_nametext);
        leveltext = findViewById(R.id.game1_leveltext);
        exptext = findViewById(R.id.game1_expText);
        moneytext = findViewById(R.id.game1_moneytext);
        ImageView imageView = findViewById(R.id.game1_pictureview);
        leftcardbtn = findViewById(R.id.leftcardBtn);
        rightcardbtn = findViewById(R.id.rightcardBtn);
        leftdoublecard = findViewById(R.id.leftdoublecard);
        leftbangcard = findViewById(R.id.leftbangcard);
        rightdoublecard = findViewById(R.id.rightdoublecard);
        rightbangcard = findViewById(R.id.rightbangcard);
        resetbutton = findViewById(R.id.game1_resetBtn);
        Button backbutton = findViewById(R.id.game1_backbutton);
        disappear = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.carddisappear);
        appear = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cardappear);
        boxappear = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.boxappear);
        betbutton = findViewById(R.id.game1_bet_button);
        leftcard = findViewById(R.id.game1_leftcard);
        rightcard = findViewById(R.id.game1_rightcard);
        betchecktext = findViewById(R.id.game1_betText);
        drop = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.golddrop);
        getexpText = findViewById(R.id.game1_getexpText);
        getgoldText = findViewById(R.id.game1_getgoldText);

        korea = findViewById(R.id.game1_koreaImage);
        usa = findViewById(R.id.game1_usaImage);
        egypt = findViewById(R.id.game1_egyptImage);
        vietnam = findViewById(R.id.game1_vietnamImage);
        germany = findViewById(R.id.game1_germanyImage);
        mongolia = findViewById(R.id.game1_mongoliaImage);

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

        frametext.setText("【하프 게임】에 오신 것을 환영합니다.\r\n\r\n" +
                "이곳에는 두장의 카드가 있습니다.\r\n" +
                "[x2]   [x0]\r\n\r\n" +
                "카드에 적힌 연산 그대로\r\n" +
                "베팅액을 지급합니다.");


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


        betbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                betDlg();
            }
        });

        // 왼쪽 카드 버튼 클릭
            leftcardbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "왼쪽 버튼 클릭");
                    clicknum += 1;
                    Log.d(TAG, "버튼 클릭 횟수 : " + clicknum);

                    if (clicknum == 1) {
                        LeftThread leftThread = new LeftThread();
                        leftThread.start();

//                        editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp);
//                        leveltext.setText("Lv." + info[2]);
//                        exptext.setText("exp : " + info[3] + " / " + info[5]);
//                        moneytext.setText("골드 : " + info[4]);
//                        editor.commit();

                    }

                }

            });

            // 오른쪽 카드 버튼 클릭
        rightcardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "오른쪽 버튼 클릭");
                clicknum += 1;
                Log.d(TAG, "버튼 클릭 횟수 : " + clicknum);

                if (clicknum == 1) {
                    RightThread rightThread = new RightThread();
                    rightThread.start();


//                    leveltext.setText("Lv." + info[2]);
//                    exptext.setText("exp : " + info[3] + " / " + info[5]);
//                    moneytext.setText("골드 : " + info[4]);

                }

            }
        });

        // 다시하기 버튼
        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicknum = 0;
                resetbutton.setVisibility(View.INVISIBLE);

                if (card == 0) {
                    Log.d(TAG, "다시하기 랜덤 값 : 0 " + "card = " + card);
                    leftdoublecard.startAnimation(disappear);
                    rightbangcard.startAnimation(disappear);
                    leftdoublecard.setVisibility(View.INVISIBLE);
                    rightbangcard.setVisibility(View.INVISIBLE);
                }
                else {
                    Log.d(TAG, "다시하기 랜덤 값 : 1 " + "card = " + card);
                    leftbangcard.startAnimation(disappear);
                    rightdoublecard.startAnimation(disappear);
                    leftbangcard.setVisibility(View.INVISIBLE);
                    rightdoublecard.setVisibility(View.INVISIBLE);
                }

                leftcard.startAnimation(appear);
                rightcard.startAnimation(appear);
                leftcard.setVisibility(View.VISIBLE);
                rightcard.setVisibility(View.VISIBLE);
                betbutton.startAnimation(boxappear);
                betbutton.setVisibility(View.VISIBLE);

                card = ran.nextInt(2);

            }
        });

        // 뒤로가기 버튼
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 프레임레이아웃 닫기 버튼
        frameclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.INVISIBLE);
                betbutton.setVisibility(View.VISIBLE);
            }
        });

    }

    public void betDlg() {

        betText = new EditText(Game1Activity.this);
        betText.setInputType(0x00000002);

        AlertDialog.Builder betting = new AlertDialog.Builder(Game1Activity.this);
        betting.setTitle("베팅 금액을 입력하세요");
        betting.setView(betText);
        betting.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                bet = Integer.parseInt(betText.getText().toString());
                Log.d(TAG, "소지 금액 : " + user.money);
                Log.d(TAG, "입력받은 베팅금액 : " + bet);

                if (bet > 0) {

                    if (user.money - bet >= 0) {
                        leftcardbtn.setVisibility(View.VISIBLE);
                        rightcardbtn.setVisibility(View.VISIBLE);
                        leftcard.setVisibility(View.INVISIBLE);
                        rightcard.setVisibility(View.INVISIBLE);
                        betbutton.setVisibility(View.INVISIBLE);

                        user.money = user.money - bet;
                        moneytext.setText("골드 : " + Integer.toString(user.money));

                        betchecktext.setText("-" + bet + "골드");
                        betchecktext.startAnimation(drop);
                    }
                    else {
                        Toast t = Toast.makeText(Game1Activity.this,"보유한 골드보다 많이 베팅하실 수 없습니다.",Toast.LENGTH_SHORT);
                        t.show();
                    }

                }
                else {
                    Toast t = Toast.makeText(Game1Activity.this,"베팅 최소단위는 1골드 입니다.",Toast.LENGTH_SHORT);
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

    public void resetDlg() {
        AlertDialog.Builder reset = new AlertDialog.Builder(Game1Activity.this);
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
        AlertDialog.Builder level3 = new AlertDialog.Builder(Game1Activity.this);
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
        AlertDialog.Builder level5 = new AlertDialog.Builder(Game1Activity.this);
        level5.setTitle("5레벨 달성!");
        level5.setMessage("「주사위 게임」이 개방되었습니다.");
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
        AlertDialog.Builder level10 = new AlertDialog.Builder(Game1Activity.this);
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

    public class LeftThread extends Thread {
        public void run() {

            handler.post(new Runnable() {
                @Override
                public void run() {
                    leftcardbtn.startAnimation(disappear);
                    rightcardbtn.startAnimation(disappear);
                    leftcardbtn.setVisibility(View.INVISIBLE);
                    rightcardbtn.setVisibility(View.INVISIBLE);

                    if (card == 0) {
                        Log.d(TAG, "랜덤 값 : 0 " + "card = " + card);
                        leftdoublecard.startAnimation(appear);
                        rightbangcard.startAnimation(appear);
                        leftdoublecard.setVisibility(View.VISIBLE);
                        rightbangcard.setVisibility(View.VISIBLE);

                        user.money = user.money + bet*2;
                        user.getexp(15);


                        Log.d(TAG, " 게임 승리 ! " + "골드 : " + user.money + " 경험치 : " + user.exp + " 레벨 : " + user.level);

                        editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                        editor.commit();

                    } else {
                        Log.d(TAG, "랜덤 값 : 1 " + "card = " + card);
                        leftbangcard.startAnimation(appear);
                        rightdoublecard.startAnimation(appear);
                        leftbangcard.setVisibility(View.VISIBLE);
                        rightdoublecard.setVisibility(View.VISIBLE);

                        user.getexp(5);


                        Log.d(TAG, " 게임 패배 ! " + "골드 : " + user.money + " 경험치 : " + user.exp + " 레벨 : " + user.level);

                        editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                        editor.commit();

                    }

                }
            });
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {

                    if (card == 0) {
                        getgoldText.setText("+" + bet*2 + "골드");
                        getexpText.setText("+15exp");
                        getgoldText.startAnimation(drop);
                        getexpText.startAnimation(drop);
                    }
                    else {
                        getexpText.setText("+5exp");
                        getexpText.startAnimation(drop);
                    }

                    leveltext.setText("Lv." + user.level);
                    exptext.setText("exp : " + user.exp + " / " + user.fullexp);
                    moneytext.setText("골드 : " + user.money);

                }
            });

            try {
                Thread.sleep(1500);
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
                        resetbutton.startAnimation(boxappear);
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

    public class RightThread extends Thread {
        public void run() {

            handler.post(new Runnable() {
                @Override
                public void run() {

                    rightcardbtn.startAnimation(disappear);
                    leftcardbtn.startAnimation(disappear);
                    leftcardbtn.setVisibility(View.INVISIBLE);
                    rightcardbtn.setVisibility(View.INVISIBLE);

                    if (card == 0) {
                        Log.d(TAG, "랜덤 값 : 0 " + "card = " + card);
                        leftdoublecard.startAnimation(appear);
                        rightbangcard.startAnimation(appear);
                        leftdoublecard.setVisibility(View.VISIBLE);
                        rightbangcard.setVisibility(View.VISIBLE);

                        user.getexp(5);

                        Log.d(TAG, " 게임 패배 ! " + "골드 : " + user.money + " 경험치 : " + user.exp + " 레벨 : " + user.level);

                        editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                        editor.commit();

                    } else {
                        Log.d(TAG, "랜덤 값 : 1 " + "card = " + card);
                        leftbangcard.startAnimation(appear);
                        rightdoublecard.startAnimation(appear);
                        leftbangcard.setVisibility(View.VISIBLE);
                        rightdoublecard.setVisibility(View.VISIBLE);

                        user.money = user.money + bet*2;
                        user.getexp(15);

                        Log.d(TAG, " 게임 패배 ! " + "골드 : " + user.money + " 경험치 : " + user.exp + " 레벨 : " + user.level);

                        editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                        editor.commit();

                    }

                }
            });

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {

                    if (card == 0) {
                        getexpText.setText("+5exp");
                        getexpText.startAnimation(drop);
                    }
                    else {
                        getgoldText.setText("+" + bet*2 + "골드");
                        getexpText.setText("+15exp");
                        getgoldText.startAnimation(drop);
                        getexpText.startAnimation(drop);
                    }

                    leveltext.setText("Lv." + user.level);
                    exptext.setText("exp : " + user.exp + " / " + user.fullexp);
                    moneytext.setText("골드 : " + user.money);

                }
            });

            try {
                Thread.sleep(1500);
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
                        resetbutton.startAnimation(boxappear);
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