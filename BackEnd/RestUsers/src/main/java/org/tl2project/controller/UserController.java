package org.tl2project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tl2project.service.UserService;



@RequestMapping("/api")
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

  @RequestMapping("/login")
  public ResponseEntity<String> login(@RequestParam(value = "username") String username,
      @RequestParam(value = "password") String password) {

    if (userservice.login(username, password)) {

      return new ResponseEntity<String>("Login Complete", HttpStatus.OK);
    } else {
      return new ResponseEntity<String>("Username or Password Wrong", HttpStatus.NOT_FOUND);
    }

  }



}
