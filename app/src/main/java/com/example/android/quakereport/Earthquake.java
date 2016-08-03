package com.example.android.quakereport;

/**
 * Created by admin on 31-07-2016.
 */
public class Earthquake {

    // variable to store earthquake information
    private double magnitude;
    private String location;
    private Long time;

    //Constructor
    public Earthquake(double mMagnitude, String mLocation, Long mTime) {
        this.magnitude = mMagnitude;
        this.location = mLocation;
        this.time = mTime;
    }

    //getter Function
    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public Long getTime() {
        return time;
    }


}
