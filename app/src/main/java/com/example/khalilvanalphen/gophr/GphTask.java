package com.example.khalilvanalphen.gophr;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by khalilvanalphen on 2016-09-02.
 */
public class GphTask implements Parcelable{

    /**
     * GOPHR TASKS
     */




    private String title;
    private String description;
    private int day;
    private int month;
    private int hour;
    private int minute;
    private LatLng location;

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
    public LatLng getLocation(){
        return location;
    }

    public void setTime(int month, int day, int hour, int minute) {
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
    public long getMonth(){
        return month;
    }
    public long getDay(){
        return day;
    }
    public long getHour(){
        return hour;
    }
    public long getMinute(){
        return minute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GphTask(){

    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeInt(month);
        parcel.writeInt(day);
        parcel.writeInt(hour);
        parcel.writeInt(minute);
        parcel.writeParcelable(location, 0);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<GphTask> CREATOR = new Parcelable.Creator<GphTask>() {
        public GphTask createFromParcel(Parcel in) {
            return new GphTask(in);
        }

        public GphTask[] newArray(int size) {
            return new GphTask[size];
        }
    };

    private GphTask(Parcel in) {
        title = in.readString();
        description = in.readString();
        month = in.readInt();
        day = in.readInt();
        hour = in.readInt();
        minute = in.readInt();
        location = in.readParcelable(LatLng.class.getClassLoader());
    }
}
