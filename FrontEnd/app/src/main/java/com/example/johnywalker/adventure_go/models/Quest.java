package com.example.johnywalker.adventure_go.models;

/**
 * Created by Dimitriadis983 on 26-Dec-16.
 */

public class Quest
{
    private Double Latitude, Longitude;

    public Quest (Double Latitude,Double Longitude){
        this.setLatitude(Latitude);
        this.setLongitude(Longitude);

    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        this.Longitude = longitude;
    }
}
