package com.example.johnywalker.adventure_go;

/**
 * Created by JohnyWalker on 11/06/2016.
 */

public interface IDao
{
    boolean attemptDatabaseConnection();
    boolean verifyUser(String email, String password);
    boolean registerUser(String email, String emailVerification, String password);
}
