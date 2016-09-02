package com.example.khalilvanalphen.gophr;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by khalilvanalphen on 2016-09-02.
 */
public class GphTask implements Parcelable{
    String name;

    public GphTask (String name){
        this.name = name;
    }

    public GphTask(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
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

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private GphTask(Parcel in) {
        name = in.readString();
    }
}
