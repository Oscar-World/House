package com.example.house;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class UserSetModeSelectActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private User_set_gameAdapter adapter;
    private ArrayList<User_set_gameItem> gameItems;
    Context context = UserSetModeSelectActivity.this;
    String TAG = "게임 목록";
    SharedPreferences sharedPreferences;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_set_mode_select);
        Log.d(TAG, "onCreate() 호출됨");


        // 추가 버튼
        Button insertBtn = findViewById(R.id.insert_btn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserSetModeSelectActivity.this, AddRecyclerviewActivity.class);
                i.putExtra("visibility","미로천재");
                i.putExtra("포지션",gameItems.size());
                launcher.launch(i); // 되돌아온다고 약속하고 보내준다
            }
        });
        // 뒤로가기 버튼
        Button backbutton = findViewById(R.id.user_set_backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        ImageButton editbutton = findViewById(R.id.edit_Button);
//        editbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(UserSetModeSelectActivity.this, AddRecyclerviewActivity.class);
//                launcher.launch(i);
//            }
//        });

        recyclerView = findViewById(R.id.recyclerView);

        adapter = new User_set_gameAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        gameItems = new ArrayList<>();

        adapter.setGameList(gameItems);
        adapter.abclauncher(launcher);
        adapter.context(context);

        sharedPreferences = getSharedPreferences("게임목록", MODE_PRIVATE);

        String size = sharedPreferences.getString("사이즈","");

        if (!size.equals("")) {
            for (int a = 0; a < Integer.parseInt(size); a++) {
                String input = sharedPreferences.getString(String.valueOf(a), "");
                if (!input.equals("")) {
                    Log.d(TAG, "저장된 리스트 : " + sharedPreferences.getString(String.valueOf(a), ""));
                    String[] info = input.split(",");
                    User_set_gameItem shared = new User_set_gameItem(info[0], info[1], R.drawable.ic_launcher_background);
                    gameItems.add(shared);
                    Log.d(TAG, "리스트 : " + gameItems);
                    adapter.notifyDataSetChanged();
                }
            }
        }

    }
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult data) {
                    Log.d("TAG", "onActivityResult : " + data);

                    if (data.getResultCode() == 1) {
                        Intent geti = data.getData();
                        String gametype = geti.getStringExtra("게임이름");
                        Log.d("TAG", "호출된 gametype data : " + gametype);
                        String gamememo = geti.getStringExtra("게임메모");
                        Log.d("TAG", "호출된 gamememo data : " + gamememo);
                        int gameimage = geti.getIntExtra("게임사진",R.drawable.ic_launcher_background);
                        User_set_gameItem gi = new User_set_gameItem(gametype, gamememo, gameimage);
                        position = geti.getIntExtra("B포지션",position);
                        Log.d(TAG, "B에서 받아온 추가된 포지션값 : " + position);
                        gameItems.add(position,gi);

                        adapter.notifyDataSetChanged();
                    }

                    else if (data.getResultCode() == 2){
                        Intent geti = data.getData();
                        String gametype = geti.getStringExtra("게임이름");
                        Log.d("TAG", "호출된 gametype data : " + gametype);
                        String gamememo = geti.getStringExtra("게임메모");
                        Log.d("TAG", "호출된 gamememo data : " + gamememo);
                        int gameimage = geti.getIntExtra("게임사진",R.drawable.ic_launcher_background);
                        User_set_gameItem gi = new User_set_gameItem(gametype, gamememo, gameimage);
                        position = geti.getIntExtra("B포지션",position);
                        Log.d(TAG, "B에서 받아온 수정된 포지션값 : " + position);
                        gameItems.set(position,gi);

                        adapter.notifyDataSetChanged();

                    }
                }
            });

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

        SharedPreferences sharedPreferences = getSharedPreferences("게임목록",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("사이즈",String.valueOf(gameItems.size()));
        Log.d(TAG, "리스트 사이즈 : " + gameItems.size());
        editor.commit();

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