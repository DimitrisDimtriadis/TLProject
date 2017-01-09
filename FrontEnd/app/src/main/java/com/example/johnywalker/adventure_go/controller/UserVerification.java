package com.example.johnywalker.adventure_go.controller;

import com.example.johnywalker.adventure_go.miscellaneous.GlobalVariables;
import com.example.johnywalker.adventure_go.models.User;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Dimitriadis983 on 14-Dec-16.
 */

public class UserVerification
{
    private GlobalVariables globalVariables = new GlobalVariables();

    public boolean attemptUserVerification(String username, String password)
    {
        try
        {
            String url = "http://83.212.100.247:8090/user/login";

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("username", username)
                    .queryParam("password", password);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<User> userResponse = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<User>()
            {
            });

            globalVariables.setUser(userResponse.getBody());

            if(userResponse.getStatusCode() == HttpStatus.OK)
            {
                return true;
            }
            else if(userResponse.getStatusCode() == HttpStatus.NOT_FOUND)
            {
                System.out.println("NOT_FOUND");
                return false;
            }
            else
            {
                System.out.println("ERROR");
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
