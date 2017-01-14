package com.example.johnywalker.adventure_go.miscellaneous;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dimitriadis983 on 14-Jan-17.
 */
public class CompareStringsTest
{
    private CompareStrings compareStrings;
    private String username;

    @Before
    public void initializeVariables()
    {
        compareStrings = new CompareStrings();
        username = "admin";
    }

    @Test
    public void compareStringsSuccess() throws Exception
    {
        assertTrue(compareStrings.compareStrings("admin", username));
    }

    @Test
    public void compareStringsFail() throws Exception
    {
        assertFalse(compareStrings.compareStrings("root", username));
    }

    @Test
    public void strictCompareStringsSuccess() throws Exception
    {
        assertTrue(compareStrings.strictCompareStrings("admin", username));
    }

    @Test
    public void strictCompareStringsSuccessMixedCaseLetters() throws Exception
    {
        assertTrue(compareStrings.strictCompareStrings("AdMiN", username));
    }

    @Test
    public void strictCompareStringsSuccessWithSpaces() throws Exception
    {
        assertTrue(compareStrings.strictCompareStrings("admin   ", username));
    }

    @Test
    public void strictCompareStringsFail() throws Exception
    {
        assertFalse(compareStrings.strictCompareStrings("admin1", username));
    }

    @After
    public void nullifyVariables()
    {
        compareStrings = null;
        username = null;
    }
}