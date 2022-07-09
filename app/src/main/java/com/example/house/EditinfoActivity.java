package com.example.house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class EditinfoActivity extends AppCompatActivity {

    String TAG = "정보 수정 화면";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String input;
    String[] info;
    UserInfo user;
    ImageView imageView;
    FrameLayout frameLayout;
    Button framecloseButton;
    ImageView catButton;
    ImageView tigerButton;
    ImageView whaleButton;
    ImageView pigButton;
    ImageView giraffeButton;
    ImageView elephantButton;
    Animation appear;

    String animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);
        Log.d(TAG, "onCreate() 호출됨");

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() 호출됨");

        sharedPreferences = getSharedPreferences("사용자 정보",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        input = sharedPreferences.getString("정보","");
        Log.d(TAG, " input 사용자 정보: " + input);

        info = input.split(",");
        user = new UserInfo(Integer.parseInt(info[2]),Integer.parseInt(info[3]),Integer.parseInt(info[4]));
        user.fullexp = Integer.parseInt(info[5]);

        Log.d(TAG,   "저장된 정보 : " + user.level + " " + user.exp + " " + user.money);

        Button completbutton = findViewById(R.id.editinfo_completebutton);
        Button backbutton = findViewById(R.id.editinfo_backbutton);
        imageView = findViewById(R.id.editinfo_setimageView);

        String cat = getURLForResource(R.drawable.cat);
        String tiger = getURLForResource(R.drawable.tiger);
        String whale = getURLForResource(R.drawable.whale);
        String pig = getURLForResource(R.drawable.pig);
        String giraffe = getURLForResource(R.drawable.giraffe);
        String elephant = getURLForResource(R.drawable.elephant);

        String[] country = {"국가를 선택하세요","대한민국", "미국", "이집트", "베트남", "독일", "몽골"};
        Spinner countryspinner = findViewById(R.id.editinfo_countryspinner);
        EditText editTextName = findViewById(R.id.editinfo_editTextName);
        frameLayout = findViewById(R.id.editinfo_frameLayout);
        framecloseButton = findViewById(R.id.editinfo_frameLayout_closebutton);
        catButton = findViewById(R.id.editinfo_catimage);
        tigerButton = findViewById(R.id.editinfo_tigerimage);
        whaleButton = findViewById(R.id.editinfo_whaleimage);
        pigButton = findViewById(R.id.editinfo_pigimage);
        giraffeButton = findViewById(R.id.editinfo_giraffeimage);
        elephantButton = findViewById(R.id.editinfo_elephantimage);
        appear = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.boxappear);

        editTextName.setText(info[1]);
        Glide.with(getApplicationContext())
                .load(Uri.parse(info[6]))
                .into(imageView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,country);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryspinner.setAdapter(adapter);

        countryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 완료 버튼
        completbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (countryspinner.getSelectedItem().toString() == country[0]) {
                    Toast t = Toast.makeText(EditinfoActivity.this, "국가를 선택해주세요.", Toast.LENGTH_SHORT);
                    t.show();
                }
                else if (editTextName.getText().toString().equals("")) {
                    Toast t = Toast.makeText(EditinfoActivity.this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT);
                    t.show();
                }
                else if (animal == null) {
                    Toast t = Toast.makeText(EditinfoActivity.this, "캐릭터를 선택해주세요.", Toast.LENGTH_SHORT);
                    t.show();
                }
                else {
                    Intent i = new Intent(EditinfoActivity.this, Casual_mode_selectActivity.class);
                    i.putExtra("국가", countryspinner.getSelectedItem().toString());
                    i.putExtra("닉네임", editTextName.getText().toString());
                    startActivity(i);

                    Toast t = Toast.makeText(completbutton.getContext(), "수정 완료 !", Toast.LENGTH_SHORT);
                    t.show();

                    editor.putString("정보", countryspinner.getSelectedItem().toString() + "," + editTextName.getText().toString() +
                            "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + animal);
                    editor.commit();
                    Log.d(TAG, "저장된 정보 : " + user.level + " " + user.exp + " " + user.money);

                    finish();
                }
            }
        });

        // 뒤로가기 버튼
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.startAnimation(appear);
                frameLayout.setVisibility(View.VISIBLE);

                completbutton.setVisibility(View.INVISIBLE);
                backbutton.setVisibility(View.INVISIBLE);
            }
        });

        framecloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.INVISIBLE);
                completbutton.setVisibility(View.VISIBLE);
                backbutton.setVisibility(View.VISIBLE);
            }
        });

        catButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.INVISIBLE);
//                imageView.setImageResource(R.drawable.cat);

                Glide.with(getApplicationContext())
                        .load(cat)
                        .into(imageView);

                animal = cat;
                Log.d(TAG, "저장된 동물 : " + animal);

                completbutton.setVisibility(View.VISIBLE);
                backbutton.setVisibility(View.VISIBLE);

            }
        });

        tigerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.INVISIBLE);
//                imageView.setImageResource(R.drawable.tiger);

                Glide.with(getApplicationContext())
                        .load(tiger)
                        .into(imageView);

                animal = tiger;
                Log.d(TAG, "저장된 동물 : " + animal);

                completbutton.setVisibility(View.VISIBLE);
                backbutton.setVisibility(View.VISIBLE);
            }
        });

        whaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.INVISIBLE);
//                imageView.setImageResource(R.drawable.whale);

                Glide.with(getApplicationContext())
                        .load(whale)
                        .into(imageView);

                animal = whale;
                Log.d(TAG, "저장된 동물 : " + animal);

                completbutton.setVisibility(View.VISIBLE);
                backbutton.setVisibility(View.VISIBLE);
            }
        });

        pigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.INVISIBLE);
//                imageView.setImageResource(R.drawable.pig);

                Glide.with(getApplicationContext())
                        .load(pig)
                        .into(imageView);

                animal = pig;
                Log.d(TAG, "저장된 동물 : " + animal);

                completbutton.setVisibility(View.VISIBLE);
                backbutton.setVisibility(View.VISIBLE);
            }
        });

        giraffeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.INVISIBLE);
//                imageView.setImageResource(R.drawable.giraffe);

                Glide.with(getApplicationContext())
                        .load(giraffe)
                        .into(imageView);

                animal = giraffe;
                Log.d(TAG, "저장된 동물 : " + animal);

                completbutton.setVisibility(View.VISIBLE);
                backbutton.setVisibility(View.VISIBLE);
            }
        });

        elephantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.INVISIBLE);
//                imageView.setImageResource(R.drawable.elephant);

                Glide.with(getApplicationContext())
                        .load(elephant)
                        .into(imageView);

                animal = elephant;
                Log.d(TAG, "저장된 동물 : " + animal);

                completbutton.setVisibility(View.VISIBLE);
                backbutton.setVisibility(View.VISIBLE);
            }
        });

    }

    private String getURLForResource(int resId) {
        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resId).toString();
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