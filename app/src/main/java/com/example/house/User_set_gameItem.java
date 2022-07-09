package com.example.house;

public class User_set_gameItem {
    String name;
    String comment;
    int idNum;

    public User_set_gameItem(){

    }

    public User_set_gameItem(String name, String comment, int idNum) {
        this.name = name;
        this.comment = comment;
        this.idNum = idNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }
}
