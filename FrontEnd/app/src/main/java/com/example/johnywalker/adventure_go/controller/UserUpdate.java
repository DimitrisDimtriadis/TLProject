package com.example.johnywalker.adventure_go.controller;

import com.example.johnywalker.adventure_go.models.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Dimitriadis983 on 13-Jan-17.
 */

public class UserUpdate
{
    public boolean update(User user)
    {
        try
        {
            String url = "http://83.212.100.247:8090/user/score";

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("username", user.getUsername())
                    .queryParam("score", user.getScore());

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(builder.build().encode().toUri(), String.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK)
            {
                System.out.println("User update successful");
                return true;
            }
            else
            {
                System.out.println("Error while updating user");
                return false;
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }
}
