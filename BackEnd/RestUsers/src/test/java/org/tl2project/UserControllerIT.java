package org.tl2project;








import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

  



public class UserControllerIT {    
  
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
      get("user/login").
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
      get("user/login").
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
      post("user/register").
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
      post("user/register").
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
      post("user/register").
      then().
        statusCode(226);
  }
  
  @Test
  public void nullParametersRegister() {
     
      
      RestAssured.
      given().
      param("username", "").
      param("email", "").
      param("password", "").
      when().
      post("user/register").
      then().
        statusCode(226);
  }
  
  @Test
  public void nullParametersLogin() {
     
      
      RestAssured.
      given().
      param("username", "").
      param("password", "").
      when().
      get("user/login").
      then().
        statusCode(404);
  }
  
  @Test
  public void settingScoreExcistingUser() {
     
      
      RestAssured.
      given().
      param("username", "test1").
      param("points", 5).
      when().
      get("user/score").
      then().
        statusCode(200);
  }
  @Test
  public void settingScoreNonExcistingUser() {
     
      
      RestAssured.
      given().
      param("username", "test2").
      param("points", 5).
      when().
      get("user/score").
      then().
        statusCode(404);
  }
  
}

