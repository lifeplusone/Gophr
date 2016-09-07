package com.example.khalilvanalphen.gophr;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by khalilvanalphen on 2016-09-02.
 */
public class GphTask implements Parcelable{

    /**
     * GOPHR TASKS
     */




    private String title;
    private String description;
    private ArrayList<Location> locations = new ArrayList<>();
    private ArrayList<Time> times = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void addLocation(Location l) {
        locations.add(l);
    }

    public ArrayList<Location> getLocations(){
        return locations;
    }

    public ArrayList<Time> getTimes(){
        return times;
    }
    public void addTime(Time t) {
        times.add(t);
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
        parcel.writeSerializable(times);
        parcel.writeSerializable(locations);
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
        times = (ArrayList<Time>) in.readSerializable();
        locations = (ArrayList<Location>) in.readSerializable();
    }
}
