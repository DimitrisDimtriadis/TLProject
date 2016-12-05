package org.tl2project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.tl2project.model.Quest;
import org.tl2project.model.User;
import org.tl2project.service.UserService;



@RequestMapping("/user")
@RestController
public class UserController {

  @Autowired
  UserService userservice;



  @RequestMapping("/register")
  public ResponseEntity<String> register(@RequestParam(value = "username") String username,
      @RequestParam(value = "email") String email,
      @RequestParam(value = "password") String password) {



    if (userservice.register(username, email, password)) {


      return new ResponseEntity<String>("User Created", HttpStatus.CREATED);
    } else {

      return new ResponseEntity<String>("Username or Email Already Exist", HttpStatus.IM_USED);
    }
  } 
 
  @RequestMapping(path="/login", method=RequestMethod.GET)
  public ResponseEntity<String> login(@RequestParam(value = "username") String username,
      @RequestParam(value = "password") String password) {
    
    
    if (userservice.login(username, password)) {
        
      return new ResponseEntity<String>(username, HttpStatus.OK);
    } else {
      return new ResponseEntity<String>("Username or Password Wrong", HttpStatus.NOT_FOUND);
    }

      
      
    

  }
  @RequestMapping("/score")
  public ResponseEntity<String> score(@RequestParam(value = "username") String username,
      @RequestParam(value = "points") Long points) {
          
    Long score = userservice.getScore(username, points);

    return new ResponseEntity<String>(Long.toString(score), HttpStatus.OK);
    
  } 


}
