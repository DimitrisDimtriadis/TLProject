package com.example.johnywalker.adventure_go.miscellaneous;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dimitriadis983 on 14-Jan-17.
 */
public class ValidateStringTest
{
    private ValidateString validateString;

    @Before
    public void initializeVariables()
    {
        validateString = new ValidateString();
    }

    @Test
    public void validateStringSuccess() throws Exception
    {
        assertEquals(100, validateString.validateString("admin"));
    }

    @Test
    public void validateStringFailTooSmall() throws Exception
    {
        assertEquals(300, validateString.validateString("ad"));
    }

    @Test
    public void validateStringFailTooLong() throws Exception
    {
        assertEquals(400, validateString.validateString("admin12345admin12345"));
    }

    @Test
    public void validateStringFailContainSpaceCharacter() throws Exception
    {
        assertEquals(500, validateString.validateString("ad min"));
    }

    @Test
    public void validateStringFailContainsSpecialCharacter() throws Exception
    {
        assertEquals(600, validateString.validateString("@dm!n"));
    }

    @Test
    public void validateStringFailEmpty() throws Exception
    {
        assertEquals(700, validateString.validateString(""));
    }

    @Test
    public void getErrorMessage100() throws Exception
    {
        assertEquals("successful", validateString.getErrorMessage(100));
    }

    @Test
    public void getErrorMessage300() throws Exception
    {
        assertEquals("is too small", validateString.getErrorMessage(300));
    }

    @Test
    public void getErrorMessage400() throws Exception
    {
        assertEquals("is too long", validateString.getErrorMessage(400));
    }

    @Test
    public void getErrorMessage500() throws Exception
    {
        assertEquals("can't contain space character(s)", validateString.getErrorMessage(500));
    }

    @Test
    public void getErrorMessage600() throws Exception
    {
        assertEquals("can't contain special character(s)", validateString.getErrorMessage(600));
    }

    @Test
    public void getErrorMessage700() throws Exception
    {
        assertEquals("field is required", validateString.getErrorMessage(700));
    }

    @After
    public void nullifyVariables()
    {
        validateString = null;
    }
}