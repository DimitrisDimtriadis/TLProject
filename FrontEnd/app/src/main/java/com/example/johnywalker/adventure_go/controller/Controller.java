package com.example.johnywalker.adventure_go.controller;

/**
 * Created by JohnyWalker on 12/02/2016.
 */

public class Controller implements IDao
{
    private attemptUserVerification login;
    private attemptUserRegistration register;

    @Override
    public boolean userVerification(String username, String password)
    {
        login = new attemptUserVerification();
        return login.attemptUserLogin(username, password);
    }

    @Override
    public boolean userRegistration(String username, String email, String password)
    {
        register = new attemptUserRegistration();
        return register.attemptUserRegister(username, email, password);
    }
}
