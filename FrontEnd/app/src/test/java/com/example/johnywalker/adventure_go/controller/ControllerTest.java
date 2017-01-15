package com.example.johnywalker.adventure_go.controller;

import com.example.johnywalker.adventure_go.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dimitriadis983 on 14-Jan-17.
 */
public class ControllerTest
{
    private UserVerification userVerification;
    private UserRegistration userRegistration;
    private UserUpdate userUpdate;

    @Before
    public void initializeVariables()
    {
        userVerification = new UserVerification();
        userRegistration = new UserRegistration();
        userUpdate = new UserUpdate();
    }

    @Test
    public void attemptUserVerificationSuccess() throws Exception
    {
        assertTrue(userVerification.attemptUserVerification("admin", "admin"));
    }

    @Test
    public void attemptUserVerificationFail() throws Exception
    {
        assertFalse(userVerification.attemptUserVerification("admin", "root"));
    }

    @Test
    public void attemptUserRegistrationSuccess() throws Exception
    {
        assertTrue(userRegistration.attemptUserRegistration("testUser1", "testUser1", "testUser1"));
    }

    @Test
    public void attemptUserRegistrationFail() throws Exception
    {
        assertFalse(userRegistration.attemptUserRegistration("admin", "admin", "admin"));
    }

    @Test
    public void attemptUserUpdateSuccess() throws Exception
    {
        assertTrue(userUpdate.update(new User("admin", "admin", "admin", 30L)));
    }

    @Test
    public void attemptUserUpdateFail() throws Exception
    {
        assertFalse(userUpdate.update(new User("user00", "user00", "user00", 30L)));
    }

    @After
    public void nullifyVariables()
    {
        userVerification = null;
        userRegistration = null;
        userUpdate = null;
    }
}