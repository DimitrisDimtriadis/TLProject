package com.example.johnywalker.adventure_go.controller;

import com.example.johnywalker.adventure_go.interfaces.IDao;

/**
 * Created by JohnyWalker on 12/02/2016.
 */

public class Controller implements IDao
{
    private UserVerification login;
    private UserRegistration register;

    @Override
    public boolean userVerification(String username, String password)
    {
        login = new UserVerification();
        return login.attemptUserVerification(username, password);
    }

    @Override
    public boolean userRegistration(String username, String email, String password)
    {
        register = new UserRegistration();
        return register.attemptUserRegistration(username, email, password);
    }
}
