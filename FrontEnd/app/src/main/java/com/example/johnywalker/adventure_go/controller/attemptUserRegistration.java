package com.example.johnywalker.adventure_go.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by JohnyWalker on 12/03/2016.
 */

public class attemptUserRegistration
{
    public boolean attemptUserRegister(String username, String email, String password)
    {
        try
        {
            String url = "http://83.212.100.247:8090/user/register";

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("username", username)
                    .queryParam("email", email)
                    .queryParam("password", password);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(builder.build().encode().toUri(), String.class);

            if(responseEntity.getStatusCode() == HttpStatus.CREATED)
            {
                return true;
            }
            else if(responseEntity.getStatusCode() == HttpStatus.IM_USED)
            {
                return false;
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }
}
