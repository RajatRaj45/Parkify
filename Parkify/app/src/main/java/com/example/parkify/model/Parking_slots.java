package com.example.parkify.model;

public class Parking_slots {
    private int id;
    private String state;

    public Parking_slots(){
    }
    public Parking_slots(int id, String state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }
}
