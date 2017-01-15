package com.example.johnywalker.adventure_go.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dimitriadis983 on 14-Jan-17.
 */
public class UserTest
{
    User user;

    private String username;
    private String email;
    private String password;
    private Long score;

    private Long newId;
    private String newUsername;
    private String newEmail;
    private String newPassword;
    private Long newScore;

    @Before
    public void initializeVariables()
    {
        username = "JohnyWalker94";
        email = "JohnyWalker94@email.com";
        password = "12345";
        score = 9001L;

        user = new User(username, email, password, score);

        newId = 2L;
        newUsername = "dimitriadis";
        newEmail = "dimitriadis@email.com";
        newPassword = "abcde";
        newScore = 10L;
    }

    @Test
    public void setId() throws Exception
    {
        user.setId(newId);
        assertEquals(newId, user.getId());
    }

    @Test
    public void getUsername() throws Exception
    {
        assertEquals(username, user.getUsername());
    }

    @Test
    public void setUsername() throws Exception
    {
        user.setUsername(newUsername);
        assertEquals(newUsername, user.getUsername());
    }

    @Test
    public void getEmail() throws Exception
    {
        assertEquals(email, user.getEmail());
    }

    @Test
    public void setEmail() throws Exception
    {
        user.setEmail(newEmail);
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    public void getPassword() throws Exception
    {
        assertEquals(password, user.getPassword());
    }

    @Test
    public void setPassword() throws Exception
    {
        user.setPassword(newPassword);
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    public void getScore() throws Exception
    {
        assertEquals(score, user.getScore());
    }

    @Test
    public void setScore() throws Exception
    {
        user.setScore(newScore);
        assertEquals(newScore, user.getScore());
    }

    @After
    public void nullifyVariables()
    {
        username = null;
        email = null;
        password = null;
        score = null;

        user = null;

        newId = null;
        newUsername = null;
        newEmail = null;
        newPassword = null;
        newScore = null;
    }

}