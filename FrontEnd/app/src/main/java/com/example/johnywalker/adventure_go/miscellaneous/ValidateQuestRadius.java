package com.example.johnywalker.adventure_go.miscellaneous;

import android.location.Location;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by JohnyWalker on 12/18/2016.
 */

public class ValidateQuestRadius
{
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Location initialLocation;
    private int result;

    public ValidateQuestRadius(BigDecimal x, BigDecimal y, Location location)
    {
        this.latitude = x;
        this.longitude = y;
        this.initialLocation = location;
    }

    public boolean isQuestRadiusValid()
    {
        BigDecimal latitudeDifference = latitude.subtract(getLatitude());
        BigDecimal longitudeDifference = latitude.subtract(getLongitude());

        BigDecimal radius = new BigDecimal("0.0002");

        result = latitudeDifference.pow(2).add(longitudeDifference.pow(2)).compareTo(radius.pow(2));

        if(result == 1)
        {
            return false;
        }
        else if(result == -1 || result == 0)
        {
            return true;
        }
        else
        {
            System.out.println("There was an error");
            return false;
        }
    }

    private BigDecimal getLatitude()
    {
        BigDecimal result = new BigDecimal(initialLocation.getLatitude(), MathContext.DECIMAL64);
        return result;
    }

    private BigDecimal getLongitude()
    {
        BigDecimal result = new BigDecimal(initialLocation.getLongitude(), MathContext.DECIMAL64);
        return result;
    }
}
