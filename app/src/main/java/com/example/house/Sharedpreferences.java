package com.example.house;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class Sharedpreferences extends AppCompatActivity {

    public void savePreferences(String nameKey, String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(nameKey,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();

    }

    public void openPreferences(String nameKey, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(nameKey,MODE_PRIVATE);
        String var1 = sharedPreferences.getString(key,"");


    }

}
