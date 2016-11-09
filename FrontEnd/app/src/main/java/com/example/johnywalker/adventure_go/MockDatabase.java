package com.example.johnywalker.adventure_go;

/**
 * Created by JohnyWalker on 11/06/2016.
 */

public class MockDatabase implements IDao
{
    private String name = "admin", eMail = "admin", pass = "admin";

    @Override
    public boolean attemptDatabaseConnection()
    {
        return true;
    }

    public boolean verifyUser(String email, String password)
    {
        if(password.equals(pass) && email.equals(eMail))
        {
            return true;
        }
        return false;
    }

    public boolean registerUser(String username, String email, String password)
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
