package org.tl2project;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.tl2project.model.Quest;
import org.tl2project.model.Riddle;
import org.tl2project.model.User;
import org.tl2project.repository.RiddleRepository;
import org.tl2project.repository.UserRepository;
import org.tl2project.service.QuestService;
import org.tl2project.service.UserService;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestServiceTest { 

    
  
    @MockBean
    private RiddleRepository riddleRepository;
    
    @MockBean
    private Riddle riddle;
    

    @Autowired
    QuestService questService;
    
    @Test
    public void produceQuests() throws Exception{
      
      String diff = null;
      BigDecimal lat = new BigDecimal(1);
      BigDecimal lng = new BigDecimal(1);
      Long score = (long) 0;
      
      List <Riddle> riddles = new ArrayList<>();
      Riddle riddle = new Riddle();
      riddles.add(riddle);
      riddles.add(riddle);
      riddles.add(riddle); 
      riddles.add(riddle);
       
      when(this.riddleRepository.findByDifficulty(diff)).thenReturn(riddles);
      assertEquals(4,questService.produceQuests(lat, lng, score).size());
    }
    
    @Test
    public void calculatePointsTest() throws Exception{
      
      List<List<BigDecimal>> points = new ArrayList<List<BigDecimal>>();
      BigDecimal test = new BigDecimal(1);
      points.add(Arrays.asList(test,test));
      points.add(Arrays.asList(test,test));
      points.add(Arrays.asList(test,test));
      points.add(Arrays.asList(test,test));
      BigDecimal lat = new BigDecimal(1);
      BigDecimal lng = new BigDecimal(1);
      List<List<BigDecimal>> points2 = new ArrayList<List<BigDecimal>>();
      points2 = points;
      BigDecimal distance = new BigDecimal(0.001);
      
      
      points2.add(Arrays.asList(lat.subtract(distance),lng)); 
      points2.add(Arrays.asList(lat.add(distance),lng));
      points2.add(Arrays.asList(lat,lng.subtract(distance)));
      points2.add(Arrays.asList(lat,lng.add(distance)));
      
      questService.calculatePoints(lat, lng, points);
      assertEquals(points,points2);  
    }
    
    @Test
    public void assingPointsToQuestsTest() throws Exception{
      
      String diff = null;
      
      ArrayList <Quest> quests = new ArrayList<>();
      List <Riddle> riddles = new ArrayList<>();
      Riddle riddle = new Riddle();
      riddles.add(riddle);
      riddles.add(riddle);
      riddles.add(riddle);
      riddles.add(riddle);
      
      List<List<BigDecimal>> points = new ArrayList<List<BigDecimal>>();
      BigDecimal test = new BigDecimal(1);
      points.add(Arrays.asList(test,test));
      points.add(Arrays.asList(test,test));
      points.add(Arrays.asList(test,test));
      points.add(Arrays.asList(test,test));
      BigDecimal lat = new BigDecimal(1);
      BigDecimal lng = new BigDecimal(1);
      
      Quest quest = new Quest(lat, lng , riddle);
      quests.add(quest);
      quests.add(quest);
      quests.add(quest);
      quests.add(quest);
      ArrayList<Quest> quests2 = quests;
      
      when(this.riddleRepository.findByDifficulty(diff)).thenReturn(riddles);
      
      questService.assignPointsToQuests(quests, diff, points);
      assertEquals(quests,quests2);   
    }
     
    @Test
    public void difficultyEasy() throws Exception{
      
        assertEquals("EASY", questService.
            calculateDifficulty( (long)2 ));
    }
    @Test
    public void difficultyMedium() throws Exception{
      
        assertEquals("MEDIUM", questService.
            calculateDifficulty( (long)25 ));
    }
    @Test
    public void difficultyHard() throws Exception{
      
        assertEquals("HARD", questService.
            calculateDifficulty( (long)55 ));
    }
    @Test
    public void difficultyVeryHard() throws Exception{
      
        assertEquals("VERY HARD", questService.
            calculateDifficulty( (long)105 ));
    }
    
    
}
