package org.tl2project;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Rule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.tl2project.model.Riddle;


public class RiddleTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();
  
  
   private Riddle riddle = new Riddle();
  
  @Test
  public void difficultyEasy() throws Exception{
    
      Long score = (long) 2;
      String dif = riddle.calculateDifficulty(score);
      assertEquals("EASY", dif);
  }
  @Test
  public void difficultyMedium() throws Exception{
    
    Long score = (long) 25;
      assertEquals("MEDIUM", riddle.
          calculateDifficulty(score));
  }
  @Test
  public void difficultyHard() throws Exception{
    
      Long score = (long) 55;
      assertEquals("HARD", riddle.
          calculateDifficulty(score));
  }
  @Test
  public void difficultyVeryHard() throws Exception{
    
      Long score = (long) 105;
      assertEquals("VERY HARD", riddle.calculateDifficulty(score));
  }
}
