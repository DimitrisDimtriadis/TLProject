package com.example.johnywalker.adventure_go.mockController;

/**
 * Created by JohnyWalker on 11/06/2016.
 */

public interface IDao
{
    boolean attemptUserVerification(String username, String password);
    boolean attemptUserRegistration(String username, String email, String password);
}
