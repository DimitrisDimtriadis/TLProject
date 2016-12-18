package com.example.johnywalker.adventure_go.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by JohnyWalker on 12/14/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User
{
    private Long id;
    private String username;
    private String email;
    private String password;
    private Long score;

    public User(){}

    public User(String username, String email, String password, Long score)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.score = score;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Long getScore()
    {
        return score;
    }

    public void setScore(Long score)
    {
        this.score = score;
    }
}
