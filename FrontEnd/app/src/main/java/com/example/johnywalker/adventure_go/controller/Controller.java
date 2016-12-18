package com.example.johnywalker.adventure_go.controller;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.johnywalker.adventure_go.mockController.IDao;
import com.example.johnywalker.adventure_go.models.Quest;
import com.example.johnywalker.adventure_go.models.User;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by JohnyWalker on 12/14/2016.
 */

public class Controller implements IDao, Parcelable
{
    private UserVerification login;
    private UserRegistration register;
    private QuestAcquisition obtainQuests;

    protected Controller(Parcel in)
    {
    }

    public Controller(){}

    public static final Creator<Controller> CREATOR = new Creator<Controller>()
    {
        @Override
        public Controller createFromParcel(Parcel in)
        {
            return new Controller(in);
        }

        @Override
        public Controller[] newArray(int size)
        {
            return new Controller[size];
        }
    };

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

    public boolean getQuests(String username, BigDecimal latitude, BigDecimal longitude)
    {
        obtainQuests = new QuestAcquisition();
        return obtainQuests.getQuests(username, latitude, longitude);
    }

    public User getUser()
    {
        return login.getUser();
    }

    public List<Quest> getQuestList()
    {
        return obtainQuests.getQuestList();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
    }
}
