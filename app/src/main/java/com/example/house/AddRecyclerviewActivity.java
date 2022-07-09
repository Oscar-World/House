package com.example.house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddRecyclerviewActivity extends AppCompatActivity {

    String TAG = "게임 추가";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recyclerview);
        Log.d(TAG, "onCreate() 호출됨");

        Button editbtn = findViewById(R.id.editOkbutton);
        Button addbtn = findViewById(R.id.addOkbutton);

        Intent geti = getIntent();
        int position = geti.getIntExtra("포지션",-1);
        Log.d(TAG, "B액티비티에서 포지션 받기 : " + position);
        String visib = geti.getStringExtra("visibility");
        Log.d(TAG, "받은 visib 값 : " + visib);
        if (visib.equals("미로천재")) {
            editbtn.setVisibility(View.GONE);
        }
        else if (visib.equals("야마최고")) {
            addbtn.setVisibility(View.GONE);
        }


        // 게임 선택 스피너
        String[] game = {"게임을 선택하세요","1번", "2번", "3번", "4번", "5번"};

        Spinner gamespinner = findViewById(R.id.addSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,game);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gamespinner.setAdapter(adapter);

        gamespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        EditText gamememo = findViewById(R.id.addEditText);

        Button editbutton = findViewById(R.id.editOkbutton);

        Button okbutton = findViewById(R.id.addOkbutton);
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent();
                i.putExtra("게임이름",gamespinner.getSelectedItem().toString());
                i.putExtra("게임메모",gamememo.getText().toString());
                i.putExtra("게임사진",R.drawable.ic_launcher_background);
                i.putExtra("B포지션",position);
                Log.d(TAG, "B에서 완료버튼 클릭 시, A로 넘기는 포지션 값: " + position);
                setResult(1,i);
                finish();

//                int a;
//                for (a=1;a<=10;a++){
//                }

                SharedPreferences sharedPreferences = getSharedPreferences("게임목록",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(String.valueOf(position),gamespinner.getSelectedItem().toString() + "," + gamememo.getText().toString() + "," + R.drawable.ic_launcher_background);
                Log.d(TAG, "B에서 추가로 저장된 포지션 값 : " + position);
                editor.commit();

                Log.d(TAG, "저장된 게임 목록 : " + gamespinner.getSelectedItem().toString() + "," + gamememo.getText().toString() + "," + R.drawable.ic_launcher_background);

            }
        });

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("게임이름",gamespinner.getSelectedItem().toString());
                i.putExtra("게임메모",gamememo.getText().toString());
                i.putExtra("게임사진",R.drawable.ic_launcher_background);
                i.putExtra("B포지션",position);
                Log.d(TAG, "B에서 수정버튼 클릭 시, A로 넘기는 포지션 값: " + position);
                setResult(2,i);

                SharedPreferences sharedPreferences = getSharedPreferences("게임목록",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(String.valueOf(position),gamespinner.getSelectedItem().toString() + "," + gamememo.getText().toString() + "," + R.drawable.ic_launcher_background);
                Log.d(TAG, "B에서 수정으로 저장된 포지션 값 : " + position);
                editor.commit();

                Log.d(TAG, "수정된 정보 : " + gamespinner.getSelectedItem().toString() + "," + gamememo.getText().toString() + "," + R.drawable.ic_launcher_background);

                finish();
            }
        });

        Button backbutton = findViewById(R.id.addcancelbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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