package com.example.house;

import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;
import java.util.Set;

public class UserInfo extends AppCompatActivity {

    int level;
    int exp;
    int money;
    int fullexp = 10;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String input;
    String[] info;

    public UserInfo(int level, int exp, int money) {

        this.level = level;
        this.exp = exp;
        this.money = money;

    }

    public void getexp(int getexp) {

        exp = exp + getexp;
        while (exp >= fullexp) {
            exp = exp - fullexp;
            level = level +1;
            fullexp = fullexp +5;

        }
    }

//    public void setMoney(int money) {
//        this.money = money;
//    }
//
//    public int getMoney() {
//        return money;
//    }

    public void reset() {

        level = 1;
        exp = 0;
        money = 100;
        fullexp = 10;

    }

}
