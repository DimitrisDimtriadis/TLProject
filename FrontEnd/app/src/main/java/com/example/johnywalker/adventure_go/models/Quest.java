package com.example.johnywalker.adventure_go.models;

import java.math.BigDecimal;

/**
 * Created by JohnyWalker on 12/14/2016.
 */

public class Quest
{
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Riddle riddle;

    public Quest(){}

    public Quest(Riddle riddle, BigDecimal latitude, BigDecimal longtitude)
    {
        super();

        this.riddle = riddle;
        this.latitude = latitude;
        this.longitude = longtitude;
    }

    public BigDecimal getLatitude()
    {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude)
    {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude()
    {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude)
    {
        this.longitude = longitude;
    }

    public Riddle getRiddle()
    {
        return riddle;
    }

    public void setRiddle(Riddle riddle)
    {
        this.riddle = riddle;
    }

    @Override
    public String toString()
    {
        return "Quest{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", riddle=" + riddle +
                '}';
    }
}
