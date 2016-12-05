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



@RequestMapping(path="/quest")
@RestController
public class QuestController {
  
  @Autowired
  QuestService questservice;
  
  
  @RequestMapping(path="/getQuests" ,method=RequestMethod.GET, produces="application/json")    
  public @ResponseBody List<Quest> getQuests(
      @RequestParam(value="latitude", required=true) BigDecimal lat,
      @RequestParam(value="longitude", required=true) BigDecimal lng,
      @RequestParam(value="score", required=true) Long score) {
      
      
    return questservice.produceQuests(lat, lng, score);
          
  }


}
