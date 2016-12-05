package org.tl2project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.tl2project.model.Riddle;

public interface RiddleRepository extends CrudRepository<Riddle, Long>{
  
  List<Riddle> findAll();
  
  List<Riddle> findByCategory(String category);

  List<Riddle> findByPoints(String points);
  
  List<Riddle> findByDifficulty(String difficulty);

}
