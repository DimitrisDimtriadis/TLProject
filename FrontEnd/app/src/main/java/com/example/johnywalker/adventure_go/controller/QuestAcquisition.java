package com.example.johnywalker.adventure_go.controller;

import android.location.Location;

import com.example.johnywalker.adventure_go.models.Quest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by JohnyWalker on 12/18/2016.
 */

public class QuestAcquisition
{
    private List<Quest> questList;
    private Location initialLocation;


    public boolean getQuests(String username, BigDecimal latitude, BigDecimal longitude)
    {
        String url = "http://83.212.100.247:8090/quest/getQuests";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("username", username);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Quest>> questResponse = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Quest>>()
        {
        });

        setQuestList(questResponse.getBody());
        initialLocation.setLatitude(latitude.doubleValue());
        initialLocation.setLongitude(longitude.doubleValue());

        return true;
    }

    public List<Quest> getQuestList()
    {
        return questList;
    }

    public void setQuestList(List<Quest> questList)
    {
        this.questList = questList;
    }

    public Location getInitialLocation()
    {
        return initialLocation;
    }

    public void setInitialLocation(Location initialLocation)
    {
        this.initialLocation = initialLocation;
    }
}
