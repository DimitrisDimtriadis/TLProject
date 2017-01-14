package com.example.johnywalker.adventure_go.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dimitriadis983 on 14-Jan-17.
 */
public class QuestTest
{
    private Quest quest;
    private Double latitude, longitude;
    private Double newLatitude, newLongitude;


    @Before
    public void initializeVariables()
    {
        latitude = 41.075481;
        longitude = 23.553565;

        newLatitude = 41.076580;
        newLongitude = 23.553564;

        quest = new Quest(latitude, longitude);

    }

    @Test
    public void getLatitude() throws Exception
    {
        assertEquals(latitude, quest.getLatitude());
    }

    @Test
    public void setLatitude() throws Exception
    {
        quest.setLatitude(newLatitude);
        assertEquals(newLatitude, quest.getLatitude());
    }

    @Test
    public void getLongitude() throws Exception
    {
        assertEquals(longitude, quest.getLongitude());
    }

    @Test
    public void setLongitude() throws Exception
    {
        quest.setLongitude(newLongitude);
        assertEquals(newLongitude, quest.getLongitude());
    }

    @After
    public void nullifyVariables()
    {
        latitude = null;
        longitude = null;

        newLatitude = null;
        newLongitude = null;

        quest = null;
    }
}