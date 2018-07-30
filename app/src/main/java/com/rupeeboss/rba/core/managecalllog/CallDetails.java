package com.rupeeboss.rba.core.managecalllog;

/**
 * Created by IN-RB on 02-02-2017.
 */
public class CallDetails {
    String number;
    String duration;
    String type;
    String date;

    public CallDetails(String number, String duration, String type, String date, String name) {
        this.number = number;
        this.duration = duration;
        this.type = type;
        this.date = date;
        this.name = name;
    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

