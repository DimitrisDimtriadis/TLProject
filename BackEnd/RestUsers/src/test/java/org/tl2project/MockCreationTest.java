package org.tl2project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.tl2project.controller.UserController;
import org.tl2project.service.UserService;
import static org.mockito.Mockito.when;

public class MockCreationTest {
		public String username = "admin";
		public String password = "admin";
	@Mock
		private UserController userController;
	@Mock
		private UserService userService;
	
	@Before
		public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void CreationTest() {
		assertNotNull(userController);
		assertNotNull(userService);	
	}
	@Test
	public void StubTest(){
		when(userService.checkLogin(username, password)).
		thenReturn(true);
		assertEquals(true, userService.checkLogin(username ,password));
	}
	

	
}
