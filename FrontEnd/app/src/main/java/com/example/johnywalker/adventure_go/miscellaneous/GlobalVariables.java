package com.example.johnywalker.adventure_go.miscellaneous;

/**
 * Created by Dimitriadis983 on 06-Jan-17.
 */

public class GlobalVariables
{
    public static String reqId;
    public static String url;

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
}
