package org.tl2project.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tl2project.model.User;
import org.tl2project.service.UserService;



@RequestMapping("/user")
@RestController
public class UserController {

  @Autowired
  UserService userservice;



  @RequestMapping("/register")
  public ResponseEntity<String> register(
      @RequestParam(value = "username", required=true) String username,
      @RequestParam(value = "email", required=true) String email,
      @RequestParam(value = "password", required=true) String password) {



    if (userservice.register(username, email, password)) {


      return new ResponseEntity<String>("User Created", HttpStatus.CREATED);
    } else {

      return new ResponseEntity<String>("Username or Email Already Exist", HttpStatus.IM_USED);
    }
  } 
 
  @RequestMapping(path="/login", method=RequestMethod.GET, produces="application/json")
  public @ResponseBody User login(
      @RequestParam(value = "username", required=true) String username,
      @RequestParam(value = "password", required=true) String password,
      HttpServletResponse response) {
    
    User user = new User();
    
    if (!userservice.checkLogin(username, password)) {
      response.setStatus(404);
    } 
    else{
      user = userservice.getUser(username);
    }
    
    
    return user;
    }
    
  @RequestMapping("/score")
  public ResponseEntity<String> score(
      @RequestParam(value = "username", required=true) String username,
      @RequestParam(value = "points", required=true) Long points) {
          
    Long score = userservice.getScore(username, points);
    
    if(score != 0){
    return new ResponseEntity<String>(Long.toString(score), HttpStatus.OK);
    }
    
    return new ResponseEntity<String>(Long.toString(score), HttpStatus.NOT_FOUND);
  } 
  


}
