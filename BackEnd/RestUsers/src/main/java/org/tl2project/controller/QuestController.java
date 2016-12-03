package org.tl2project.controller;




import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tl2project.model.Quest;
import org.tl2project.service.QuestService;



@RequestMapping("/quest")
@RestController
public class QuestController {
  
  @Autowired
  QuestService questservice;
  
  @RequestMapping(method=RequestMethod.GET, produces="application/json")    
  public @ResponseBody List<Quest> getQuests(@RequestParam(value="latitude", required=true) BigDecimal lat,
      @RequestParam(value="longtitude", required=true) BigDecimal lng,
      @RequestParam(value="score", required=true) int score) {
    
      String difficulty = "EASY";
      
    
    
    return questservice.produceQuests(lat, lng, difficulty);
          
  }

}
