package org.tl2project;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.tl2project.model.User;
import static org.mockito.Mockito.*;


public class UserTest {
  
    private Long id;
    private String typeString;
    
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    
    @Mock
     private User user;
    
    
    @Test
    public void getIdTest(){        
        when(user.getId()).thenReturn(id); 
        assertEquals(id,user.getId()); 
    }
    @Test
    public void setIdTest (){
        user.setId(id);
        verify (user).setId(id);        
    }
    @Test
    public void getUsernameTest(){
        when(user.getUserName()).thenReturn(typeString);
        assertEquals(typeString,user.getUserName());            
    }
    @Test
    public void setUsernameTest (){
        user.setUserName(typeString);
        verify (user).setUserName(typeString);      
    }
    @Test
    public void getPasswordTest(){
        when(user.getPassword()).thenReturn(typeString);
        assertEquals(typeString,user.getPassword());            
    }
    @Test
    public void setPasswordTest (){
        user.setPassword(typeString);
        verify (user).setPassword(typeString);      
    }
    @Test
    public void getemailTest(){
        when(user.getEmail()).thenReturn(typeString);
        assertEquals(typeString,user.getEmail());           
    }
    @Test
    public void setEmailTest (){
        user.setEmail(typeString);
        verify (user).setEmail(typeString);     
    }
    
}