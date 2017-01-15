package com.example.johnywalker.adventure_go.controller;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

/**
 * Created by JohnyWalker94 on 15-Dec-16.
 */
 
public class UserRegistrationTest
{
	private UserRegistration register;
	
	private String username;
	private String email;
	private String password;
	
	@Before
	public void initializeVariablesForInvalidNameNotEnoughCharacters()
	{
		register = new UserRegistration();
		
		username = "ad";
		email = "ad";
		password = "12345";
	}
	
    @Test
    public void userRegistrationInvalidNameNotEnoughCharacters() throws Exception
    {
		//3 characters minimum
        assertFalse(register.attemptUserRegistration(username, email, password));
    }
	
	@Before
	public void initializeVariablesForUserAlreadyExists()
	{
		register = new UserRegistration();
		
		username = "admin";
		email = "12345";
		password = "12345";
	}
	
    @Test
    public void userRegistrationUserAlreadyExists() throws Exception
    {
		//Different email & password for existing user
        assertFalse(register.attemptUserRegistration(username, email, password));
    }
	
	@Before
	public void initializeVariablesForSuccess()
	{
		register = new UserRegistration();
		
		username = "testUser";
		email = "testUser";
		password = "12345";
	}
	
    @Test
    public void userRegistrationSuccess() throws Exception
    {
        assertTrue(register.attemptUserRegistration(username, email, password));
    }
	
	@Before
	public void initializeVariablesForInvalidNameWithCharacters()
	{
		register = new UserRegistration();
		
		username = "@dm1n";
		email = "@dm1n";
		password = "@dm1n";
	}
	
    @Test
    public void userRegistrationInvalidNameWithCharacters() throws Exception
    {
		//Special characters not allowed
        assertFalse(register.attemptUserRegistration(username, email, password));
    }
	
	@Before
	public void initializeVariablesForInvalidNameTooManyCharacters()
	{
		register = new UserRegistration();
		
		username = "admin123456789123456789";
		email = "admin123456789123456789";
		password = "12345";
	}
	
    @Test
    public void userRegistrationInvalidNameTooManyCharacters() throws Exception
    {
		//16 characters maximum
        assertFalse(register.attemptUserRegistration(username, email, password));
    }
	
	@Before
	public void initializeVariablesForEmailAlreadyUsed()
	{
		register = new UserRegistration();
		
		username = "name";
		email = "admin";
		password = "12345";
	}
	
    @Test
    public void userRegistrationEmailAlreadyUsed() throws Exception
    {
        assertFalse(register.attemptUserRegistration(username, email, password));
    }
	
	@After
	public void nullifyVariables()
	{
		register = null;
		
		username = null;
		email = null;
		password = null;
	}
}