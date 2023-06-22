package com.example.parkify.model;

import java.util.jar.Attributes;

public class User_Details {
    private String id;
    private String name;
    private String cnum;
    private String pin;
    private String slot;
    private String time;

    public User_Details() {
    }

    public User_Details(String Name1, int Cnum1, int Pin1){
        this.name = Name1;
        this.cnum = Integer.toString(Cnum1);
        this.pin = Integer.toString(Pin1);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setCnum(String cnum) {
//        this.cnum = cnum;
//    }
//
//    public void setPin(String pin) {
//        this.pin = pin;
//    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCnum() {
        return cnum;
    }

    public String getPin() {
        return pin;
    }

    public String getSlot() {
        return slot;
    }

    public String getTime() {
        return time;
    }
}
