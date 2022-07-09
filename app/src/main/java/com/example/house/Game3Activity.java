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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Random;

public class Game3Activity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String input;
    String[] info;
    UserInfo user;
    int bet;

    String TAG = "게임 3";
    Random ran = new Random();
    Handler handler = new Handler();

    Button resetbutton;
    Button startbutton;
    Button betbutton;
    Animation diceroll1;
    Animation diceroll2;
    Animation diceroll3;
    Animation diceappear;
    ImageView numberDiceAnim;
    ImageView pmDice;
    ImageView oneDice;
    ImageView twoDice;
    ImageView threeDice;
    ImageView fourDice;
    ImageView fiveDice;
    ImageView sixDice;
    ImageView plusDice;
    ImageView mynusDice;
    Animation dicereset;
    EditText betText;
    TextView leveltext;
    TextView exptext;
    TextView moneytext;
    TextView betchecktext;
    Animation drop;
    TextView getgoldText;
    TextView getexpText;
    int numberDice;
    int signDice;
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
        setContentView(R.layout.game3);
        Log.d(TAG, "onCreate() 호출됨");


        FrameLayout frameLayout = findViewById(R.id.game3_frameLayout);
        TextView frametext = findViewById(R.id.game3_frameText);
        Button frameclose = findViewById(R.id.game3_frameClose);
        TextView countrytext = findViewById(R.id.game3_countrytext);
        TextView nametext = findViewById(R.id.game3_nametext);
        leveltext = findViewById(R.id.game3_leveltext);
        exptext = findViewById(R.id.game3_expText);
        moneytext = findViewById(R.id.game3_moneytext);
        ImageView imageView = findViewById(R.id.game3_pictureview);
        numberDiceAnim = findViewById(R.id.game3_numberdice);
        pmDice = findViewById(R.id.game3_pmdice);
        oneDice = findViewById(R.id.game3_diceOne);
        twoDice = findViewById(R.id.game3_diceTwo);
        threeDice = findViewById(R.id.game3_diceThree);
        fourDice = findViewById(R.id.game3_diceFour);
        fiveDice = findViewById(R.id.game3_diceFive);
        sixDice = findViewById(R.id.game3_diceSix);
        plusDice = findViewById(R.id.game3_dicePlus);
        mynusDice = findViewById(R.id.game3_diceMynus);
        startbutton = findViewById(R.id.game3_start_button);
        betbutton = findViewById(R.id.game3_bet_button);
        Button backbutton = findViewById(R.id.game3_backbutton);
        resetbutton = findViewById(R.id.game3_reset_button);
        diceroll1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.diceroll1);
        diceroll2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.diceroll2);
        diceroll3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.diceroll3);
        diceappear = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.diceappear);
        dicereset = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.boxappear);
        betchecktext = findViewById(R.id.game3_betText);
        drop = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.golddrop);
        getexpText = findViewById(R.id.game3_getexpText);
        getgoldText = findViewById(R.id.game3_getgoldText);

        korea = findViewById(R.id.game3_koreaImage);
        usa = findViewById(R.id.game3_usaImage);
        egypt = findViewById(R.id.game3_egyptImage);
        vietnam = findViewById(R.id.game3_vietnamImage);
        germany = findViewById(R.id.game3_germanyImage);
        mongolia = findViewById(R.id.game3_mongoliaImage);

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

        frametext.setText("【주사위 게임】에 오신것을 환영합니다.\r\n\r\n" +
                "이곳에는 두개의 주사위가 있습니다\r\n" +
                "[숫자 주사위]   [± 주사위]\r\n\r\n" +
                "베팅액에 [숫자 주사위]의 값을 곱하고,\r\n" +
                "[± 주사위]를 적용하여 골드를 지급합니다.");


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
                DiceThread thread = new DiceThread();
                thread.start();

            }
        });

        betbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                betDlg();
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ResetThread resetThread = new ResetThread();
                resetThread.start();

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

        betText = new EditText(Game3Activity.this);
        betText.setInputType(0x00000002);

        AlertDialog.Builder betting = new AlertDialog.Builder(Game3Activity.this);
        betting.setTitle("베팅 금액을 입력하세요");
        betting.setView(betText);

        betting.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                bet = Integer.parseInt(betText.getText().toString());
                Log.d(TAG, "소지 금액 : " + user.money);
                Log.d(TAG, "입력받은 베팅금액 : " + bet);

                if (bet > 0) {

                    if (user.money - bet >= 0) {

                        if (user.money/6 < bet) {
                            Toast t = Toast.makeText(Game3Activity.this,"게임 특성 상, (보유골드 ÷ 6) 보다 많이 베팅하실 수 없습니다.",Toast.LENGTH_LONG);
                            t.show();
                        }

                        else{
                            startbutton.setVisibility(View.VISIBLE);
                            betbutton.setVisibility(View.INVISIBLE);

                            user.money = user.money - bet;
                            moneytext.setText("골드 : " + Integer.toString(user.money));
                            betchecktext.setText("-" + bet + "골드");
                            betchecktext.startAnimation(drop);
                        }
                    }
                    else {
                        Toast t = Toast.makeText(Game3Activity.this,"보유한 골드보다 많이 베팅하실 수 없습니다.",Toast.LENGTH_SHORT);
                        t.show();
                    }

                }
                else {
                    Toast t = Toast.makeText(Game3Activity.this,"베팅 최소단위는 1골드 입니다.",Toast.LENGTH_SHORT);
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
        AlertDialog.Builder reset = new AlertDialog.Builder(Game3Activity.this);
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

    public void level10Dlg() {
        AlertDialog.Builder level10 = new AlertDialog.Builder(Game3Activity.this);
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

    public class DiceThread extends Thread {
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {

                    startbutton.setVisibility(View.INVISIBLE);

                    // 주사위 굴리는 속도 경우의 수
                    int roll = ran.nextInt(9);
                    if (roll == 0) {
                        numberDiceAnim.startAnimation(diceroll1);
                        pmDice.startAnimation(diceroll1);
                    }
                    else if (roll == 1) {
                        numberDiceAnim.startAnimation(diceroll1);
                        pmDice.startAnimation(diceroll2);
                    }
                    else if (roll == 2) {
                        numberDiceAnim.startAnimation(diceroll1);
                        pmDice.startAnimation(diceroll3);
                    }
                    else if (roll == 3) {
                        numberDiceAnim.startAnimation(diceroll2);
                        pmDice.startAnimation(diceroll1);
                    }
                    else if (roll == 4) {
                        numberDiceAnim.startAnimation(diceroll2);
                        pmDice.startAnimation(diceroll2);
                    }
                    else if (roll == 5) {
                        numberDiceAnim.startAnimation(diceroll2);
                        pmDice.startAnimation(diceroll3);
                    }
                    else if (roll == 6) {
                        numberDiceAnim.startAnimation(diceroll3);
                        pmDice.startAnimation(diceroll1);
                    }
                    else if (roll == 7) {
                        numberDiceAnim.startAnimation(diceroll3);
                        pmDice.startAnimation(diceroll2);
                    }
                    else if (roll == 8) {
                        numberDiceAnim.startAnimation(diceroll3);
                        pmDice.startAnimation(diceroll3);
                    }

                    numberDiceAnim.setVisibility(View.INVISIBLE);
                    pmDice.setVisibility(View.INVISIBLE);

                    // 여기부터 주사위 경우의 수
                    numberDice = ran.nextInt(6);
                    signDice = ran.nextInt(2);

                    if (signDice == 0) { // + 주사위 6개의 경우의 수
                        plusDice.startAnimation(diceappear);
                        plusDice.setVisibility(View.VISIBLE);

                        if (numberDice == 0) {
                            oneDice.startAnimation(diceappear);
                            oneDice.setVisibility(View.VISIBLE);

                            user.money = user.money + bet;
                            user.getexp(30);
                            Log.d(TAG, "주사위 게임 결과 : " + bet + "골드 획득");
                        }
                        else if (numberDice == 1) {
                            twoDice.startAnimation(diceappear);
                            twoDice.setVisibility(View.VISIBLE);

                            user.money = user.money + bet*2;
                            user.getexp(50);
                            Log.d(TAG, "주사위 게임 결과 : " + bet*2 + "골드 획득");

                        }
                        else if (numberDice == 2) {
                            threeDice.startAnimation(diceappear);
                            threeDice.setVisibility(View.VISIBLE);

                            user.money = user.money + bet*3;
                            user.getexp(70);
                            Log.d(TAG, "주사위 게임 결과 : " + bet*3 + "골드 획득");
                        }
                        else if (numberDice == 3) {
                            fourDice.startAnimation(diceappear);
                            fourDice.setVisibility(View.VISIBLE);

                            user.money = user.money + bet*4;
                            user.getexp(90);
                            Log.d(TAG, "주사위 게임 결과 : " + bet*4 + "골드 획득");

                        }
                        else if (numberDice == 4) {
                            fiveDice.startAnimation(diceappear);
                            fiveDice.setVisibility(View.VISIBLE);

                            user.money = user.money + bet*5;
                            user.getexp(110);
                            Log.d(TAG, "주사위 게임 결과 : " + bet*5 + "골드 획득");

                        }
                        else if (numberDice == 5) {
                            sixDice.startAnimation(diceappear);
                            sixDice.setVisibility(View.VISIBLE);

                            user.money = user.money + bet*6;
                            user.getexp(130);
                            Log.d(TAG, "주사위 게임 결과 : " + bet*6 + "골드 획득");

                        }

                    }
                    else if (signDice == 1) { // - 주사위 6개의 경우의 수
                        mynusDice.startAnimation(diceappear);
                        mynusDice.setVisibility(View.VISIBLE);

                        if (numberDice == 0) {
                            oneDice.startAnimation(diceappear);
                            oneDice.setVisibility(View.VISIBLE);

                            user.getexp(10);
                            Log.d(TAG, "주사위 게임 결과 : 기본 베팅액만 차감");

                        }
                        else if (numberDice == 1) {
                            twoDice.startAnimation(diceappear);
                            twoDice.setVisibility(View.VISIBLE);

                            user.money = user.money - bet;
                            user.getexp(10);
                            Log.d(TAG, "주사위 게임 결과 : " + bet + "골드 차감");

                        }
                        else if (numberDice == 2) {
                            threeDice.startAnimation(diceappear);
                            threeDice.setVisibility(View.VISIBLE);

                            user.money = user.money - bet*2;
                            user.getexp(10);
                            Log.d(TAG, "주사위 게임 결과 : " + bet*2 + "골드 차감");

                        }
                        else if (numberDice == 3) {
                            fourDice.startAnimation(diceappear);
                            fourDice.setVisibility(View.VISIBLE);

                            user.money = user.money - bet*3;
                            user.getexp(10);
                            Log.d(TAG, "주사위 게임 결과 : " + bet*3 + "골드 차감");

                        }
                        else if (numberDice == 4) {
                            fiveDice.startAnimation(diceappear);
                            fiveDice.setVisibility(View.VISIBLE);

                            user.money = user.money - bet*4;
                            user.getexp(10);
                            Log.d(TAG, "주사위 게임 결과 : " + bet*4 + "골드 차감");

                        }
                        else if (numberDice == 5) {
                            sixDice.startAnimation(diceappear);
                            sixDice.setVisibility(View.VISIBLE);

                            user.money = user.money - bet*5;
                            user.getexp(10);
                            Log.d(TAG, "주사위 게임 결과 : " + bet*5 + "골드 차감");

                        }
                    }

                    editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                    editor.commit();


                }
            });

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.post(new Runnable() {
                @Override
                public void run() {

                    if (signDice == 0) { // + 주사위 6개의 경우의 수

                        if (numberDice == 0) {

                            getgoldText.setText("+" + bet + "골드");
                            getexpText.setText("+30exp");
                            getgoldText.startAnimation(drop);
                            getexpText.startAnimation(drop);
                        }
                        else if (numberDice == 1) {
                            getgoldText.setText("+" + bet*2 + "골드");
                            getexpText.setText("+50exp");
                            getgoldText.startAnimation(drop);
                            getexpText.startAnimation(drop);
                        }
                        else if (numberDice == 2) {
                            getgoldText.setText("+" + bet*3 + "골드");
                            getexpText.setText("+70exp");
                            getgoldText.startAnimation(drop);
                            getexpText.startAnimation(drop);
                        }
                        else if (numberDice == 3) {
                            getgoldText.setText("+" + bet*4 + "골드");
                            getexpText.setText("+90exp");
                            getgoldText.startAnimation(drop);
                            getexpText.startAnimation(drop);
                        }
                        else if (numberDice == 4) {
                            getgoldText.setText("+" + bet*5 + "골드");
                            getexpText.setText("+110exp");
                            getgoldText.startAnimation(drop);
                            getexpText.startAnimation(drop);
                        }
                        else if (numberDice == 5) {
                            getgoldText.setText("+" + bet*6 + "골드");
                            getexpText.setText("+130exp");
                            getgoldText.startAnimation(drop);
                            getexpText.startAnimation(drop);
                        }

                    }
                    else if (signDice == 1) { // - 주사위 6개의 경우의 수

                        if (numberDice == 0) {

                            getexpText.setText("+10exp");
                            getexpText.startAnimation(drop);
                        }
                        else if (numberDice == 1) {
                            getgoldText.setText("-" + bet + "골드");
                            getgoldText.startAnimation(drop);
                            getexpText.setText("+10exp");
                            getexpText.startAnimation(drop);
                        }
                        else if (numberDice == 2) {
                            getgoldText.setText("-" + bet*2 + "골드");
                            getgoldText.startAnimation(drop);
                            getexpText.setText("+10exp");
                            getexpText.startAnimation(drop);
                        }
                        else if (numberDice == 3) {
                            getgoldText.setText("-" + bet*3 + "골드");
                            getgoldText.startAnimation(drop);
                            getexpText.setText("+10exp");
                            getexpText.startAnimation(drop);
                        }
                        else if (numberDice == 4) {
                            getgoldText.setText("-" + bet*4 + "골드");
                            getgoldText.startAnimation(drop);
                            getexpText.setText("+10exp");
                            getexpText.startAnimation(drop);
                        }
                        else if (numberDice == 5) {
                            getgoldText.setText("-" + bet*5 + "골드");
                            getgoldText.startAnimation(drop);
                            getexpText.setText("+10exp");
                            getexpText.startAnimation(drop);
                        }
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
                        resetbutton.startAnimation(dicereset);
                        resetbutton.setVisibility(View.VISIBLE);

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

    public class ResetThread extends Thread{
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {

                    oneDice.setVisibility(View.INVISIBLE);
                    twoDice.setVisibility(View.INVISIBLE);
                    threeDice.setVisibility(View.INVISIBLE);
                    fourDice.setVisibility(View.INVISIBLE);
                    fiveDice.setVisibility(View.INVISIBLE);
                    sixDice.setVisibility(View.INVISIBLE);
                    plusDice.setVisibility(View.INVISIBLE);
                    mynusDice.setVisibility(View.INVISIBLE);

                    resetbutton.setVisibility(View.INVISIBLE);

                    numberDiceAnim.startAnimation(dicereset);
                    pmDice.startAnimation(dicereset);
                    numberDiceAnim.setVisibility(View.VISIBLE);
                    pmDice.setVisibility(View.VISIBLE);

                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    betbutton.setVisibility(View.VISIBLE);
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