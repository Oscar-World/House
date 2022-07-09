package com.example.house;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
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

import com.bumptech.glide.Glide;

import java.util.Random;

public class Game2Activity extends AppCompatActivity {

    String TAG = "게임 2";
    Random ran = new Random();
    int answer;
    int chance;
    int examAnswer;
    Handler handler = new Handler();
    TextView timeText;
    int time;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String input;
    String[] info;

    UserInfo user;

    Button betbutton;
    EditText betText;
    int bet;
    TextView leveltext;
    TextView exptext;
    TextView moneytext;
    EditText inputAnswer;
    Button startbutton;
    Button resetbutton;
    TimeThread thread;
    TextView numberText;
    TextView chanceText;
    TextView upText;
    TextView downText;
    TextView answerText;
    TextView gameoverText;
    Animation updownslide;
    Animation alpha;
    Animation gameoverslide;
    TextView betchecktext;
    Animation drop;
    TextView getgoldText;
    TextView getexpText;
    Animation appear;
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
        setContentView(R.layout.game2);
        Log.d(TAG, "onCreate() 호출됨");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() 호출됨");

        FrameLayout frameLayout = findViewById(R.id.game2_frameLayout);
        Button frameClose = findViewById(R.id.game2_frameClose);
        TextView frameText = findViewById(R.id.game2_frameText);
        TextView countrytext = findViewById(R.id.game2_countrytext);
        TextView nametext = findViewById(R.id.game2_nametext);
        leveltext = findViewById(R.id.game2_leveltext);
        exptext = findViewById(R.id.game2_expText);
        moneytext = findViewById(R.id.game2_moneytext);
        ImageView imageView = findViewById(R.id.game2_pictureview);
        startbutton = findViewById(R.id.game2_start_button);
        Button backbutton = findViewById(R.id.game2_backbutton);
        resetbutton = findViewById(R.id.game2_reset_button);
        inputAnswer = findViewById(R.id.game2_inputAnswer);
        upText = findViewById(R.id.game2_upText);
        downText = findViewById(R.id.game2_downText);
        answerText = findViewById(R.id.game2_answerText);
        gameoverText = findViewById(R.id.game2_gameOverText);
        numberText = findViewById(R.id.game2_numberText);
        chanceText = findViewById(R.id.game2_chanceText);
        timeText = findViewById(R.id.game2_timeText);
        updownslide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.updowntext);
        alpha = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        gameoverslide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.gameovertext);
        betbutton = findViewById(R.id.game2_bet_button);
        betchecktext = findViewById(R.id.game2_betText);
        drop = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.golddrop);
        appear = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.boxappear);
        getexpText = findViewById(R.id.game2_getexpText);
        getgoldText = findViewById(R.id.game2_getgoldText);

        korea = findViewById(R.id.game2_koreaImage);
        usa = findViewById(R.id.game2_usaImage);
        egypt = findViewById(R.id.game2_egyptImage);
        vietnam = findViewById(R.id.game2_vietnamImage);
        germany = findViewById(R.id.game2_germanyImage);
        mongolia = findViewById(R.id.game2_mongoliaImage);

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

        frameText.setText("【300 게임】에 오신 것을 환영합니다.\r\n\r\n" +
                "시스템이 0부터 300까지의 숫자 중,\r\n하나의 숫자를 지정합니다.\r\n\r\n" +
                "8번의 기회가 주어집니다.\r\n\r\n" +
                "정답을 입력하시면 승리합니다.");


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

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examAnswer = Integer.parseInt(inputAnswer.getText().toString());
                chance -= 1;
                time = 11;
                Log.d(TAG, "chance : " + chance);
                Log.d(TAG, "입력한 값 : " + examAnswer);
                chanceText.setText("남은 기회 : " + chance + "번");

                if (chance > 0) {

//                    if (examAnswer != Integer.parseInt(inputAnswer.getText().toString())) {
//                        //정수가 아닐 경우 예외처리 필요
//                       Toast t = Toast.makeText(Game2Activity.this,"숫자를 입력하세요",Toast.LENGTH_SHORT);
//                       t.show();
//                    }

                    if (answer > examAnswer) {
                        upText.startAnimation(updownslide);
                    } else if (answer < examAnswer) {
                        downText.startAnimation(updownslide);
                    } else if (answer == examAnswer) {
                        time = 0;
                        chance = 0;
                        chanceText.setText("남은 기회 : " + chance + "번");
                        timeText.setText("남은 시간 : " + time + "초");

                    }
                }
                else if (chance == 0) {
                    time = 0;

                }

            }
        });

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answerText.setVisibility(View.INVISIBLE);
                gameoverText.setVisibility(View.INVISIBLE);
                numberText.setVisibility(View.INVISIBLE);
                resetbutton.setVisibility(View.INVISIBLE);
                betbutton.setVisibility(View.VISIBLE);
                chance = 8;
                time = 10;
                inputAnswer.setText(null);
                chanceText.setText("남은 기회 : " + chance + "번");
                timeText.setText("남은 시간 : " + time + "초");
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        frameClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.INVISIBLE);
                betbutton.setVisibility(View.VISIBLE);

            }
        });

    }

    public void betDlg() {

        betText = new EditText(Game2Activity.this);
        betText.setInputType(0x00000002);

        AlertDialog.Builder betting = new AlertDialog.Builder(Game2Activity.this);
        betting.setTitle("베팅 금액을 입력하세요");
        betting.setView(betText);
        betting.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                bet = Integer.parseInt(betText.getText().toString());
                Log.d(TAG, "소지 금액 : " + user.money);
                Log.d(TAG, "입력받은 베팅금액 : " + bet);

                if (bet > 0) {

                    if (user.money - bet >= 0) {
                        inputAnswer.setVisibility(View.VISIBLE);
                        startbutton.setVisibility(View.VISIBLE);
                        betbutton.setVisibility(View.INVISIBLE);

                        user.money = user.money - bet;
                        moneytext.setText("골드 : " + Integer.toString(user.money));
                        betchecktext.setText("-" + bet + "골드");
                        betchecktext.startAnimation(drop);

                        chance = 8;
                        chanceText.setText("남은 기회 : " + chance + "번");
                        answer = ran.nextInt(301);
                        Log.d(TAG, "이번 게임 정답 : " + answer);

                        thread = new TimeThread();
                        thread.start();

                        // 확인 버튼 클릭시, 스레드 종료 로직 추가
                    }
                    else {
                        Toast t = Toast.makeText(Game2Activity.this,"보유한 골드보다 많이 베팅하실 수 없습니다.",Toast.LENGTH_SHORT);
                        t.show();
                    }

                }
                else {
                    Toast t = Toast.makeText(Game2Activity.this,"베팅 최소단위는 1골드 입니다.",Toast.LENGTH_SHORT);
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
        AlertDialog.Builder reset = new AlertDialog.Builder(Game2Activity.this);
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

    public void level5Dlg() {
        AlertDialog.Builder level5 = new AlertDialog.Builder(Game2Activity.this);
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
        AlertDialog.Builder level10 = new AlertDialog.Builder(Game2Activity.this);
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


    public class TimeThread extends Thread {

        public void run() {
            time = 10;

            while (chance > 0) {
                while (time > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    time -= 1;
                    Log.d(TAG, "타이머 진행중 : " + time);

                    if (time == 0) {
                        if (chance > 1) {
                            chance = chance - 1;
                            time = 10;
                            Log.d(TAG, "남은 기회 : " + chance);
                        }
                        else if (chance == 1) {
                            chance = chance - 1;
                            Log.d(TAG, "남은 기회 : " + chance);
                            time =0;
                        }

                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            timeText.setText("남은 시간 : " + time + "초");
                            chanceText.setText("남은 기회 : " + chance + "번");

                        }
                    });
                }

            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (chance == 0) {
                        time = 0;
                        timeText.setText("남은 시간 : " + time + "초");
                        if (examAnswer == answer) {

                            answerText.setVisibility(View.VISIBLE);
                            answerText.startAnimation(gameoverslide);
                            numberText.setText("정답은 " + answer + " 입니다.");
                            numberText.setVisibility(View.VISIBLE);
                            numberText.startAnimation(alpha);

                            user.money = user.money + bet*3;
                            user.getexp(35);

                            getgoldText.setText("+" + bet*3 + "골드");
                            getexpText.setText("+35exp");
                            getgoldText.startAnimation(drop);
                            getexpText.startAnimation(drop);

                            editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                            editor.commit();

                            leveltext.setText("Lv." + user.level);
                            exptext.setText("exp : " + user.exp + " / " + user.fullexp);
                            moneytext.setText("골드 : " + user.money);

                        }
                        else {
                            gameoverText.setVisibility(View.VISIBLE);
                            gameoverText.startAnimation(gameoverslide);
                            numberText.setText("정답은 " + answer + " 입니다.");
                            numberText.setVisibility(View.VISIBLE);
                            numberText.startAnimation(alpha);

                            user.getexp(10);
                            getexpText.setText("+10exp");
                            getexpText.startAnimation(drop);

                            editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                            editor.commit();

                            leveltext.setText("Lv." + user.level);
                            exptext.setText("exp : " + user.exp + " / " + user.fullexp);
                            moneytext.setText("골드 : " + user.money);

                        }
                        startbutton.setVisibility(View.INVISIBLE);
                    }
                }
            });



            try {
                Thread.sleep(2500);
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
                        resetbutton.startAnimation(appear);
                        resetbutton.setVisibility(View.VISIBLE);

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