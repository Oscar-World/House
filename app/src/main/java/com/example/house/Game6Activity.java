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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.Random;

public class Game6Activity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String input;
    String[] info;
    UserInfo user;

    EditText betText;
    Button betbutton;
    int bet;
    int clicknum = 0;

    String TAG = "게임 6";
    Random ran = new Random();
    Handler handler = new Handler();
    ImageView villiain1;
    ImageView villiain2;
    ImageView villiain3;
    ImageView villiain4;
    Animation alpha;
    Button startbutton;
    Button resetbutton;
    TextView checkText;
    ImageView house1;
    ImageView house2;
    ImageView house3;
    ImageView house4;
    ImageButton housebtn1;
    ImageButton housebtn2;
    ImageButton housebtn3;
    ImageButton housebtn4;
    TextView getgoldText;
    TextView getexpText;
    TextView answerText;
    TextView bangText;
    Animation appear;
    Animation drop;
    TextView leveltext;
    TextView exptext;
    TextView moneytext;
    TextView betchecktext;

    ImageView korea;
    ImageView usa;
    ImageView egypt;
    ImageView vietnam;
    ImageView germany;
    ImageView mongolia;

    int miro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game6);
        Log.d(TAG, "onCreate() 호출됨");

        FrameLayout frameLayout = findViewById(R.id.game6_frameLayout);
        TextView frametext = findViewById(R.id.game6_frameText);
        Button frameclose = findViewById(R.id.game6_frameClose);
        TextView countrytext = findViewById(R.id.game6_countrytext);
        TextView nametext = findViewById(R.id.game6_nametext);
        leveltext = findViewById(R.id.game6_leveltext);
        exptext = findViewById(R.id.game6_expText);
        moneytext = findViewById(R.id.game6_moneytext);
        checkText = findViewById(R.id.game6_checkText);
        ImageView imageView = findViewById(R.id.game6_pictureview);
        startbutton = findViewById(R.id.game6_start_button);
        resetbutton = findViewById(R.id.game6_reset_button);
        Button backbutton = findViewById(R.id.game6_backbutton);
        house1 = findViewById(R.id.game6_house1);
        house2 = findViewById(R.id.game6_house2);
        house3 = findViewById(R.id.game6_house3);
        house4 = findViewById(R.id.game6_house4);
        housebtn1 = findViewById(R.id.game6_house1btn);
        housebtn2 = findViewById(R.id.game6_house2btn);
        housebtn3 = findViewById(R.id.game6_house3btn);
        housebtn4 = findViewById(R.id.game6_house4btn);
        villiain1 = findViewById(R.id.game6_villain1);
        villiain2 = findViewById(R.id.game6_villain2);
        villiain3 = findViewById(R.id.game6_villain3);
        villiain4 = findViewById(R.id.game6_villain4);
        alpha = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.villainalpha);
        getexpText = findViewById(R.id.game6_getexpText);
        getgoldText = findViewById(R.id.game6_getgoldText);
        answerText = findViewById(R.id.game6_answerText);
        bangText = findViewById(R.id.game6_bangText);
        appear = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.boxappear);
        drop = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.golddrop);
        betbutton = findViewById(R.id.game6_bet_button);
        betchecktext = findViewById(R.id.game6_betText);

        korea = findViewById(R.id.game6_koreaImage);
        usa = findViewById(R.id.game6_usaImage);
        egypt = findViewById(R.id.game6_egyptImage);
        vietnam = findViewById(R.id.game6_vietnamImage);
        germany = findViewById(R.id.game6_germanyImage);
        mongolia = findViewById(R.id.game6_mongoliaImage);

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

        frametext.setText("【제이든 잡기】에 오신것을 환영합니다.\r\n\r\n" +
                "무자비한 악당 제이든..!\r\n" +
                "잠시 후, 그가 이곳에 출현한다고 합니다.\r\n\r\n" +
                "4개의 집 中, 의심가는 집을 골라주세요.\r\n\r\n" +
                "[제이든은 숨을 집 앞을 서성입니다]\r\n" +
                "[당신의 눈을 신뢰하세요]");


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

        // 게임 시작 버튼
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JaydenThraed thraed = new JaydenThraed();
                thraed.start();
            }
        });



        housebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicknum += 1;
                Log.d(TAG, "클릭 횟수 : " + clicknum);

                if (clicknum == 1) {

                    if (miro == 0) {

                        getgoldText.setText("+" + bet*4 + "골드");
                        getexpText.setText("+100exp");

                        answerText.startAnimation(appear);
                        getgoldText.startAnimation(drop);
                        getexpText.startAnimation(drop);
                        answerText.setVisibility(View.VISIBLE);

                        user.money = user.money + bet*4;
                        user.getexp(100);

                    }
                    else {

                        getexpText.setText("+20exp");

                        bangText.startAnimation(appear);
                        getexpText.startAnimation(drop);
                        bangText.setVisibility(View.VISIBLE);
                        getexpText.setVisibility(View.VISIBLE);

                        user.getexp(20);
                    }


                    editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                    editor.commit();

                    leveltext.setText("Lv." + user.level);
                    exptext.setText("exp : " + user.exp + " / " + user.fullexp);
                    moneytext.setText("골드 : " + user.money);

                    checkText.setVisibility(View.INVISIBLE);

                    AnswerThraed answerThraed = new AnswerThraed();
                    answerThraed.start();

                }

            }
        });

        housebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicknum += 1;
                Log.d(TAG, "클릭 횟수 : " + clicknum);

                if (clicknum == 1) {

                    if (miro == 1) {

                        getgoldText.setText("+" + bet*4 + "골드");
                        getexpText.setText("+100exp");

                        answerText.startAnimation(appear);
                        getgoldText.startAnimation(drop);
                        getexpText.startAnimation(drop);
                        answerText.setVisibility(View.VISIBLE);

                        user.money = user.money + bet*4;
                        user.getexp(100);

                    }
                    else {

                        getexpText.setText("+20exp");

                        bangText.startAnimation(appear);
                        getexpText.startAnimation(drop);
                        bangText.setVisibility(View.VISIBLE);
                        getexpText.setVisibility(View.VISIBLE);

                        user.getexp(20);

                    }

                    editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                    editor.commit();

                    leveltext.setText("Lv." + user.level);
                    exptext.setText("exp : " + user.exp + " / " + user.fullexp);
                    moneytext.setText("골드 : " + user.money);

                    checkText.setVisibility(View.INVISIBLE);

                    AnswerThraed answerThraed = new AnswerThraed();
                    answerThraed.start();

                }

            }
        });

        housebtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicknum += 1;
                Log.d(TAG, "클릭 횟수 : " + clicknum);

                if (clicknum == 1) {

                    if (miro == 2) {

                        getgoldText.setText("+" + bet*4 + "골드");
                        getexpText.setText("+100exp");

                        answerText.startAnimation(appear);
                        getgoldText.startAnimation(drop);
                        getexpText.startAnimation(drop);
                        answerText.setVisibility(View.VISIBLE);

                        user.money = user.money + bet*4;
                        user.getexp(100);

                    }
                    else {

                        getexpText.setText("+20exp");

                        bangText.startAnimation(appear);
                        getexpText.startAnimation(drop);
                        bangText.setVisibility(View.VISIBLE);
                        getexpText.setVisibility(View.VISIBLE);

                        user.getexp(20);

                    }

                    editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                    editor.commit();

                    leveltext.setText("Lv." + user.level);
                    exptext.setText("exp : " + user.exp + " / " + user.fullexp);
                    moneytext.setText("골드 : " + user.money);

                    checkText.setVisibility(View.INVISIBLE);

                    AnswerThraed answerThraed = new AnswerThraed();
                    answerThraed.start();

                }

            }
        });

        housebtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicknum += 1;
                Log.d(TAG, "클릭 횟수 : " + clicknum);

                if (clicknum == 1) {

                    if (miro == 3) {

                        getgoldText.setText("+" + bet*4 + "골드");
                        getexpText.setText("+100exp");

                        answerText.startAnimation(appear);
                        getgoldText.startAnimation(drop);
                        getexpText.startAnimation(drop);
                        answerText.setVisibility(View.VISIBLE);

                        user.money = user.money + bet*4;
                        user.getexp(100);

                    }
                    else {

                        getexpText.setText("+20exp");

                        bangText.startAnimation(appear);
                        getexpText.startAnimation(drop);
                        bangText.setVisibility(View.VISIBLE);
                        getexpText.setVisibility(View.VISIBLE);

                        user.getexp(20);

                    }

                    editor.putString("정보",info[0] + "," + info[1] + "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + info[6]);
                    editor.commit();

                    leveltext.setText("Lv." + user.level);
                    exptext.setText("exp : " + user.exp + " / " + user.fullexp);
                    moneytext.setText("골드 : " + user.money);


                    checkText.setVisibility(View.INVISIBLE);

                    AnswerThraed answerThraed = new AnswerThraed();
                    answerThraed.start();

                }

            }
        });

        // 정답확인이 끝난 후, 다시하기 버튼
        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicknum = 0;
                Log.d(TAG, "클릭 횟수 : " + clicknum);

                house1.startAnimation(alpha);
                house2.startAnimation(alpha);
                house3.startAnimation(alpha);
                house4.startAnimation(alpha);
                house1.setVisibility(View.VISIBLE);
                house2.setVisibility(View.VISIBLE);
                house3.setVisibility(View.VISIBLE);
                house4.setVisibility(View.VISIBLE);

                housebtn1.setVisibility(View.INVISIBLE);
                housebtn2.setVisibility(View.INVISIBLE);
                housebtn3.setVisibility(View.INVISIBLE);
                housebtn4.setVisibility(View.INVISIBLE);
                villiain1.setVisibility(View.INVISIBLE);
                villiain2.setVisibility(View.INVISIBLE);
                villiain3.setVisibility(View.INVISIBLE);
                villiain4.setVisibility(View.INVISIBLE);

                getexpText.setVisibility(View.INVISIBLE);
                getgoldText.setVisibility(View.INVISIBLE);
                answerText.setVisibility(View.INVISIBLE);
                bangText.setVisibility(View.INVISIBLE);
                resetbutton.setVisibility(View.INVISIBLE);
                betbutton.startAnimation(appear);
                betbutton.setVisibility(View.VISIBLE);

            }
        });

        // 뒤로가기 버튼
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 게임 안내창 닫기 버튼
        frameclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.INVISIBLE);
                betbutton.setVisibility(View.VISIBLE);
            }
        });

    }

    public void betDlg() {

        betText = new EditText(Game6Activity.this);
        betText.setInputType(0x00000002);

        AlertDialog.Builder betting = new AlertDialog.Builder(Game6Activity.this);
        betting.setTitle("베팅 금액을 입력하세요");
        betting.setView(betText);
        betting.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                bet = Integer.parseInt(betText.getText().toString());
                Log.d(TAG, "소지 금액 : " + user.money);
                Log.d(TAG, "입력받은 베팅금액 : " + bet);

                if (bet > 0) {

                    if (user.money - bet >= 0) {
                        startbutton.setVisibility(View.VISIBLE);
                        betbutton.setVisibility(View.INVISIBLE);

                        user.money = user.money - bet;
                        moneytext.setText("골드 : " + Integer.toString(user.money));
                        betchecktext.setText("-" + bet + "골드");
                        betchecktext.startAnimation(drop);
                    }

                    else {
                        Toast t = Toast.makeText(Game6Activity.this,"보유한 골드보다 많이 베팅하실 수 없습니다.",Toast.LENGTH_SHORT);
                        t.show();
                    }

                }
                else {
                    Toast t = Toast.makeText(Game6Activity.this,"베팅 최소단위는 1골드 입니다.",Toast.LENGTH_SHORT);
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
        AlertDialog.Builder reset = new AlertDialog.Builder(Game6Activity.this);
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

    public class JaydenThraed extends Thread {

        public void run() {
            for (int a = 1; a <= 4 ; a++) {
                int jayden = ran.nextInt(4);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        startbutton.setVisibility(View.INVISIBLE);
                        if (jayden == 0) {
                                villiain1.setVisibility(View.VISIBLE);
                        } else if (jayden == 1) {
                            villiain2.setVisibility(View.VISIBLE);
                        } else if (jayden == 2) {
                            villiain3.setVisibility(View.VISIBLE);
                        } else if (jayden == 3) {
                            villiain4.setVisibility(View.VISIBLE);
                        }
                        Log.d(TAG, "제이든 등장 위치 500 : " + jayden);
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
                        if (jayden == 0) {
                            villiain1.setVisibility(View.INVISIBLE);
                        } else if (jayden == 1) {
                            villiain2.setVisibility(View.INVISIBLE);
                        } else if (jayden == 2) {
                            villiain3.setVisibility(View.INVISIBLE);
                        } else if (jayden == 3) {
                            villiain4.setVisibility(View.INVISIBLE);
                        }
                        Log.d(TAG, "제이든 사라진 위치 500 : " + jayden);
                    }
                });

            }

            for (int a = 1; a <= 4; a++) {
                int jayden = ran.nextInt(4);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jayden == 0) {
                            villiain1.setVisibility(View.VISIBLE);
                        } else if (jayden == 1) {
                            villiain2.setVisibility(View.VISIBLE);
                        } else if (jayden == 2) {
                            villiain3.setVisibility(View.VISIBLE);
                        } else if (jayden == 3) {
                            villiain4.setVisibility(View.VISIBLE);
                        }
                        Log.d(TAG, "제이든 등장 위치 250 : " + jayden);
                    }
                });

                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jayden == 0) {
                            villiain1.setVisibility(View.INVISIBLE);
                        } else if (jayden == 1) {
                            villiain2.setVisibility(View.INVISIBLE);
                        } else if (jayden == 2) {
                            villiain3.setVisibility(View.INVISIBLE);
                        } else if (jayden == 3) {
                            villiain4.setVisibility(View.INVISIBLE);
                        }
                        Log.d(TAG, "제이든 사라진 위치 250 : " + jayden);
                    }
                });

            }

            for (int a = 1; a <= 4; a++) {
                int jayden = ran.nextInt(4);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jayden == 0) {
                            villiain1.setVisibility(View.VISIBLE);
                        } else if (jayden == 1) {
                            villiain2.setVisibility(View.VISIBLE);
                        } else if (jayden == 2) {
                            villiain3.setVisibility(View.VISIBLE);
                        } else if (jayden == 3) {
                            villiain4.setVisibility(View.VISIBLE);
                        }
                        Log.d(TAG, "제이든 등장 위치 100 : " + jayden);
                    }
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jayden == 0) {
                            villiain1.setVisibility(View.INVISIBLE);
                        } else if (jayden == 1) {
                            villiain2.setVisibility(View.INVISIBLE);
                        } else if (jayden == 2) {
                            villiain3.setVisibility(View.INVISIBLE);
                        } else if (jayden == 3) {
                            villiain4.setVisibility(View.INVISIBLE);
                        }
                        Log.d(TAG, "제이든 사라진 위치 100 : " + jayden);
                    }
                });

            }

            for (int a = 1; a <= 4; a++) {
                int jayden = ran.nextInt(4);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jayden == 0) {
                            villiain1.setVisibility(View.VISIBLE);
                        } else if (jayden == 1) {
                            villiain2.setVisibility(View.VISIBLE);
                        } else if (jayden == 2) {
                            villiain3.setVisibility(View.VISIBLE);
                        } else if (jayden == 3) {
                            villiain4.setVisibility(View.VISIBLE);
                        }
                        Log.d(TAG, "제이든 등장 위치 50 : " + jayden);
                    }
                });

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jayden == 0) {
                            villiain1.setVisibility(View.INVISIBLE);
                        } else if (jayden == 1) {
                            villiain2.setVisibility(View.INVISIBLE);
                        } else if (jayden == 2) {
                            villiain3.setVisibility(View.INVISIBLE);
                        } else if (jayden == 3) {
                            villiain4.setVisibility(View.INVISIBLE);
                        }
                        Log.d(TAG, "제이든 사라진 위치 50 : " + jayden);
                    }
                });

            }

            for (int a = 1; a <= 4; a++) {
                int jayden = ran.nextInt(4);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jayden == 0) {
                            villiain1.setVisibility(View.VISIBLE);
                        } else if (jayden == 1) {
                            villiain2.setVisibility(View.VISIBLE);
                        } else if (jayden == 2) {
                            villiain3.setVisibility(View.VISIBLE);
                        } else if (jayden == 3) {
                            villiain4.setVisibility(View.VISIBLE);
                        }
                        Log.d(TAG, "제이든 등장 위치 20 : " + jayden);
                    }
                });

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jayden == 0) {
                            villiain1.setVisibility(View.INVISIBLE);
                        } else if (jayden == 1) {
                            villiain2.setVisibility(View.INVISIBLE);
                        } else if (jayden == 2) {
                            villiain3.setVisibility(View.INVISIBLE);
                        } else if (jayden == 3) {
                            villiain4.setVisibility(View.INVISIBLE);
                        }
                        Log.d(TAG, "제이든 사라진 위치 20 : " + jayden);
                    }
                });

            }

            for (int a = 1; a <= 4; a++) {
                int jayden = ran.nextInt(4);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jayden == 0) {
                            villiain1.setVisibility(View.VISIBLE);
                        } else if (jayden == 1) {
                            villiain2.setVisibility(View.VISIBLE);
                        } else if (jayden == 2) {
                            villiain3.setVisibility(View.VISIBLE);
                        } else if (jayden == 3) {
                            villiain4.setVisibility(View.VISIBLE);
                        }
                        Log.d(TAG, "제이든 등장 위치 20 : " + jayden);
                    }
                });

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jayden == 0) {
                            villiain1.setVisibility(View.INVISIBLE);
                        } else if (jayden == 1) {
                            villiain2.setVisibility(View.INVISIBLE);
                        } else if (jayden == 2) {
                            villiain3.setVisibility(View.INVISIBLE);
                        } else if (jayden == 3) {
                            villiain4.setVisibility(View.INVISIBLE);
                        }
                        Log.d(TAG, "제이든 사라진 위치 20 : " + jayden);

                        miro = jayden;
                        Log.d(TAG, "제이든 위치 : " + miro);
                    }
                });

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    checkText.startAnimation(alpha);
                    checkText.setVisibility(View.VISIBLE);
                    house1.setVisibility(View.INVISIBLE);
                    house2.setVisibility(View.INVISIBLE);
                    house3.setVisibility(View.INVISIBLE);
                    house4.setVisibility(View.INVISIBLE);
                    housebtn1.startAnimation(alpha);
                    housebtn2.startAnimation(alpha);
                    housebtn3.startAnimation(alpha);
                    housebtn4.startAnimation(alpha);
                    housebtn1.setVisibility(View.VISIBLE);
                    housebtn2.setVisibility(View.VISIBLE);
                    housebtn3.setVisibility(View.VISIBLE);
                    housebtn4.setVisibility(View.VISIBLE);
                }
            });

        }
    }

    public class AnswerThraed extends Thread{
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {

                    if (miro == 0) {
                        villiain1.startAnimation(alpha);
                        villiain1.setVisibility(View.VISIBLE);
                    }
                    else if (miro == 1) {
                        villiain2.startAnimation(alpha);
                        villiain2.setVisibility(View.VISIBLE);
                    }
                    else if (miro == 2) {
                        villiain3.startAnimation(alpha);
                        villiain3.setVisibility(View.VISIBLE);
                    }
                    else if (miro == 3) {
                        villiain4.startAnimation(alpha);
                        villiain4.setVisibility(View.VISIBLE);
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