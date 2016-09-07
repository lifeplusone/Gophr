package com.example.khalilvanalphen.gophr;

import java.io.Serializable;

/**
 * Created by khalilvanalphen on 2016-09-07.
 */
public class Time implements Serializable{
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String tag;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Time(){}

    @Override
    public String toString(){
        return hour + ":" + minute;
    }
}
