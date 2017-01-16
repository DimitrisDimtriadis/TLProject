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
      
      if(username.isEmpty() || email.isEmpty() || password.isEmpty() ){
        response = false;
      }
      else{
      
        List<User> users = repository.findByUsernameOrEmail(username, email);
        if (users.isEmpty()) {
          Long score = (long) 0;
          User user = new User(username, email, password, score);
          repository.save(user);
          response = true;

        } else {
      
          response = false;
        }
      }
    }catch(Exception e){
      response = false;
    }
    
    return response;
  }

  public boolean checkLogin(String username, String password) {

    boolean response;
    try{

      if(username.isEmpty() || password.isEmpty() ){
        response = false;
      }
      else{
          List<User> users = repository.findByUsernameAndPassword(username, password);

<<<<<<< HEAD
          if (users.isEmpty()) { 
=======
          if (users.isEmpty()) {
>>>>>>> origin/TasosKobi
      
              response = false;
          }else 
          {
      
              response =  true;
          }
      }
    }catch(Exception e){
      response = false;
    }
    
    return response;
  }
  public Long getScore(String username, Long points){
    
    Long score = (long) 0;
   
    
    User user = repository.findByUsername(username);
    if(user != null){
      
      user.setScore(user.getScore()+points);
      score = user.getScore();
      repository.save(user);
    }
    return score;   
  }
public User getUser(String username){
    
    User user = repository.findByUsername(username);
    
    return user;
  }
  

}
