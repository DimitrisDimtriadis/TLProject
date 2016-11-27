package org.tl2project;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.tl2project.controller.UserController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class UserControllerTest {
	
	public String username = "admin";
	public String password = "admin";
	public String email = "email";
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	private UserController userController;

	@Test
	public void registerTest() {
		when(userController.register(username, email, password))
			.thenReturn(new ResponseEntity<String>("User Created",HttpStatus.CREATED));
	
		assertEquals(new ResponseEntity<String>("User Created",HttpStatus.CREATED)
			,userController.register(username, email, password) );
	}
	@Test
	public void registerTest2() {
		when(userController.register(username, email, password))
			.thenReturn(new ResponseEntity<String>("Username or Email Already Exist",HttpStatus.IM_USED));
		
		assertEquals(new ResponseEntity<String>("Username or Email Already Exist",HttpStatus.IM_USED)
			,userController.register(username, email, password) );
	}
	@Test
	public void registerTest3() {
		when(userController.register(username, email, password))
			.thenReturn(new ResponseEntity<String>("Username or Email Already Exist",HttpStatus.IM_USED));
		
		assertEquals(new ResponseEntity<String>("Username or Email Already Exist",HttpStatus.IM_USED)
			,userController.register(username, email, password) );
	}
	@Test
	public void RegisterVerification() {
		userController.register(username, null,password);
		verify(userController).register(eq(username),anyString(),eq(password));		
	}
	@Test
	public void RegisterVerification2() {
		userController.register(null, email,password);
		verify(userController).register(anyString(),eq(email),eq(password));		
	}
	@Test
	public void RegisterVerification3() {
		userController.register(username,email,null);
		verify(userController).register(eq(username),eq(email),anyString());		
	}
	@Test
	public void loginTest() {
		when(userController.login(username, password))
			.thenReturn(new ResponseEntity<String>("Login Complete",HttpStatus.OK));
	
		assertEquals(new ResponseEntity<String>("Login Complete",HttpStatus.OK)
			,userController.login(username, password) );
	}
	@Test
	public void loginTest2() {
		when(userController.login(username, password))
			.thenReturn(new ResponseEntity<String>("Username or password wrong",HttpStatus.NOT_FOUND));
	
		assertEquals(new ResponseEntity<String>("Username or password wrong",HttpStatus.NOT_FOUND)
			,userController.login(username, password) );
	}
	@Test
	public void loginVerification() {
		userController.login(username,password);
		verify(userController).login( username,password);		
	}
	@Test
	public void loginVerification2() {
		userController.login(username,null);
		verify(userController).login(eq(username),anyString());		
	}
	@Test
	public void loginVerification3() {
		userController.login(null,password);
		verify(userController).login(anyString(),eq(password));		
	}
}
