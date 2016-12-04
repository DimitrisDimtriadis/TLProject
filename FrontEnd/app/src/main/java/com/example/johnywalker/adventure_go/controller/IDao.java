package com.example.johnywalker.adventure_go.controller;

/**
 * Created by JohnyWalker on 11/06/2016.
 */

public interface IDao
{
    boolean userVerification(String email, String password);
    boolean userRegistration(String username, String email, String password);
}
