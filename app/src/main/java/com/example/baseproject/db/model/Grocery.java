package com.example.baseproject.db.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grocery")
public class Grocery {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String number;
    private boolean isBought;

    public Grocery() {
    }

    public String getNum(){
        return String.valueOf(number);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }
}
