package com.example.johnywalker.adventure_go.miscellaneous;

import android.location.Location;

import com.example.johnywalker.adventure_go.controller.Controller;
import com.example.johnywalker.adventure_go.frontEnd.MapsActivity;
import com.example.johnywalker.adventure_go.models.Quest;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

/**
 * Created by JohnyWalker on 12/18/2016.
 */

public class QuestManager
{
    private MapsActivity mMap;
    private Controller controller;
    private LatLng questLocation;
    private Location initialLocation;

    private ValidateQuestRadius validateQuestRadius;
    private List<Quest> questList;
    private boolean generateQuests;

    public QuestManager(MapsActivity map)
    {
        mMap = map;

        controller = mMap.getController();
        initialLocation = mMap.getInitialLocation();
    }

    public void checkValidQuestRadius(Location location)
    {
        if (initialLocation.equals(null))
        {
            validateQuestRadius = new ValidateQuestRadius(getLatitude(location), getLongitude(location), initialLocation);
        } else
        {
            generateQuests = true;
        }

        if (!validateQuestRadius.isQuestRadiusValid() || generateQuests)
        {
            generateQuests(location);
        }
    }

    public void generateQuests(Location location)
    {

        if (controller.getQuests(getUsername(), getLatitude(location), getLongitude(location)))
        {
            questList = controller.getQuestList();
            mMap.setInitialLocation(location);

            for (Quest quest : questList)
            {
                generateQuestLocation(quest);
            }
        }
    }

    private void generateQuestLocation(Quest quest)
    {
        questLocation = new LatLng(
                quest.getLatitude().doubleValue(),
                quest.getLongitude().doubleValue()
        );

        mMap.getMap().addMarker(new MarkerOptions()
                .position(questLocation)
                .title("Quest"));
    }

    private String getUsername()
    {
        return controller.getUser().getUsername();
    }

    private BigDecimal getLatitude(Location location)
    {
        return new BigDecimal(location.getLatitude(), MathContext.DECIMAL64);
    }

    private BigDecimal getLongitude(Location location)
    {
        return new BigDecimal(location.getLongitude(), MathContext.DECIMAL64);
    }
}