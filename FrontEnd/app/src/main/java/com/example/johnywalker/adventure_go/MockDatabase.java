package com.example.johnywalker.adventure_go;

/**
 * Created by JohnyWalker on 11/06/2016.
 */

public class MockDatabase implements IDao
{
    private String eMail = "admin", pass = "admin";

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

    public boolean registerUser(String email, String emailVerification, String password)
    {
        if(email.equals(emailVerification) && email.equals(eMail))
        {
            if(password.equals(pass));
            {
                return true;
            }
        }
        return false;
    }
}
