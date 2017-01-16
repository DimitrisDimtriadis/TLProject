package com.example.johnywalker.adventure_go.controller;

import com.example.johnywalker.adventure_go.mockController.IDao;
import com.example.johnywalker.adventure_go.models.User;

/**
 * Created by Dimitriadis983 on 14-Dec-16.
 */

public class Controller implements IDao
{
    private UserVerification login;
    private UserRegistration register;
    private UserUpdate userUpdate;
    private TopScorePlayer topScorePlayer;

    @Override
    public boolean attemptUserVerification(String username, String password)
    {
        login = new UserVerification();
        return login.attemptUserVerification(username, password);
    }

    @Override
    public boolean attemptUserRegistration(String username, String email, String password)
    {
        register = new UserRegistration();
        return register.attemptUserRegistration(username, email, password);
    }

    public boolean userUpdate(User user)
    {
        userUpdate = new UserUpdate();
        return userUpdate.update(user);
    }

    public void updateScoreboard()
    {
        topScorePlayer = new TopScorePlayer();
        topScorePlayer.getTopScorePlayer();
    }
}
