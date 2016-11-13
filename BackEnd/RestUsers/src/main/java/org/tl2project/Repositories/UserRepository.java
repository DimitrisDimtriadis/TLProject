package org.tl2project.Repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import org.tl2project.Models.User;


public interface UserRepository extends CrudRepository<User, Long> {

	
	List<User> findByUsernameAndPassword(String username,String password);
	List<User> findByUsernameOrEmail(String username,String email);
}
