package com.example.house;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Mode_choiceActivity extends AppCompatActivity {

    SettingActivity seti;
    String TAG = "게임 모드 선택";

    Button casualhelp;
    Button timehelp;
    Button userhelp;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String input;
    String[] info;
    UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_choice);
        Log.d(TAG, "onCreate() 호출됨");
    }

    public void casualinfoDlg() {

        AlertDialog.Builder info = new AlertDialog.Builder(Mode_choiceActivity.this);
        info.setTitle("캐주얼 모드");
        info.setMessage("다양한 종류의 게임을 즐길 수 있는\r\n" +
                "일반적인 플레이모드 입니다.\r\n\r\n" +
                "게임을 통해 간단한 캐릭터 육성이 가능합니다.");
        info.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog infoDlg = info.create();
        infoDlg.show();

    }

    public void timeattackinfoDlg() {

        AlertDialog.Builder info = new AlertDialog.Builder(Mode_choiceActivity.this);
        info.setTitle("타임어택 모드");
        info.setMessage("캐주얼 모드에서 갈고 닦은 실력으로\r\n" +
                "다른 사용자와 경쟁 가능한 모드입니다.\r\n\r\n" +
                "일정 레벨 & 골드 도달 시 클리어 타임이\r\n" +
                "기록되며 랭킹으로 표시됩니다.");
        info.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog infoDlg = info.create();
        infoDlg.show();

    }

    public void usersetinfoDlg() {

        AlertDialog.Builder info = new AlertDialog.Builder(Mode_choiceActivity.this);
        info.setTitle("사용자 설정 모드");
        info.setMessage("다양한 게임들을 사용자가 원하는대로\r\n" +
                "커스텀하여 이용할 수 있는 모드입니다.\r\n\r\n" +
                "내기, 뽑기 등에 유용하게 사용하세요 !\r\n\r\n" +
                "(개방조건 : 캐주얼 모드 5레벨 ↑)");
        info.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog infoDlg = info.create();
        infoDlg.show();

    }


    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() 호출됨");

        TextView countryText = findViewById(R.id.mode_countrytext);
        TextView nameText = findViewById(R.id.mode_nametext);
        ImageView photo = findViewById(R.id.mode_pictureview);
        Button casualmodebutton = findViewById(R.id.casual_modebutton);
        Button backbutton = findViewById(R.id.mode_choice_backbutton);
        Button user_set_modebutton = findViewById(R.id.user_set_modebutton);
        ImageButton editbutton = findViewById(R.id.mode_editBtn);
        Button time_attack_modebutton = findViewById(R.id.time_attack_modebutton);
        casualhelp = findViewById(R.id.casual_helpbutton);
        timehelp = findViewById(R.id.time_attack_helpbutton);
        userhelp = findViewById(R.id.user_set_helpbutton);

        Intent geti = getIntent();

        String country = geti.getStringExtra("국가");
        String name = geti.getStringExtra("닉네임");
        countryText.setText(country);
        nameText.setText(name);

        sharedPreferences = getSharedPreferences("사용자 정보",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        input = sharedPreferences.getString("정보","");

        Log.d(TAG, " input 사용자 정보: " + input);

        info = input.split(",");

        user = new UserInfo(Integer.parseInt(info[2]),Integer.parseInt(info[3]),Integer.parseInt(info[4]));
        user.fullexp = Integer.parseInt(info[5]);


        countryText.setText(info[0]);
        nameText.setText(info[1]);

        Log.d(TAG, "불러온 국가명 : " + info[0]);
        Log.d(TAG, "불러온 닉네임 : " + info[1]);
        Log.d(TAG,   "저장된 정보 : " + user.level + " " + user.exp + " " + user.money);


        casualmodebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Mode_choiceActivity.this, Casual_mode_selectActivity.class);
                startActivity(j);
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        time_attack_modebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(time_attack_modebutton.getContext(),"서비스 준비중입니다.",Toast.LENGTH_SHORT);
                t.show();
            }
        });

        user_set_modebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user.level > 4) {
                    Intent i = new Intent(Mode_choiceActivity.this, UserSetModeSelectActivity.class);
                    startActivity(i);
                }
                else {
                    Toast t = Toast.makeText(time_attack_modebutton.getContext(),"아직 개방되지 않았습니다.",Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Mode_choiceActivity.this, EditinfoActivity.class);
                startActivity(i);
                finish();
            }
        });

        casualhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                casualinfoDlg();
            }
        });

        timehelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeattackinfoDlg();
            }
        });

        userhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usersetinfoDlg();
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