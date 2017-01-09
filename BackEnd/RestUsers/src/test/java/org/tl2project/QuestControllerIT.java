package org.tl2project;
import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

public class QuestControllerIT {
  
  @Before
  public void setup (){ 
    int port = 8090;
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
    
  }
  
  @Test
  public void getQuests() {
     
      
      RestAssured.
      given().
      param("latitude", "1.111111").
      param("longitude", "1.111111").
      param("score", (long)5).
      when().
      get("quest/getQuests").
      then().
        statusCode(200);
  }

}
