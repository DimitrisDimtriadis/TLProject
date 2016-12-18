package org.tl2project.service;

import org.tl2project.model.Quest;
import org.tl2project.model.Riddle;
import org.tl2project.repository.RiddleRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestService {
  
  @Autowired
  RiddleRepository repository;
  
  public List<Quest> produceQuests(BigDecimal lat, BigDecimal lng,Long score){
    
    List<List<BigDecimal>> points = new ArrayList<List<BigDecimal>>();
    
    calculatePoints(lat, lng, points);
    
    String difficulty = calculateDifficulty(score);
    
    ArrayList<Quest> quests = new ArrayList<Quest>();
    
    assignPointsToQuests(quests, difficulty, points);
    
    return quests;
  }
  
  
  private void calculatePoints(BigDecimal lat, BigDecimal lng, List<List<BigDecimal>> points){
    
    BigDecimal distance = new BigDecimal(0.001);
      
    
    points.add(Arrays.asList(lat.subtract(distance),lng));
    
    points.add(Arrays.asList(lat.add(distance),lng));
    
    points.add(Arrays.asList(lat,lng.subtract(distance)));
  
    points.add(Arrays.asList(lat,lng.add(distance)));
      
  }
  
  private void assignPointsToQuests( ArrayList<Quest> quests, String difficulty, List<List<BigDecimal>> points){
        
      int i=0, j=0;
      List<Riddle> riddles = repository.findByDifficulty(difficulty);
      
      for(i=0;i< 4;i++){
        
        Quest quest = new Quest();
        quest.setRiddle(riddles.get(i));
        for(j=0;j< 2;j++){
          
          if(j==0){
            
            quest.setLatitude(points.get(i).get(j));
         }else{
           
            quest.setLongitude(points.get(i).get(j));     
         }
        }
        quests.add(quest);
      }
  }
  
  private String calculateDifficulty(Long score){
    String difficulty = new String();
    
    if(score<20){
      difficulty = "EASY";
    }
    else if(score>=20 && score < 50 ){
        difficulty = "MEDIUM";
    }
    else if(score>=50 && score < 100 ){
      difficulty = "HARD";
    }
    else if(score>=50 && score < 100 ){
      difficulty = "VERY HARD";
    }
    
    
    return difficulty;
  }
}

