package com.example.johnywalker.adventure_go.miscellaneous;

import com.example.johnywalker.adventure_go.models.User;

import java.util.List;

/**
 * Created by Dimitriadis983 on 06-Jan-17.
 */

public class GlobalVariables
{
    public static String reqId;
    public static String url;
    public static User user;
    public static String lastQuestionAnswered;

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {

        return url;
    }

    public void setReqID(String REQID)
    {
        this.reqId = REQID;
    }

    public String getReqID()
    {
        return this.reqId;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public User getUser()
    {
        return user;
    }

    public void setLastQuestionAnswered(String lastQuestionAnswered)
    {
        this.lastQuestionAnswered = lastQuestionAnswered;
    }

    public String getLastQuestionAnswered()
    {
        return lastQuestionAnswered;
    }
}
