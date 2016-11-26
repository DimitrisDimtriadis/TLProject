package org.tl2project;








import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

  



public class UserControllerITest {   
  
  @Before
  public void setup (){
    int port = 8090;
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
    
  }

  
  @Test
  public void excistingUserLogin() {
     
      
      RestAssured.
      given().
      param("username", "admin").
      param("password", "admin").
      when().
      post("api/login").
      then().
        statusCode(200);
  }
  
  @Test
  public void nonExcistingUserLogin() {
     
      
      RestAssured.
      given().
      param("username", "blah").
      param("password", "blah").
      when().
      post("api/login").
      then().
        statusCode(404);
  }
  
  @Test
  public void userRegister() {
     
      
      RestAssured.
      given().
      param("username", "test1").
      param("email", "test1").
      param("password", "test1").
      when().
      post("api/register").
      then().
        statusCode(201);
  }
  
  @Test
  public void excistingUsernameRegister() {
     
      
      RestAssured.
      given().
      param("username", "test1").
      param("email", "test2").
      param("password", "test2").
      when().
      post("api/register").
      then().
        statusCode(226);
  }
  
  @Test
  public void excistingEmailRegister() {
     
      
      RestAssured.
      given().
      param("username", "test2").
      param("email", "test1").
      param("password", "test2").
      when().
      post("api/register").
      then().
        statusCode(226);
  }
  
}

