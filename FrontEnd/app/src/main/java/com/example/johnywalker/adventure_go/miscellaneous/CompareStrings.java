package com.example.johnywalker.adventure_go.miscellaneous;

/**
 * Created by Dimitriadis983 on 13-Jan-17.
 */

public class CompareStrings
{
    public boolean compareStrings(String string1, String string2)
    {
        if(string1.equals(string2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean strictCompareStrings(String string1, String string2)
    {
        string1 = modifyString(string1);
        string2 = modifyString(string2);

        if(string1.equals(string2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private String modifyString(String s)
    {
        String result = s;
        result = result.toUpperCase();
        result = result.trim();

        return result;
    }
}
