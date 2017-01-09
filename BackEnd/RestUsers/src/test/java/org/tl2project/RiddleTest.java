package org.tl2project;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.tl2project.model.Riddle;

import static org.mockito.Mockito.*;

public class RiddleTest {
  private Long id;
  private String typeString;
  
  
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();
  
  @Mock
   private Riddle riddle;
  
  
  @Test
  public void getIdTest(){        
      when(riddle.getId()).thenReturn(id);
      assertEquals(id,riddle.getId());
  }
  @Test
  public void setIdTest (){
    riddle.setId(id);
      verify (riddle).setId(id);        
  }
  @Test
  public void getQuestionTest(){
      when(riddle.getQuestion()).thenReturn(typeString);
      assertEquals(typeString,riddle.getQuestion());            
  }
  @Test
  public void setQuestioneTest (){
      riddle.setQuestion(typeString);
      verify (riddle).setQuestion(typeString);      
  }
  @Test
  public void getAnswerTest(){
      when(riddle.getAnswer()).thenReturn(typeString);
      assertEquals(typeString,riddle.getAnswer());            
  }
  @Test
  public void setAnswerTest (){
      riddle.setAnswer(typeString);
      verify (riddle).setAnswer(typeString);      
  }
  @Test
  public void gethintTest(){
      when(riddle.getHint()).thenReturn(typeString);
      assertEquals(typeString,riddle.getHint());           
  }
  @Test
  public void sethintTest (){
      riddle.setHint(typeString);
      verify (riddle).setHint(typeString);     
  }
}
