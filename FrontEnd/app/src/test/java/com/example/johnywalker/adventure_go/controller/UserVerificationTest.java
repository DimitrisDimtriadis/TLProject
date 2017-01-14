package com.example.johnywalker.adventure_go.controller;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

/**
 * Created by JohnyWalker94 on 15-Dec-16.
 */
 
public class UserVerificationTest
{
	private UserVerification login;
	
	private String username;
	private String password;
	
	@Before
	public void initializeVariablesForSuccess()
	{
		login = new UserVerification();
		
		username = "admin";
		password = "admin";
	}
	
    @Test
    public void userVerificationSuccess() throws Exception
    {
        assertTrue(login.attemptUserVerification(username, password));
    }
	
	@Before
	public void initializeVariablesForUserDoesNotExist()
	{
		login = new UserVerification();
		
		username = "admin123678";
		password = "12345";
	}
	
    @Test
    public void userVerificationUserDoesNotExist() throws Exception
    {
        assertFalse(login.attemptUserVerification(username, password));
    }
	
	@Before
	public void initializeVariablesForInvalidNameWithCharacters()
	{
		login = new UserVerification();
		
		username = "@dm!n";
		password = "admin";
	}
	
    @Test
    public void userVerificationInvalidNameWithCharacters() throws Exception
    {
		//Special characters not allowed
        assertFalse(login.attemptUserVerification(username, password));
    }
	
	@Before
	public void initializeVariablesForInvalidNameTooManyCharacters()
	{
		login = new UserVerification();
		
		username = "admin123456789123456789";
		password = "admin";
	}
	
    @Test
    public void userVerificationInvalidNameTooManyCharacters() throws Exception
    {
		//16 letters maximum
        assertFalse(login.attemptUserVerification(username, password));
    }
	
	@Before
	public void initializeVariablesForWrongPassword()
	{
		login = new UserVerification();
		
		username = "admin";
		password = "12345";
	}
	
    @Test
    public void userVerificationWrongPassword() throws Exception
    {
        assertFalse(login.attemptUserVerification(username, password));
    }
	
	@Before
	public void initializeVariablesForInvalidNameNotEnoughCharacters()
	{
		login = new UserVerification();
		
		username = "ad";
		password = "admin";
	}
	
    @Test
    public void userVerificationInvalidNameNotEnoughCharacters() throws Exception
    {
		//3 letters minimum
        assertFalse(login.attemptUserVerification(username, password));
    }
	
	@After
	public void nullifyVariables()
	{
		login = null;
		
		username = null;
		password = null;
	}
}