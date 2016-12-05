package org.tl2project.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.tl2project.model.User;


public interface UserRepository extends CrudRepository<User, Long> {


  List<User> findByUsernameAndPassword(String username, String password);

  List<User> findByUsernameOrEmail(String username, String email);
  
  User findByUsername(String username);
}
