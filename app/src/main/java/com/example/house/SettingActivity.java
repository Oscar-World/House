package com.example.house;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class SettingActivity extends AppCompatActivity {

    String TAG = "계정 설정"; // 로그 확인용 선언

    int level = 1;
    int exp = 0;
    int money = 100;
    UserInfo user = new UserInfo(level,exp,money);
    ImageView imageView;
    String animal;

//    private int GALLEY_CODE = 10;

    FrameLayout frameLayout;
    Button framecloseButton;
    ImageView catButton;
    ImageView tigerButton;
    ImageView whaleButton;
    ImageView pigButton;
    ImageView giraffeButton;
    ImageView elephantButton;
    Animation appear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        Log.d(TAG, "onCreate() 호출됨");

        level = user.level;
        exp = user.exp;
        money = user.money;

        String cat = getURLForResource(R.drawable.cat);
        String tiger = getURLForResource(R.drawable.tiger);
        String whale = getURLForResource(R.drawable.whale);
        String pig = getURLForResource(R.drawable.pig);
        String giraffe = getURLForResource(R.drawable.giraffe);
        String elephant = getURLForResource(R.drawable.elephant);

        String[] country = {"국가를 선택하세요","대한민국", "미국", "이집트", "베트남", "독일", "몽골"};
        Spinner countryspinner = findViewById(R.id.countryspinner);
        EditText editTextName = findViewById(R.id.editTextName);
        Button completbutton = findViewById(R.id.setting_completebutton);
        Button backbutton = findViewById(R.id.setting_backbutton);
        imageView = findViewById(R.id.setting_setimagebutton);
        frameLayout = findViewById(R.id.setting_frameLayout);
        framecloseButton = findViewById(R.id.setting_frameLayout_closebutton);
        catButton = findViewById(R.id.setting_catimage);
        tigerButton = findViewById(R.id.setting_tigerimage);
        whaleButton = findViewById(R.id.setting_whaleimage);
        pigButton = findViewById(R.id.setting_pigimage);
        giraffeButton = findViewById(R.id.setting_giraffeimage);
        elephantButton = findViewById(R.id.setting_elephantimage);
        appear = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.boxappear);


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
                    Toast t = Toast.makeText(SettingActivity.this, "국가를 선택해주세요.", Toast.LENGTH_SHORT);
                    t.show();
                }
                else if (editTextName.getText().toString().equals("")) {
                    Toast t = Toast.makeText(SettingActivity.this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT);
                    t.show();
                }
                else if (animal == null) {
                    Toast t = Toast.makeText(SettingActivity.this, "캐릭터를 선택해주세요.", Toast.LENGTH_SHORT);
                    t.show();
                }
                else {
                    Intent i = new Intent(SettingActivity.this, Casual_mode_selectActivity.class);
                    i.putExtra("국가", countryspinner.getSelectedItem().toString());
                    i.putExtra("닉네임", editTextName.getText().toString());
                    startActivity(i);
                    Toast t = Toast.makeText(completbutton.getContext(), "설정 완료 !", Toast.LENGTH_SHORT);
                    t.show();

                    SharedPreferences sharedPreferences = getSharedPreferences("사용자 정보", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("정보", countryspinner.getSelectedItem().toString() + "," + editTextName.getText().toString() +
                            "," + user.level + "," + user.exp + "," + user.money + "," + user.fullexp + "," + animal);
                    editor.commit();

                    Log.d(TAG, "저장된 정보 : " + countryspinner.getSelectedItem().toString() + "," + editTextName.getText().toString());

                    finish();
                }

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(Intent.ACTION_PICK);
//                i.setType(MediaStore.Images.Media.CONTENT_TYPE);
//                startActivityForResult(i,GALLEY_CODE);

                frameLayout.startAnimation(appear);
                frameLayout.setVisibility(View.VISIBLE);

                completbutton.setVisibility(View.INVISIBLE);
                backbutton.setVisibility(View.INVISIBLE);

            }
        });

        // 뒤로가기 버튼
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

//    private String getRealPathFromUri(Uri uri) { // 절대 경로 구하기
//        String[] proj=  {MediaStore.Images.Media.DATA};
//        CursorLoader cursorLoader = new CursorLoader(this,uri,proj,null,null,null);
//        Cursor cursor = cursorLoader.loadInBackground();
//
//        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String url = cursor.getString(columnIndex);
//        cursor.close();
//        return  url;
//    }
//
//    private String imageUrl;
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // 사진 고른 후 돌아오기 + 로컬 파일 업로드
//        if(requestCode == GALLEY_CODE)
//        {
//            imageUrl = getRealPathFromUri(data.getData());
//            RequestOptions cropOptions = new RequestOptions();
//
//            Log.d(TAG, "imageUrl : " + imageUrl);
//
//            Glide.with(getApplicationContext())
//                    .load(imageUrl)
//                    .placeholder(R.drawable.whale)
//                    .error(R.drawable.tiger)
//                    .fallback(R.drawable.cat)
//                    .apply(cropOptions.centerCrop())
//                    .into(imageView);
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() 호출됨");

        Log.d(TAG, "imageView : " + imageView);
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