package com.example.johnywalker.adventure_go.controller;

import com.example.johnywalker.adventure_go.miscellaneous.GlobalVariables;
import com.example.johnywalker.adventure_go.models.User;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Dimitriadis983 on 16-Jan-17.
 */

public class TopScorePlayer
{
    private GlobalVariables globalVariables = new GlobalVariables();
    private RestTemplate restTemplate;

    public void getTopScorePlayer()
    {
        /*try
        {
            String url = "http://83.212.100.247:8090/user/getscores";

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

            restTemplate = new RestTemplate();
            ResponseEntity<List<User>> userResponse = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>()
            {
            });

            globalVariables.setTopScore(userResponse.getBody().get(0).getScore());

            if(userResponse.getBody().get(0).getUsername() == null)
            {
                globalVariables.setTopPlayer(globalVariables.getUser().getUsername());
            }
            else
            {
                globalVariables.setTopPlayer(userResponse.getBody().get(0).getUsername());
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }*/
    }
}
