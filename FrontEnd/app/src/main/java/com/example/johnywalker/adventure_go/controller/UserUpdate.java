package com.example.johnywalker.adventure_go.controller;

import com.example.johnywalker.adventure_go.models.User;

/**
 * Created by Dimitriadis983 on 13-Jan-17.
 */

public class UserUpdate
{
    User user;

    public UserUpdate(User u)
    {
        user = u;
    }

    public void update()
    {
//        try
//        {
//            String url = "http://83.212.100.247:8090/user/update";
//
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
//                    .queryParam("user", user);
//
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<User> userResponse = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<User>()
//            {
//            });
//
//            if(userResponse.getStatusCode() == HttpStatus.OK)
//            {
//                System.out.println("User update successful");
//            }
//            else if(userResponse.getStatusCode() == HttpStatus.NOT_FOUND)
//            {
                System.out.println("User update failed");
//            }
//            else
//            {
//                System.out.println("Error while updating user");
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println("Exception: " + e.getMessage());
//        }
    }
}
