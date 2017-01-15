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

import org.tl2project.service.QuestService;

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

    @Autowired
    QuestService questService;
    
    List <Riddle> riddles = new ArrayList<>(); 
    
    @Test
    public void testproduceQuests() throws Exception{
      
      for(int i=0;i<=4;i++){
        Riddle riddle = new Riddle();
        riddles.add(riddle);
      }
      BigDecimal lat = new BigDecimal(1);
      BigDecimal lng = new BigDecimal(1);
      Long score = (long) 0;
      
      when(this.riddleRepository.findByDifficulty("EASY")).thenReturn(riddles);
      int size = questService.produceQuests(lat, lng, score).size();
      assertEquals(4,size); 
    }
}
