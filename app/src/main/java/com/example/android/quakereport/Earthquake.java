package com.example.android.quakereport;

/**
 * Created by admin on 31-07-2016.
 */
public class Earthquake {

    // variable to store earthquake information
    private double magnitude;
    private String location;
    private Long time;
    private String url;

    //Constructor
    public Earthquake(double mMagnitude, String mLocation, Long mTime, String mUrl) {
        this.magnitude = mMagnitude;
        this.location = mLocation;
        this.time = mTime;
        this.url = mUrl;
    }

    //getter Function

    // return the magnitude of the earthquake
    public double getMagnitude() {
        return magnitude;
    }

    // return the location of the earthquake
    public String getLocation() {
        return location;
    }

    // return the time at which earthquake occurs
    public Long getTime() {
        return time;
    }

    // return the url of the webpage
    public String getUrl() {
        return url;
    }


}
