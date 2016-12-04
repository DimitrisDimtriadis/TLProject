package com.example.johnywalker.adventure_go.controller;

/**
 * Created by JohnyWalker on 11/06/2016.
 */

public class MockDatabase implements IDao
{
    private String name = "admin", eMail = "admin", pass = "admin";

    public boolean userVerification(String email, String password)
    {
        if(password.equals(pass) && email.equals(eMail))
        {
            return true;
        }
        return false;
    }

    public boolean userRegistration(String username, String email, String password)
    {
        if(!email.equals(eMail))
        {
            return false;
            //Email error
        }
        else if(!pass.equals(password))
        {
            return false;
            //Password error
        }
        else if(!username.equals(name))
        {
            return false;
            //Username error
        }
        else
        {
            return true;
        }
    }
}
