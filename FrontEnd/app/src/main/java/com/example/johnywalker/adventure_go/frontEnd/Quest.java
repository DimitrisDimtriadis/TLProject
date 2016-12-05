package com.example.johnywalker.adventure_go.frontEnd;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by rezu on 5/12/2016.
 */

public class Quest {
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
