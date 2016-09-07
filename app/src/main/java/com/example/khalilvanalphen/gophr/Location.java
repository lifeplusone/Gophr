package com.example.khalilvanalphen.gophr;

import java.io.Serializable;

/**
 * Created by khalilvanalphen on 2016-09-07.
 */
public class Location implements Serializable {
    private double lat;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private double lng;
    private String tag;
    private String name;
    private String id;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Location(){}

    @Override
    public String toString(){
        return lat + "/" + lng;
    }
}
