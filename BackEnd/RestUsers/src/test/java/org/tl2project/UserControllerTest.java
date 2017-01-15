package org.tl2project;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.tl2project.controller.UserController;
import org.tl2project.model.User;
import org.tl2project.service.UserService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest { 
  
  @Mock
  private UserService userService;

  @InjectMocks
  UserController userController;

  private MockMvc mockMvc;
  
  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
    
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }
  
  @Test
  public void registerTest() throws Exception{
    
    when(userService.register("admin", "admin", "admin")).thenReturn(true);
    
    mockMvc.perform(get("/user/register?username=admin&email=admin&password=admin"))
    .andExpect(status().isCreated());   
  }
 
  @Test  
  public void registerfailTest() throws Exception{
    
    when(userService.register("admin", "admin", "admin")).thenReturn(false);
    
    mockMvc.perform(get("/user/register?username=admin&email=admin&password=admin"))
    .andExpect(status().isImUsed());  
  }
  
  @Test
  public void loginTest() throws Exception {
    
    when(userService.checkLogin("admin", "admin")).thenReturn(true);
    
    mockMvc.perform(get("/user/login?username=admin&password=admin"))
    .andExpect(status().isOk());  
    
  }
  
  @Test
  public void loginfailTest() throws Exception {
    
    when(userService.checkLogin("admin", "admin")).thenReturn(false);
    
    mockMvc.perform(get("/user/login?username=admin&password=admin"))
    .andExpect(status().isNotFound()); 
  }
  
 @Test 
 public void setScoreTest() throws Exception {
    
   Long p = (long) 5;
    when(userService.getScore("admin",p)).thenReturn(p);
    
    mockMvc.perform(get("/user/score?username=admin&points=5"))
    .andExpect(status().isOk());  
    
  }
 @Test
 public void getScoresTest() throws Exception {
   
   List<User> users = new ArrayList<>();
    when(userService.getScores()).thenReturn(users);
    
    mockMvc.perform(get("/user/getscores"))
    .andExpect(status().isOk());  
    
  }
  
}
