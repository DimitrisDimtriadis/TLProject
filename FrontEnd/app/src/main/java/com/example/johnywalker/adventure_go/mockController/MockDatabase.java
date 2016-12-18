package com.example.johnywalker.adventure_go.mockController;

/**
 * Created by JohnyWalker on 11/06/2016.
 */

public class MockDatabase implements IDao
{
    private String name = "admin", mail = "admin", pass = "admin";

    public boolean attemptUserVerification(String username, String password)
    {
        if(password.equals(pass) && username.equals(name))
        {
            return true;
        }
        return false;
    }

    public boolean attemptUserRegistration(String username, String email, String password)
    {
        if(email.equals(mail))
        {
            System.out.println("Error: email");
            return false;
        }
        else if(username.equals(name))
        {
            System.out.println("Error: username");
            return false;
        }
        else
        {
            return true;
        }
    }
}
