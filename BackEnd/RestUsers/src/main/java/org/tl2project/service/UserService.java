package org.tl2project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tl2project.model.User;
import org.tl2project.repository.UserRepository;

import java.util.List;



@Service
public class UserService {

  @Autowired
  UserRepository repository;

  public boolean register(String username, String email, String password) {
    
    boolean response;
    try{

    List<User> users = repository.findByUsernameOrEmail(username, email);
    if (users.isEmpty()) {

      User user = new User(username, email, password);
      repository.save(user);
      response = true;

    } else {
      response = false;
     
    }
    }
    catch(Exception e){
      response = false;
    }
    
    return response;
  }

  public boolean login(String username, String password) {

    boolean response;
    try{

    List<User> users = repository.findByUsernameAndPassword(username, password);

    if (users.isEmpty()) {
      
      response = false;
    } else {
      
      response =  true;
    }
    }
    catch(Exception e){
      response = false;
    }
    
    return response;
  }

}
