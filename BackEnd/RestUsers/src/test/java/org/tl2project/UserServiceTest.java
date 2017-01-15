package org.tl2project;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.tl2project.model.User;
import org.tl2project.repository.UserRepository;
import org.tl2project.service.UserService;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest { 

    
  
    @MockBean
    private UserRepository userRepository;

    @Autowired
    UserService userService;
	
    List <User> users = new ArrayList<>(); 
    
    
	@Test
	public void LoginTest() throws Exception{
	  
	  User user = new User("admin","admin","admin", (long) 0);
      users.add(user);
	  
	  when(this.userRepository.findByUsernameAndPassword("admin", "admin")).thenReturn( users);
		
		assertEquals(true, userService.
		    checkLogin(user.getUserName(),user.getPassword())); 
	}
	@Test
	public void LoginFailureTest() {
	  

      User user2 = new User("admin1","admin1","admin1", (long) 0);
      
      
      when(this.userRepository.findByUsernameAndPassword("admin1", "admin1")).thenReturn( users);
        
        assertEquals(false, userService.
            checkLogin(user2.getUserName(),user2.getPassword()));
	}
	
	@Test
	public void LoginVerificationAnyStringUsername() {
		
	  User user = new User("","admin","admin", (long) 0);
     
        assertEquals(false, userService.
            checkLogin(user.getUserName(),user.getPassword()));
	}
	
	@Test
	public void LoginVerificationAnyStringPassword() {
	  
	  User user = new User("admin","","admin", (long) 0);
	     
      assertEquals(false, userService.
          checkLogin(user.getUserName(),user.getPassword()));
	}
	
	@Test
	public void RegisterTest(){ 
	  
	  
      User user = new User("admin1","admin1","admin1", (long) 0);
      
      when(this.userRepository.findByUsernameOrEmail("admin1", "admin1")).thenReturn( users);
        
        assertEquals(true, userService.
            register(user.getUserName(), user.getEmail(), user.getPassword()));
	}
	@Test
	public void RegisterFailureTest(){
	  
	  User user = new User("admin","admin","admin", (long) 0);
      users.add(user);
      
      when(this.userRepository.findByUsernameOrEmail("admin", "admin")).thenReturn( users);
        
        assertEquals(false, userService.
            register(user.getUserName(), user.getEmail(), user.getPassword()));
	}	
	@Test
	public void RegisterVerificationAnyStringEmail() {
		
	  User user = new User("admin","","admin", (long) 0);
	     
      assertEquals(false, userService.
          register(user.getUserName(), user.getEmail(), user.getPassword()));
	}
	@Test
	public void RegisterVerificationAnyStringUsername() {
		
	  User user = new User("","admin","admin", (long) 0);
      
      assertEquals(false, userService.
          register(user.getUserName(), user.getEmail(), user.getPassword()));
	}
	@Test
	public void RegisterVerificationAnyStringPassword() {
	  User user = new User("admin","admin","", (long) 0);
      
      assertEquals(false, userService.
          register(user.getUserName(), user.getEmail(), user.getPassword()));
	}
	
	@Test
    public void getScoreTest() {
	  User user = new User("admin","admin","admin", (long) 0);
	  Long points = (long) 5;
      
      when(this.userRepository.findByUsername("admin")).thenReturn(user);
        
        assertEquals(points, userService.
            getScore(user.getUserName(), points));
    }
	@Test
    public void getUserTest() {
      User user = new User("admin","admin","admin", (long) 0);
      
      
      when(this.userRepository.findByUsername("admin")).thenReturn(user);
        
        assertEquals(user, userService.
            getUser(user.getUserName()));
    }
}
